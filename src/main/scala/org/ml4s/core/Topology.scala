package org.ml4s.core

trait Topology {
  def activationFunction: ActivationFunction
  def layers: Seq[Int]
}

object Topology {

  def homogeneous(af: ActivationFunction, layersDef: Seq[Int]): Topology = new Topology {
    override def activationFunction: ActivationFunction = af
    override def layers: Seq[Int] = layersDef
  }
}