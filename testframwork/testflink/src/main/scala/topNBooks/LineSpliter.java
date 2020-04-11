package topNBooks;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class LineSpliter implements FlatMapFunction<String, Tuple2<String,Integer>> {
    @Override
    public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
        out.collect(new Tuple2<String,Integer>(value,1));
    }
}
