package producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import mqutil.ConnectionUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class ProducerCircle {

    private final static String EXCHANGE_NAME = "exchange_name_test";
    private final static String QUEUE_NAME = "queue_name_test";
    private final static String ROUTING_KEY = "routing_key_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        ProducerCircle.sendMsg(EXCHANGE_NAME, QUEUE_NAME, ROUTING_KEY);
    }

    public static void sendMsg(String exchange, String queueName, String routingKey) throws IOException, TimeoutException {
        int i = 0;
        Random random = new Random();
        Double cut = 0.0;
        ConnectionUtil util = new ConnectionUtil();
        Connection connection = util.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, false, null);
        Map<String, Object> args = new HashMap<String, Object>();
//        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEC_ROUTING_KEY);
        args.put("x-dead-letter-exchange", "some.exchange.name");
        channel.queueDeclare(QUEUE_NAME, true, false, false, args);
        while (i < 100) {
            int ints = random.nextInt(100);
            cut = cut(ints);
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, cut.toString().getBytes());

        }
    }

    static double cut(int n) {
        double y = 1.0;
        for (int i = 0; i <= n; i++) {
            double π = 3 * Math.pow(2, i) * y;
            System.out.println("第" + i + "次切割,为正" + (6 + 6 * i) + "边形，圆周率π≈" + π);
            y = Math.sqrt(2 - Math.sqrt(4 - y * y));
        }
        return y;

    }


}
