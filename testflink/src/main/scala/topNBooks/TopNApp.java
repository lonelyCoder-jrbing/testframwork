package topNBooks;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessAllWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.SlidingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.sql.Timestamp;
import java.util.Properties;
import java.util.TreeMap;

public class TopNApp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        //将处理时间设置为处理时间特征
        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);
        //设置kafka的consumer
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        FlinkKafkaConsumer<String> input = new FlinkKafkaConsumer<>("topn", new SimpleStringSchema(), properties);
        input.setStartFromEarliest();
        DataStreamSource<String> stream = env.addSource(input);
        SingleOutputStreamOperator<Tuple2<String, Integer>> ds = stream.flatMap(new LineSpliter());
        DataStream<Tuple2<String, Integer>> wc = ds.keyBy(0)
                //统计的是过去的一小时之内的热销图书，所以窗口的大小设置为一小时，每5秒钟滑动一次。
                .window(SlidingProcessingTimeWindows.of(Time.seconds(3600), Time.seconds(5)))
                .sum(1);
//             wc.print();
//        System.out.println("===============up=================");


        //每隔5秒钟做一次聚合，计算出过去时间之内热销图书榜的前三名
        wc.windowAll(TumblingProcessingTimeWindows.of(Time.seconds(5)))
                .process(new TopNAllFunction(3))
                .print();
        env.execute("topN");

    }

    private static class TopNAllFunction extends ProcessAllWindowFunction<Tuple2<String, Integer>, String, TimeWindow> {
        private Integer topNSize = 3;

        public TopNAllFunction(int size) {

            this.topNSize = size;
        }


        @Override
        public void process(Context context, Iterable<Tuple2<String, Integer>> elements, Collector<String> out) throws Exception {

            TreeMap<Integer, Tuple2<String, Integer>> treemap = new TreeMap<>((x, y) -> (x < y) ? 1 : -1);
            for (Tuple2<String, Integer> element : elements) {
                treemap.put(element.f1, element);
                if (treemap.size() > topNSize) {
                    treemap.pollLastEntry();
                }
            }
            treemap.forEach((k, v) -> out.collect("\n热销图书列表\n：" + new Timestamp(System.currentTimeMillis()) + "  书名："+v.f0+".   "   +"总的销售量："+k +"\n=============\n"));
        }
    }
}
