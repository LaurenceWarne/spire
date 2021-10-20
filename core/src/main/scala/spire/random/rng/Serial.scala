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
package random
package rng

import spire.util.Pack

final class Serial(seed0: Long) extends LongBasedGenerator {
  private[this] var seed: Long = seed0
  def copyInit: Serial = new Serial(seed)
  def getSeed: Long = seed
  def setSeed(n: Long): Unit = seed = n
  override def getSeedBytes: Array[Byte] = Pack.longToBytes(seed)
  def setSeedBytes(bytes: Array[Byte]): Unit = seed = Pack.longFromBytes(bytes)
  def nextLong(): Long = { seed += 1; seed }
}

object Serial extends GeneratorCompanion[Serial, Long] {
  def randomSeed(): Long = System.nanoTime
  def fromBytes(bytes: Array[Byte]): Serial = new Serial(Pack.longFromBytes(bytes))
  def fromSeed(seed: Long): Serial = new Serial(seed)
  def fromTime(time: Long = System.nanoTime): Serial = new Serial(time)
}
