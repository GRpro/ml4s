package org.ml4s.core

import org.scalatest.WordSpec

class UtilSpec extends WordSpec {

  "Util" should {
    "perform randomSplit correctly" in {

      val data: Seq[Int] = 0 to 10 // 11 examples

      val Seq(train, test, validation) = Util.randomSplit(data, Seq(0.5, 0.2, 0.3))

      assert(5 == train.size)
      assert(2 == test.size)
      assert(4 == validation.size)
    }
  }
}
