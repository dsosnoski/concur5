package com.sosnoski.concur.article5java8;

import akka.actor.*;
import akka.japi.pf.ReceiveBuilder;

/** Simple hello from an actor in Java 8 with lambdas. */
public class Hello1 {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("actor-demo-java8");
        ActorRef hello = system.actorOf(Props.create(Hello.class));
        hello.tell("Bob", ActorRef.noSender());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { /* ignore */ }
        system.shutdown();
    }

    private static class Hello extends AbstractActor {
        
        public Hello() {
            receive(ReceiveBuilder.
                match(String.class, s -> { System.out.println("Hello " + s); }).
                build());
        }
    }
}
