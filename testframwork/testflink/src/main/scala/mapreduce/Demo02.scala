package mapreduce

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.streaming.api.scala._

object Demo02 {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    val input: DataSet[(Int, String, Double)] = env.fromElements(
      (1, "hello", 5.0),
      (2, "hello", 4.0),
      (1, "hello", 5.0),
      (4, "hello", 6.0)
    )
    //    val res = input.groupBy(1).minBy(2,0)
    //    val output = input.maxBy(2, 0)
    val output = input.distinct(0, 2)
    output.print()
  }
}

