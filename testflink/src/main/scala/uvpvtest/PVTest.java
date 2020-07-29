package uvpvtest;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.ContinuousProcessingTimeTrigger;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.io.Serializable;
import java.util.Properties;

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
                "user_behavior", new SimpleStringSchema(), properties));
        SingleOutputStreamOperator<UserInfo> userStream = source.map(new MapFunction<String, UserInfo>() {
            @Override
            public UserInfo map(String s) throws Exception {
                String[] split = s.split(",");
                UserInfo subOrderDetail = new UserInfo();
                subOrderDetail.setOrderId(split[1]);
                subOrderDetail.setUserId(split[0]);
                subOrderDetail.setBehavior(split[3].split(":")[1]);
                return subOrderDetail;
            }
        });
        userStream
                .keyBy("userId")
                .window(TumblingProcessingTimeWindows.of(Time.days(1)))
                .trigger(ContinuousProcessingTimeTrigger.of(Time.seconds(1)))
                .aggregate(new RsesultAggregateFunc())
                .print("每秒执行一次---------");
        env.execute("test");
    }

    public static class RsesultAggregateFunc implements AggregateFunction<UserInfo, ResultInfo, ResultInfo> {
        //初始化计数器：就是给你要计算指标赋予初始值
        @Override
        public ResultInfo createAccumulator() {
            ResultInfo resultInfo = new ResultInfo();
            return resultInfo;
        }

        //指标计算：指标增长的逻辑 比如pv userID一样加一
        @Override
        public ResultInfo add(UserInfo userInfo, ResultInfo resultInfo) {
            if (userInfo.getUserId().equals(resultInfo.getUserId())) {
                resultInfo.setCount(resultInfo.count += 1);
                resultInfo.setBehavior(userInfo.getBehavior());
            } else {
                resultInfo.setUserId(userInfo.getUserId());
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

    public static class UserInfo implements Serializable {

        private static final long serialVersionUID = 1L;
        private String userId;
        private String orderId;
        private String behavior;

        public UserInfo() {
        }

        public UserInfo(String userId, String orderId, String behavior) {
            this.userId = userId;
            this.orderId = orderId;
            this.behavior = behavior;
        }

        public String getUserId() {
            return userId;
        }


        public void setUserId(String userId) {
            this.userId = userId;
        }


        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getBehavior() {
            return behavior;
        }

        public void setBehavior(String behavior) {
            this.behavior = behavior;
        }
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
}

//}
