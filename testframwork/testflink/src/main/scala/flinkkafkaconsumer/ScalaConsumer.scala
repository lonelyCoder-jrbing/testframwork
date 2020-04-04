package flinkkafkaconsumer

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

object ScalaConsumer {
  def main(args: Array[String]): Unit = {
    val senv = StreamExecutionEnvironment.getExecutionEnvironment();

//    val textStreaming = senv.fromElements(
//
//      "To be, or not to be,--that is the question:--",
//      "Whether 'tis nobler in the mind to suffer",
//      "The slings and arrows of outrageous fortune",
//      "Or to take arms against a sea of troubles,")
//     val countsStreaming = textStreaming
//      .flatMap ((_..split("\\W+") )
//      .map { (_, 1) }.keyBy(0).sum(1)
//    countsStreaming.print()
//    senv.execute("Streaming Wordcount")



  }
}
