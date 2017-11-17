package org.ml4s.core

trait ActivationFunction {
  def process(v1: Double): Double
  def derivative(x: Double): Double
}

case class SigmoidFunction() extends ActivationFunction {

  override def process(x: Double): Double =
    1d / (1d + scala.math.exp(-x))

  override def derivative(x: Double): Double = {
    val Fx = process(x)
    Fx * (1d - Fx)
  }
}

case class LinearFunction() extends ActivationFunction {
  override def process(x: Double): Double = x

  override def derivative(x: Double): Double = 1.0
}