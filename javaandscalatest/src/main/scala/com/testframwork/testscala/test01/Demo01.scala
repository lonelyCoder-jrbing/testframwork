package com.testframwork.testscala.test01

abstract class Expr

case class Var(name: String) extends Expr

case class Number(num: Double) extends Expr

case class UnOp(operator: String, arg: Expr) extends Expr

case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

object Demo01 {

  def main(args: Array[String]): Unit = {
    val ints = describe(5)
    println("ints =>", ints)
    val op = BinOp("+", Number(1.0), Var("x"))
    val result = simpliftop(UnOp("-", UnOp("-", Number(2.0))))
    println(result)
    1 match {
      case v => "the varible is " + v
    }

    println("StringLength:  ", generalSize("abc"))

    println("mapSize:       ", generalSize(Map(1 -> 'a', 2 -> 'b')))

    println("anything else: ", generalSize(123))
  }

  def describe01(e: Expr): String = e match {
    case Number(_) => "a number"
    case Var(_) => "a variable"

  }


  def describe(x: Any): Any = x match {
    case 5 => "five"
    case true => "truth"
    case spark => "the future"
    case Nil => "the empty list"
    case _ => "something else"
  }

  def simpliftop(expr: Expr): Expr = expr match {

    case UnOp("-", UnOp("-", e)) => e
    case BinOp("+", e, Number(0)) => e
    case BinOp("*", e, Number(1)) => e
    case _ => expr
  }

  def generalSize(x: Any) = x match {
    case s: String => s.length
    case m: Map[_, _] => m.size
    case _ => -1
  }


}




