package com.testframwork.testscala.implicitTest

class Fraction(n: Int, d: Int) {
  private val den = d;
  private val num = n;

  def *(other: Fraction) = Fraction(other.num * this.num, other.den * this.den)

  override def toString() = s"$num / $den"
}

object Fraction {
  // implicit隐转 方法名无关可以随意改，自动调用
  implicit def int2Fraction(n: Int) = Fraction(n, 1)

  def apply(n: Int, d: Int) = {
    new Fraction(n, d)
  }

  def unapply(frac: Fraction) = if (frac.den == 0) None else Some((frac.num, frac.den))
}

object Testfrac extends App {
  val result = 3 * Fraction(4, 5)
  println("result：  ", result)
}



