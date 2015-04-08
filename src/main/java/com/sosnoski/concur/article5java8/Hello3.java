package com.sosnoski.concur.article5java8;

import akka.actor.*;
import akka.japi.pf.ReceiveBuilder;

/** Hellos with properties passed to constructor, using messages to communicate. */
public class Hello3 {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("actor-demo-java8");
        ActorRef bob = system.actorOf(Greeter.props("Bob", "Howya doing"));
        ActorRef alice = system.actorOf(Greeter.props("Alice", "Happy to meet you"));
        bob.tell(new Greet(alice), ActorRef.noSender());
        alice.tell(new Greet(bob), ActorRef.noSender());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { /* ignore */ }
        system.shutdown();
    }
    
    // messages
    private static class Greet {
        public final ActorRef target;
        
        public Greet(ActorRef actor) {
            target = actor;
        }
    }
    
    private static Object AskName = new Object();
    
    private static class TellName {
        public final String name;
        
        public TellName(String name) {
            this.name = name;
        }
    }

    // actor implementation
    private static class Greeter extends AbstractActor {
        private final String myName;
        private final String greeting;
        
        Greeter(String name, String greeting) {
            myName = name;
            this.greeting = greeting;
            receive(ReceiveBuilder.
                match(Greet.class, g -> { g.target.tell(AskName, self()); }).
                matchEquals(AskName, a -> { sender().tell(new TellName(myName), self()); }).
                match(TellName.class, t -> { System.out.println(greeting + ", " + t.name); }).
                build());
        }
        
        public static Props props(String name, String greeting) {
            return Props.create(Greeter.class, name, greeting);
          }
    }
}
