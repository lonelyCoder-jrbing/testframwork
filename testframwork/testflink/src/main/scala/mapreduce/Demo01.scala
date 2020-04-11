package mapreduce

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.streaming.api.scala._

object Demo01 {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    val text = env.fromElements(
      " i am a it man", "i want to learn flink", "who can teach me?"
    )
    val counts = text.flatMap(_.toLowerCase.split("\\W+") filter (_.nonEmpty))
      .map((_, 1))
      .groupBy(0)
      .sum(1)
    counts.print()
    //    counts.writeAsCsv("D:\\nacos\\test.csv")

  }
}
