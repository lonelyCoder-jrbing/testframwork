package topNBooks;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyNoParalleSource implements SourceFunction<String> {

    private Boolean isRunning = true;

    static List<String> books = new ArrayList<>();

    static {
        books.add("Pyhton从入门到放弃");//10
        books.add("Java从入门到放弃");//8
        books.add("Php从入门到放弃");//5
        books.add("C++从入门到放弃");//3
        books.add("Scala从入门到放弃");//0-4
    }


    @Override
    public void run(SourceContext<String> ctx) throws Exception {
        while (isRunning) {
            //每隔一秒钟随机向kafka发送一本书籍的名称
            int i = new Random().nextInt(5);
            ctx.collect(books.get(i));
            Thread.sleep(1000);
        }


    }

    @Override
    public void cancel() {
        //当取消的时候，会将isRunning设置为false
        isRunning = false;
    }
}
