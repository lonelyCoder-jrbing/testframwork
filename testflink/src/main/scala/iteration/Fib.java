package iteration;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.IterativeStream;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.Collections;
import java.util.Random;

public class Fib {
    private static final String ITERATE_FLAG = "";
    private static final Integer MAX_RANDOM_VALUE = 10;
    private static final String OUTPUT_FLAG = "";
    private static final Integer OVERFLOW_THRESHOLD = 12;

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment
                .getExecutionEnvironment().setBufferTimeout(1);

//         // 设置保存点的保存路径，这里是保存在hdfs中
//         env.setStateBackend(new FsStateBackend("file:\\nacos"));
//         CheckpointConfig config = env.getCheckpointConfig();
//         // 任务流取消和故障应保留检查点
//         config.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
//         // 保存点模式：exactly_once
//         config.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
//         // 触发保存点的时间间隔
//         config.setCheckpointInterval(60000);


        DataStream<Tuple2<Integer, Integer>> inputStream = env.addSource(new RandomFibonacciSource());

        IterativeStream<Tuple5<Integer, Integer, Integer, Integer, Integer>> iterativeStream =
                inputStream.map(new TupleTransformMapFunction()).iterate(5000);

        DataStream<Tuple5<Integer, Integer, Integer, Integer, Integer>> fibonacciStream =
                iterativeStream.map(new FibonacciCalcStepFunction());

        SplitStream<Tuple5<Integer, Integer, Integer, Integer, Integer>> branchedStream =
                fibonacciStream.split(new FibonacciOverflowSelector());

        iterativeStream.closeWith(branchedStream.select(ITERATE_FLAG));

        DataStream<Tuple3<Integer, Integer, Integer>> outputStream = branchedStream
                .select(OUTPUT_FLAG).map(new BuildOutputTupleMapFunction());

        outputStream.print();
        env.execute("Streaming Iteration Example");
    }

    private static class RandomFibonacciSource
            implements SourceFunction<Tuple2<Integer, Integer>> {

        private Random random = new Random();
        private volatile boolean isRunning = true;
        private int counter = 0;

        @Override
        public void run(SourceContext<Tuple2<Integer, Integer>> ctx) throws Exception {
            while (isRunning && counter < MAX_RANDOM_VALUE) {
                int first = random.nextInt(MAX_RANDOM_VALUE / 2 - 1) + 1;
                int second = random.nextInt(MAX_RANDOM_VALUE / 2 - 1) + 1;

                if (first > second) continue;

                ctx.collect(new Tuple2<Integer, Integer>(first, second));
                counter++;
                Thread.sleep(50);
            }
        }

        @Override
        public void cancel() {
            isRunning = false;
        }
    }

    private static class FibonacciOverflowSelector implements OutputSelector<
            Tuple5<Integer, Integer, Integer, Integer, Integer>> {
        @Override
        public Iterable<String> select(
                Tuple5<Integer, Integer, Integer, Integer, Integer> inputTuple) {
            if (inputTuple.f2 < OVERFLOW_THRESHOLD && inputTuple.f3 < OVERFLOW_THRESHOLD) {
                return Collections.singleton(ITERATE_FLAG);
            }

            return Collections.singleton(OUTPUT_FLAG);
        }
    }

    private static class TupleTransformMapFunction extends RichMapFunction<Tuple2<Integer,
            Integer>, Tuple5<Integer, Integer, Integer, Integer, Integer>> {
        @Override
        public Tuple5<Integer, Integer, Integer, Integer, Integer> map(
                Tuple2<Integer, Integer> inputTuples) throws Exception {
            return new Tuple5<Integer, Integer, Integer, Integer, Integer>(
                    inputTuples.f0,
                    inputTuples.f1,
                    inputTuples.f0,
                    inputTuples.f1,
                    0);
        }
    }

    private static class FibonacciCalcStepFunction extends
            RichMapFunction<Tuple5<Integer, Integer, Integer, Integer, Integer>,
                    Tuple5<Integer, Integer, Integer, Integer, Integer>> {
        @Override
        public Tuple5<Integer, Integer, Integer, Integer, Integer> map(
                Tuple5<Integer, Integer, Integer, Integer, Integer> inputTuple) throws Exception {
            return new Tuple5<Integer, Integer, Integer, Integer, Integer>(
                    inputTuple.f0,
                    inputTuple.f1,
                    inputTuple.f3,
                    inputTuple.f2 + inputTuple.f3,
                    ++inputTuple.f4);
        }
    }

    private static class BuildOutputTupleMapFunction extends RichMapFunction<
            Tuple5<Integer, Integer, Integer, Integer, Integer>,
            Tuple3<Integer, Integer, Integer>> {
        @Override
        public Tuple3<Integer, Integer, Integer> map(Tuple5<Integer, Integer, Integer, Integer,
                Integer> inputTuple) throws Exception {
            return new Tuple3<Integer, Integer, Integer>(
                    inputTuple.f0,
                    inputTuple.f1,
                    inputTuple.f4);
        }
    }

}
