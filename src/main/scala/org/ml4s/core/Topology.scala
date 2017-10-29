package org.ml4s.core


trait Neuron {
  def activationFunction: ActivationFunction

  override def toString: String = {
    val af = activationFunction.sign
    "(" + af + ")"
  }
}

trait Topology {
  def get: IndexedSeq[IndexedSeq[Neuron]]

  final override def toString: String = {
    val topology = get
    val maxNeuronsInLayer = topology.foldLeft(0)((agg, layer) => math.max(agg, layer.length))
    val networkAsString = (0 until maxNeuronsInLayer).map { n: Int => {
      val r =       topology.map(layer => if (layer.isDefinedAt(n)) {
        val k = layer(n).toString + "\t"
        k
      } else {
        "   \t"
      }).mkString
      r
    }

    }.mkString("\n")

    val markLayers = topology.indices map(i => s"$i\t") mkString

    networkAsString + "\n" + markLayers
  }
}

object Topology {

  def homogeneous(af: ActivationFunction, layers: Int*): Topology = new Topology {
    override def get: IndexedSeq[IndexedSeq[Neuron]] = {
      val topology: Array[Array[Neuron]] = Array.ofDim(layers.length)
      (0 until layers.length) foreach { i =>
        topology(i) = Array.fill(layers(i))(new Neuron {
          override def activationFunction: ActivationFunction = af
        })
      }
      topology.map(_.toIndexedSeq).toIndexedSeq
    }
  }
}