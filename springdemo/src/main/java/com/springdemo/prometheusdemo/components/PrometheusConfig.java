//package com.springdemo.prometheusdemo.components;
//
//import io.prometheus.client.Counter;
//import io.prometheus.client.exporter.PushGateway;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PrometheusConfig
//{
//    public static final Counter counterDemo = Counter.build()
//                                                        .name("push_way_counter")
//                                                        .labelNames("wy","zxjr","ocs","xxjf","unit","instance")
//                                                        .help("Counter 实例")
//                                                        .register();
//    //测试发送
//    public static void main(String[] args)
//    {
//        PushGateway prometheusPush = new PushGateway("localhost:9091");
//        try
//        {
//            for(int i=0;i<50;i++){
//                counterDemo.labels("网元","在线接入","OCS","消息计费","byte","localhsot:9091").inc();
//                prometheusPush.push(counterDemo,"sp-getway");
//                Thread.sleep(5000);
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//}