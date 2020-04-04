package waterMarkTest;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WaterMarkTest04 {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        // 设置并行度为1，方便调试
        env.setParallelism(1);

        DataStreamSource<Tuple2<String, Long>> input = env.addSource(new SourceFunction<Tuple2<String, Long>>() {
            String[] letters = new String[]{"A", "B", "C", "D", "E", "F"};
            Random random = new Random();

            @Override
            public void run(SourceContext<Tuple2<String, Long>> ctx) throws Exception {
                while (true) {
                    String key = letters[random.nextInt(6)];
                    Tuple2<String, Long> next = Tuple2.of(key, System.currentTimeMillis());

                    // 打印产生的消息
                    long w = showWindowsStart(next.f1, 0L, 7000L);
                    System.out.println(next + " -> " + converseStringFromDate(new Date(next.f1)) + " -> 所属的窗口起始点是：" + converseStringFromDate
                            (new Date(w)));
                    // event-time 设置为消息本身时间
                    ctx.collectWithTimestamp(next, next.f1);
                    // 消息时间 + Lateness
                    ctx.emitWatermark(new Watermark(next.f1 + 8000));

                    // 生成一条消息，暂停2秒
                    TimeUnit.SECONDS.sleep(2);
                }
            }

            @Override
            public void cancel() {

            }
        });

        input.keyBy(0).timeWindow(Time.seconds(7))
                .reduce((value1, value2) -> Tuple2.of(value1.f0, value1.f1 + value2.f1))
                .map(new MapFunction<Tuple2<String, Long>, Object>() {
                    @Override
                    public Object map(Tuple2<String, Long> value) throws Exception {
                        return Tuple2.of(value.f0, converseStringFromDate(new Date(value.f1)));
                    }
                })
                .print();

        env.execute();


    }

    private static long showWindowsStart(long timestamp, long offset, long windowSize) {
        return timestamp - (timestamp - offset + windowSize) % windowSize;
    }

    private static String converseStringFromDate(Date date) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        String format = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));
        return format;
    }
}
