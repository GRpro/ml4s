package org.ml4s.core

object TopologyPrinter {

  def main(args: Array[String]): Unit = {
    val topology = Topology.homogeneous(SigmoidFunction(), 4, 3, 7, 2)
    println(topology.toString)
  }
}
