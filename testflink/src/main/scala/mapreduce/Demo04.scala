package mapreduce

import java.util.stream.Collector

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.streaming.api.scala._

case class Rating(name: String, categary: String, points: Int)

object Demo04 {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    val weights: DataSet[(String, Double)] = env.fromElements(
      ("hello", 1.0),
      ("world", 2.0)
    )
    val rating: DataSet[(Rating)] = env.fromElements(Rating("AA", "world", 10))
    val weightedrating = rating.join(weights).where("categary").equalTo(0) {
      (rating, weights) => (rating.name, rating.points * weights._2)
    }
    //    rating.join(weights).where("categary").equalTo(0){
    //      (rating,weights,out: Collector[(String,Double)]) =>
    ////        if (weights._2>2) out.collect()
    //    }

    weightedrating.print()
  }


}
