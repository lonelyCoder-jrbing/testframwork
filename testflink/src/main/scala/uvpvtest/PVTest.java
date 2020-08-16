package uvpvtest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.windowing.ProcessAllWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.SlidingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.ContinuousProcessingTimeTrigger;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/***
 *   使用userId进行分组，开10s中的处理时间窗口（模拟线上1天的窗口），
 *   并同时设定ContinuousProcessingTimeTrigger触发器，以1秒周期触发计算去更新最新的PV值
 */
public class PVTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty("group.id", "flink_consumer");

        DataStreamSource<String> source = env.addSource(new FlinkKafkaConsumer<>(
                "user_behavior", new SimpleStringSchema(), properties).setStartFromEarliest());
        SingleOutputStreamOperator<PvUvDTO> userStream =
                source
                        .setParallelism(1)
                        .map(new MapFunction<String, PvUvDTO>() {
                            @Override
                            public PvUvDTO map(String s) throws Exception {
                                PvUvDTO pvUvDTO = JSONObject.parseObject(s, PvUvDTO.class);
                                return pvUvDTO;
                            }
                        });

        userStream
                .keyBy(el -> el.user_id)
//                .print();
                .window(SlidingProcessingTimeWindows.of(Time.seconds(3600), Time.seconds(5)))
                .aggregate(new RsesultAggregateFunc())
                .windowAll(TumblingProcessingTimeWindows.of(Time.seconds(5)))
                .process(new TopNHotItems(8))
//                .print();
                .print("=================topn==============\n");


//        userStream
//                .keyBy("userId")
//                .window(TumblingProcessingTimeWindows.of(Time.hours(1)))
//                .trigger(ContinuousProcessingTimeTrigger.of(Time.seconds(5)))
//                .aggregate(new RsesultAggregateFunc())
//                .print("=================topn==============\n");

        env.execute("test");
    }

    public static class RsesultAggregateFunc implements AggregateFunction<PvUvDTO, ResultInfo, ResultInfo> {
        //初始化计数器：就是给你要计算指标赋予初始值
        @Override
        public ResultInfo createAccumulator() {
            ResultInfo resultInfo = new ResultInfo();
            return resultInfo;
        }

        //指标计算：指标增长的逻辑 比如pv userID一样加一
        @Override
        public ResultInfo add(PvUvDTO userInfo, ResultInfo resultInfo) {
            if (userInfo.getUser_id().equals(resultInfo.getUserId())) {
                resultInfo.setCount(resultInfo.count += 1);
                resultInfo.setBehavior(userInfo.getBehavior());
            } else {
                resultInfo.setUserId(userInfo.getUser_id());
                resultInfo.setCount(1);
                resultInfo.setBehavior(userInfo.getBehavior());
            }
            return resultInfo;
        }

        @Override
        public ResultInfo getResult(ResultInfo resultInfo) {
            return resultInfo;
        }

        //指标结果合并
        @Override
        public ResultInfo merge(ResultInfo resultInfo1, ResultInfo resultInfo2) {
            resultInfo2.setCount(resultInfo2.getCount() + resultInfo1.getCount());
            return resultInfo2;
        }
    }
    @Data
    public static class PvUvDTO implements Serializable {

        private static final long serialVersionUID = 1L;
        private String user_id;
        private String item_id;
        private String category_id;
        private String behavior;
        private String ts;
    }
    public static class ResultInfo implements Serializable {
        private static final long serialVersionUID = 1L;
        private String userId;
        private int count;
        private String behavior;

        public String getBehavior() {
            return behavior;
        }

        public void setBehavior(String behavior) {
            this.behavior = behavior;
        }

        public ResultInfo() {
        }

        public ResultInfo(String userId, int count) {
            this.userId = userId;
            this.count = count;
        }

        public String getUserId() {
            return userId;
        }


        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "ResultInfo{" +
                    "userId='" + userId + '\'' +
                    ", count=" + count +
                    ", behavior='" + behavior + '\'' +
                    '}';
        }
    }

    private static class TopNHotItems extends ProcessAllWindowFunction<ResultInfo, String, TimeWindow> {

        private Integer topSize;

        public TopNHotItems(Integer size) {
            this.topSize = size;
        }

        @Override
        public void process(Context context, Iterable<ResultInfo> elements, Collector<String> out) throws Exception {
            TreeMap<Integer, ResultInfo> map = new TreeMap<>((x, y) -> x > y ? -1 : 1);
            for (ResultInfo element : elements) {
                map.put(element.count, element);
                if (map.size() > topSize) {
                    map.pollLastEntry();
                }
            }
            System.out.println("map:   " + map);
            map.forEach((k, v) -> out.collect("热门商品=====userid:  " + v.getUserId() + " count:  " + k + "\n=============\n"));
        }

    }


}

