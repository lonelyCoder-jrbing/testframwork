package mapreduce

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.streaming.api.scala._

object Demo03 {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    val intput = env.fromElements(
      (3, "hello"),
      (2, "world")
    )

    val intput2 = env.fromElements(
      ("hello", 1),
      ("world", 2)
    )
    val res = intput.join(intput2).where(0).equalTo(1)
    res.print()
  }
}
