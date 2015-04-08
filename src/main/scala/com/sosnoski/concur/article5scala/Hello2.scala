package com.sosnoski.concur.article5scala

import akka.actor._
import akka.util._

/** Simple hello from an actor with state. */
object Hello2 extends App {
  
  case class Greeting(greet: String)
  case class Greet(name: String)
  
  val system = ActorSystem("actor-demo-scala")
  val hello = system.actorOf(Props[Hello])
  hello ! Greeting("Hello")
  hello ! Greet("Bob")
  hello ! Greet("Alice")
  hello ! Greeting("Hola")
  hello ! Greet("Alice")
  hello ! Greet("Bob")
  Thread sleep 1000
  system shutdown
  
  class Hello extends Actor {
    var greeting = ""
    def receive = {
      case Greeting(greet) => greeting = greet
      case Greet(name) => println(s"$greeting $name")
    }
  }
}