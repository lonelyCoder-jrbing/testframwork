package com.springdemo.annotationtest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.springdemo.annotationtest"})
public class AppAnnotation {

    public static void main(String[] args) {
//        new AnnotationConfigApplicationContext(Ap.class);
        SpringApplication.run(AppAnnotation.class, args);
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

            String[] beanNamesForType = ctx.getBeanNamesForType(AppAnnotation.class);
            for(String e:beanNamesForType){
                System.out.println("beanNamesForType------------:"+e);
            }
        };
    }

}