package org.ml4s

import breeze.linalg.DenseMatrix

package object core {

  type Datum = (DenseMatrix[Double], DenseMatrix[Double])

}
