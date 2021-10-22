/*
 * **********************************************************************\
 * * Project                                                              **
 * *       ______  ______   __    ______    ____                          **
 * *      / ____/ / __  /  / /   / __  /   / __/     (c) 2011-2021        **
 * *     / /__   / /_/ /  / /   / /_/ /   / /_                            **
 * *    /___  / / ____/  / /   / __  /   / __/   Erik Osheim, Tom Switzer **
 * *   ____/ / / /      / /   / / | |   / /__                             **
 * *  /_____/ /_/      /_/   /_/  |_|  /____/     All rights reserved.    **
 * *                                                                      **
 * *      Redistribution and use permitted under the MIT license.         **
 * *                                                                      **
 * \***********************************************************************
 */

package spire
package optional

import spire.algebra.{Order, PartialOrder}
import spire.math.Interval

object intervalSubsetPartialOrder {

  /**
   * Interval partial order defined as follows:
   *
   * I <= J if I is a subset of J.
   */
  class IntervalSubsetPartialOrder[A: Order] extends PartialOrder[Interval[A]] {
    override def eqv(x: Interval[A], y: Interval[A]): Boolean = x == y
    override def lteqv(x: Interval[A], y: Interval[A]): Boolean = x.isSubsetOf(y)
    override def lt(x: Interval[A], y: Interval[A]): Boolean = x.isProperSubsetOf(y)
    override def gteqv(x: Interval[A], y: Interval[A]): Boolean = x.isSupersetOf(y)
    override def gt(x: Interval[A], y: Interval[A]): Boolean = x.isProperSupersetOf(y)

    def partialCompare(x: Interval[A], y: Interval[A]): Double = {
      if (eqv(x, y)) 0.0
      else if (lt(x, y)) -1.0
      else if (gt(x, y)) 1.0
      else Double.NaN
    }
  }
  implicit def intervalSubsetPartialOrder[A: Order]: PartialOrder[Interval[A]] = new IntervalSubsetPartialOrder[A]
}
