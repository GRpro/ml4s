package org.ml4s.core

import java.io.IOException

import scala.annotation.tailrec
import scala.util.{Random, Try}

object Util {

  def randomSplit[T](source: Seq[T], weights: Seq[Double]): Seq[Seq[T]] = {
    if (weights.sum != 1)
      throw new IllegalArgumentException(s"sum of $weights must be 1.0")
    val lengths = weights.map(_ * source.size).map(_.toInt)

    @tailrec
    def split(lengths: List[Int], remained: Seq[T], aggResult: List[Seq[T]]): List[Seq[T]] = {
      lengths match {
        case Nil =>
          aggResult
        case _ :: Nil =>
          aggResult :+ remained
        case length :: tailLengths =>
          val (current, tail) = remained.splitAt(length)
          split(tailLengths, tail, aggResult :+ current)
      }
    }

    val shuffled = Random.shuffle(source)
    split(lengths.toList, shuffled, List.empty)
  }

  def getFileByName(fileName: String): Option[String] = Try {
    Some(getClass.getClassLoader.getResource(fileName).toString)
  } recover {
      case e: IOException =>
        e.printStackTrace()
        None
  } get
}
