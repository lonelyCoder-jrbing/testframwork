package redis.redisdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * 用缓存要注意，启动类要加上一个注解开启缓存
 */
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
@EnableCaching
@PropertySource(value = {"classpath:"})
public class RedisdemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisdemoApplication.class, args);

    }
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();

//            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println("beanname:     "+beanName);
            }

            String[] beanNamesForType = ctx.getBeanNamesForType(RedisdemoApplication.class);
            for(String e:beanNamesForType){
                System.out.println("beanNamesForType------------:"+e);
            }
        };
    }

}
