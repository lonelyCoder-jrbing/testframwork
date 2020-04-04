package waterMarkTest;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;

import javax.annotation.Nullable;

//import org.apache.flink.api.java.tuple.Tuple2;
//import org.apache.flink.streaming.api.functions.TimestampExtractor;

public class WaterMarkTest01 {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        DataStream<String> text = env.socketTextStream("localhost", 9999)
                .assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks<String>(){

                    @Override
                    public long extractTimestamp(String e, long previousElementTimestamp) {
                        return Long.parseLong(e.split(",")[1]);
                    }

                    @Nullable
                    @Override
                    public Watermark getCurrentWatermark() {
                        return new Watermark(System.currentTimeMillis());
                    }
                });

        DataStream<Tuple2<String, Integer>> count = text.map(
                (MapFunction<String, Tuple2<String, Integer>>) m ->
                        Tuple2.of(m.split(",")[0], 1)
                 )
                .keyBy(0)
                .timeWindow(Time.seconds(10), Time.seconds(5))
                .sum(1);
        count.print();
        env.execute("EventTime processing example");

    }

}
