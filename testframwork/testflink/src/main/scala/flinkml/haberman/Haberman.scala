package flinkml.haberman

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.ml.MLUtils
import org.apache.flink.ml.classification.SVM
import org.apache.flink.ml.common.LabeledVector
import org.apache.flink.ml.math.DenseVector

object Haberman {
  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment
    val survival = env.readCsvFile[(String, String, String, String)]("D:\\code\\myproject\\springCloud_dev\\flink\\src\\main\\scala\\flinkkafkademo\\testFlinkKafka\\ml\\haberman\\haberman.data")
    val survivalLV = survival
      .map { tuple =>
        val list = tuple.productIterator.toList
        val numList = list.map(_.asInstanceOf[String].toDouble)
        LabeledVector(numList(3), DenseVector(numList.take(3).toArray))
      }
    survivalLV.print()

    val astroTrainLibSVM: DataSet[LabeledVector] = MLUtils.readLibSVM(env, "/path/to/svmguide1")
    val astroTestLibSVM: DataSet[LabeledVector] = MLUtils.readLibSVM(env, "/path/to/svmguide1.t")

    //分类
    def normalizer: LabeledVector => LabeledVector = {
      lv => LabeledVector(if (lv.label > 0.0) 1.0 else -1.0, lv.vector)
    }
    /*
    val astroTrain: DataSet[LabeledVector] = astroTrainLibSVM.map(normalizer)
    val astroTest: DataSet[(DenseVector, Double)] = astroTestLibSVM.map(normalizer).map(x => (x.vector, x.label))

    val svm = SVM()
      .setBlocks(env.getParallelism)
      .setIterations(100)
      .setRegularization(0.001)
      .setStepsize(0.1)
      .setSeed(42)

    svm.fit(astroTrain)

    val evaluationPairs: DataSet[(Double, Double)] = svm.evaluate(astroTest)

    import org.apache.flink.ml.preprocessing.MinMaxScaler

    val scaler = MinMaxScaler()

    val scaledSVM = scaler.chainPredictor(svm)


    scaledSVM.fit(astroTrain)

    val evaluationPairsScaled: DataSet[(Double, Double)] = scaledSVM.evaluate(astroTest)
    //支持向量机


 */
  }
}
