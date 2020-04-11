package flinkkafkaconsumer

//package flinkkafkademo.testFlinkKafka.flinkkafkaconsumer

import java.text.SimpleDateFormat
import java.util.{Date, Properties}

import org.apache.flink.streaming.api.scala.{StreamExecutionEnvironment, _}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.streaming.util.serialization.SimpleStringSchema

object FlinkScalaStream {
  def main(args: Array[String]): Unit = {
    //创建执行环境
    val senv = StreamExecutionEnvironment.getExecutionEnvironment

    //    senv.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    //    senv.setParallelism(1)
    val topic = "t1"
    val prop = new Properties()
    prop.setProperty("bootstrap.servers", "localhost:9092")
    prop.setProperty("group.id", "con1")

    val myConsumer = new FlinkKafkaConsumer[String](topic, new SimpleStringSchema(), prop)
    val textStream = senv.addSource(myConsumer)
    textStream.print()
    val dataStream = textStream.flatMap(_.split(",")).map(lines => {
      val temp = tranTimeToString(lines)
      println("temp: ", temp)
      temp
    })
      .map((_, 1))
      .keyBy(0)
      .timeWindow(Time.seconds(2), Time.seconds(2))
      .sum(1)
    dataStream.print()


    senv.execute("flink  stream word count")
    //    val inputMap = textStream.map(lines => {
    //        val str = lines.asInstanceOf[String]
    //        var arr = str.split("")
    //        return  (arr(0), arr(1).toLong)
    //    })


  }

  def tranTimeToString(tm: String): String = {
    val fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val tim = fm.format(new Date(tm.toLong))
    tim
  }
}
