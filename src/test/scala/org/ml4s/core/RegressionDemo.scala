package org.ml4s.core

import breeze.linalg.DenseMatrix

import scala.io.Source

object RegressionDemo extends App {

  case class DataLine(line: String) {
    val raw: Array[Double] = line.split(";") map (_.trim.toDouble)
    val quality = new DenseMatrix(1, 1, raw.takeRight(1))
    val parameters = new DenseMatrix(11, 1, raw.dropRight(1))
  }

  val vineDataSet = Source.fromFile("src/test/resources/winequality-red.csv")
    .getLines()
    .drop(1)
    .map { line => DataLine(line) }
    .map { dataLine => (dataLine.parameters, dataLine.quality) }.toSeq

  val Seq(training, testing) = Util.randomSplit(vineDataSet, Seq(0.9, 0.1))

  println(s"training size: ${training.size}")
  println(s"testing size: ${testing.size}")

  // linear function
  val net = NeuralNetwork.regression(11, Seq(4))

  println("now training")
  // Train for 10 epochs, with mini-batch size 1, and learning rate 0.01
  net.sgd(training, 10, 1, 0.01, Some(testing), Estimator.rmse(net))
//  println(s"final accuracy: ${100.0 * net.evaluate(testing).toDouble / testing.length.toDouble}%")
}
