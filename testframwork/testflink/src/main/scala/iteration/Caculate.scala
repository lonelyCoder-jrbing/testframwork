package iteration

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.streaming.api.scala._

object Caculate {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    val initial = env.fromElements(10)
    val count = initial.iterate(1200) {
      iter: DataSet[Int] =>
        val res = iter.map {
          i =>
            val x = Math.random()
            val y = Math.random()
            i + (if (x * x + y * y < 1) 1 else 0)
        }
        res
    }
    val res = count.map(c => c / 10000.0 * 4)
    res.print()
    env.execute("Pi")
  }
}
