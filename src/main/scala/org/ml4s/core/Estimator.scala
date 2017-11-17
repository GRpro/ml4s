package org.ml4s.core

import breeze.linalg.{DenseMatrix, argmax}

trait Estimator extends (Seq[Datum] => Double)

class RmseEstimator(neuralNetwork: NeuralNetwork) extends Estimator {
  override def apply(testData: Seq[(DenseMatrix[Double], DenseMatrix[Double])]): Double = {
    val se: Double = (for ((input, label) <- testData) yield math.pow(label.data(0) - neuralNetwork.feedForward(input).data(0), 2)).sum
    val mse: Double = se / testData.length
    math.sqrt(mse)
  }

  override def toString(): String = "RMSE"
}

/* Returns the fraction of inputs from test_data for which the network's response is correct.
    *  The output is calculated as the index of the output neuron with the maximum activation.
    */
class ClassificationAccuracyEstimator(neuralNetwork: NeuralNetwork) extends Estimator {
  override def apply(testData: Seq[(DenseMatrix[Double], DenseMatrix[Double])]): Double = {
    (for (
      (input, label) <- testData
      if argmax(neuralNetwork.feedForward(input)) == argmax(label)
    ) yield 1).length.toDouble / testData.length
  }

  override def toString(): String = "accuracy"
}

object Estimator {

  def classificationAccuracy(neuralNetwork: NeuralNetwork): Estimator = new ClassificationAccuracyEstimator(neuralNetwork)

  def rmse(neuralNetwork: NeuralNetwork): Estimator = new RmseEstimator(neuralNetwork)
}
