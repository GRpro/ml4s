package org.ml4s.core

trait ActivationFunction extends ((Double) => Double) {
  def sign: String

  // TODO derivative etc
}

case class SigmoidFunction() extends ActivationFunction {
  override def apply(v1: Double): Double = ???

  override def sign: String = "s"
}