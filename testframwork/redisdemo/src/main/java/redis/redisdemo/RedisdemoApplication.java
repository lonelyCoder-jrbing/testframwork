package redis.redisdemo;

//import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import redis.clients.jedis.Jedis;

@SpringBootApplication
public class RedisdemoApplication {
//    public Logger log = Logger.getLogger(this.getClass());
    public static void main(String[] args) {
        SpringApplication.run(RedisdemoApplication.class, args);

    }
}
