package flinkml.SVMUsingCoCoA

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
//import org.apache.flink.ml.classification.SVM
//import org.apache.flink.ml.common.LabeledVector
//import org.apache.flink.ml.math.Vector

object SVMUsingCoCoA {
  def main(args: Array[String]): Unit = {
    val pathToTrainingFile: String = "D:\\code\\myproject\\springCloud_dev\\flink\\src\\main\\scala\\flinkkafkademo\\testFlinkKafka\\ml\\haberman.data"
    val pathToTestingFile: String = "D:\\code\\myproject\\springCloud_dev\\flink\\src\\main\\scala\\flinkkafkademo\\testFlinkKafka\\ml\\Thaberman.data"
    val env = ExecutionEnvironment.getExecutionEnvironment
//
//    // Read the training data set, from a LibSVM formatted file
//    val trainingDS: DataSet[LabeledVector] = env.readLibSVM(pathToTrainingFile)
//
//    // Create the SVM learner
//    val svm = SVM()
//      .setBlocks(10)
//
//    // Learn the SVM model
//    svm.fit(trainingDS)
//
//    // Read the testing data set
//    val testingDS: DataSet[Vector] = env.readLibSVM(pathToTestingFile).map(_.vector)
//
//    // Calculate the predictions for the testing data set
//    val predictionDS: DataSet[(Vector, Double)] = svm.predict(testingDS)
//




  }
}
