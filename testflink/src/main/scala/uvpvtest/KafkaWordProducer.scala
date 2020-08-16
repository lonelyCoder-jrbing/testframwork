package uvpvtest

import java.time.Instant
import java.util
import java.util.TimeZone

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import scala.util.Random


/**
  * 生产数据
  */

object KafkaWordProducer {
  def main(args: Array[String]): Unit = {
    // kafka服务器地址
    val brokers = "localhost:9092"
    // kafka topic
    val topic = "user_behavior"
    // 每秒发送三次消息
//    val messagesPerSec = 3
//    // 每次发送五个数字
//    val wordsPerMessage = 5
    val props = new util.HashMap[String, Object]()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String, String](props)
    while (true) {
      val tz = TimeZone.getTimeZone("Asia/Shanghai")
      val instant = Instant.ofEpochMilli(System.currentTimeMillis + tz.getOffset(System.currentTimeMillis))

      val placeholder = "{\"user_id\": \"%s\", \"item_id\":\"%s\", \"category_id\": \"%s\", \"behavior\": \"%s\", \"ts\": \"%s\"}"

      val formatted = placeholder.format(
        Random.nextInt(10),
        Random.nextInt(100),
        Random.nextInt(1000),
        "pv",
        instant.toString)
      println(formatted)
      val message = new ProducerRecord[String, String](topic, null, formatted)
      producer.send(message)

      Thread.sleep(1000)
    }
  }
}
