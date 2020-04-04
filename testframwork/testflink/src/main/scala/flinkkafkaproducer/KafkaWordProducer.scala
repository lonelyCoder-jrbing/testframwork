package flinkkafkaproducer

import java.util

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import scala.util.Random

/**
  * 产生数据
  */

object KafkaWordProducer {
  def main(args: Array[String]): Unit = {
    // kafka服务器地址
    val brokers = "localhost:9092"
    // kafka topic
    val topic = "jurongbing01"
    // 每秒发送三次消息
    val messagesPerSec = 3
    // 每次发送五个数字
    val wordsPerMessage = 5
    val props = new util.HashMap[String, Object]()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String, String](props)
    while (true){
      (1 to messagesPerSec.toInt).foreach{
        messageNum => val str = (1 to wordsPerMessage.toInt).map(x => Random.nextInt(30).toString).mkString(" ")
          println(str)
          val message = new ProducerRecord[String, String](topic, null, str)
          producer.send(message)
      }
      Thread.sleep(500)
    }
  }
}
