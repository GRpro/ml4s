package org.ml4s

import breeze.linalg.{DenseMatrix, DenseVector, argmax}
import org.ml4s.core.{NeuralNetwork, NeuralNetworkForRegression}

import scala.io.Source

object Test2 extends App {

  case class DataLine(line: String) {
    val raw: Array[Double] = line.split(";") map (_.trim.toDouble)
    val quality = new DenseMatrix(1, 1, raw.takeRight(1))
    val parameters =  new DenseMatrix(11, 1, raw.dropRight(1))
  }

  val vineDataSet = Source.fromFile("src/main/resources/winequality-red.csv")
    .getLines()
    .drop(1)
    .map { line => DataLine(line) }
    .map { dataLine => (dataLine.parameters, dataLine.quality) }.toSeq

  // Test/train split
  val testAmount = (vineDataSet.length*0.1).toInt
  val data_train = vineDataSet.dropRight(testAmount)
  val data_test = vineDataSet.takeRight(testAmount)

  val net = NeuralNetworkForRegression(List(11, 8, 4, 2, 1))

  println("now training")
  net.sgd(data_train, 10, 1, 0.0001, Some(data_test))
  println(net.feedForward(new DenseMatrix[Double](11, 1, Array(7.1, 0.68, 0.07, 1.9, 0.075, 16, 51, 0.99685, 3.38, 0.52, 9.5)))) //5 strong
  println(net.feedForward(new DenseMatrix[Double](11, 1, Array(6, 0.31, 0.47, 3.6, 0.067, 18, 42, 0.99549, 3.39, 0.66, 11)))) //6
  println(net.feedForward(new DenseMatrix[Double](11, 1, Array(5.6, 0.62, 0.03, 1.5, 0.08, 6, 13, 0.99498, 3.66, 0.62, 10)))) //4
  println(s"final accuracy: ${100.0 * net.evaluate(data_test).toDouble / data_test.length.toDouble}%")
}
