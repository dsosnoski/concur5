package com.sosnoski.concur.article5scala

import akka.actor._
import akka.util._

/** Hellos with properties passed to constructor, using messages to communicate. */
object Hello3 extends App {

  import Greeter._
  val system = ActorSystem("actor-demo-scala")
  val bob = system.actorOf(props("Bob", "Howya doing"))
  val alice = system.actorOf(props("Alice", "Happy to meet you"))
  bob ! Greet(alice)
  alice ! Greet(bob)
  Thread sleep 1000
  system shutdown

  object Greeter {
    case class Greet(peer: ActorRef)
    case object AskName
    case class TellName(name: String)
    def props(name: String, greeting: String) = Props(new Greeter(name, greeting))
  }

  class Greeter(myName: String, greeting: String) extends Actor {
    import Greeter._
    def receive = {
      case Greet(peer) => peer ! AskName
      case AskName => sender ! TellName(myName)
      case TellName(name) => println(s"$greeting, $name")
    }
  }
}