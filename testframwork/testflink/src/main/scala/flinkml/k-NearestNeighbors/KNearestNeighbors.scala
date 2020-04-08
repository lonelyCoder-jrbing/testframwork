package flinkml.k-NearestNeighbors

import org.apache.flink.api.common.operators.base.CrossOperatorBase.CrossHint
import org.apache.flink.api.scala._
//import org.apache.flink.ml.nn.KNN
//import org.apache.flink.ml.math.Vector
//import org.apache.flink.ml.metrics.distances.SquaredEuclideanDistanceMetric
object KNearestNeighbors {
  def main(args: Array[String]): Unit = {

  val env = ExecutionEnvironment.getExecutionEnvironment

//  // prepare data
//  val trainingSet: DataSet[Vector] = env.readCsvFile("D:\\code\\myproject\\springCloud_dev\\flink\\src\\main\\scala\\flinkkafkademo\\testFlinkKafka\\ml\\k-NearestNeighbors\\test.csv")
//  val testingSet: DataSet[Vector] = env.readCsvFile("D:\\code\\myproject\\springCloud_dev\\flink\\src\\main\\scala\\flinkkafkademo\\testFlinkKafka\\ml\\k-NearestNeighbors\\train.csv")
//
//  val knn = KNN()
//    .setK(3)
//    .setBlocks(10)
//    .setDistanceMetric(SquaredEuclideanDistanceMetric())
//    .setUseQuadTree(false)
//    .setSizeHint(CrossHint.SECOND_IS_SMALL)
//
//  // run knn join
//  knn.fit(trainingSet)
//  val result = knn.predict(testingSet).collect()
  }


}
