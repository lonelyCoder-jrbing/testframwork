package com.testframwork.actortest

import akka.actor.{Actor, ActorSystem, Props}

/**
  * create by sumerian on 2020/5/10
  *
  * desc:
  **/
class HelloActor extends Actor {
  override def receive = {
    case "hello" => print("您好")
    case _ => print("您是？")
  }
}

object test extends App {
  val system = ActorSystem("HelloSystem")
  //默认的Actor构造函数
  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
  helloActor ! "hello"
  helloActor ! "喂"
}