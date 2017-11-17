package org.ml4s.core

trait Topology {
  def activationFunction(layer: Int): ActivationFunction
  def layers: Seq[Int]
}

object Topology {

  def homogeneous(af: ActivationFunction, layersDef: Seq[Int]): Topology = new Topology {
    def activationFunction(layer: Int): ActivationFunction = af
    override def layers: Seq[Int] = layersDef
  }

  def homogeneousWithSpecificOutput(af: ActivationFunction, afOutput: ActivationFunction, layersDef: Seq[Int]): Topology = new Topology {
    def activationFunction(layer: Int): ActivationFunction = if (layer == layers.length-1) afOutput else af
    override def layers: Seq[Int] = layersDef
  }
}