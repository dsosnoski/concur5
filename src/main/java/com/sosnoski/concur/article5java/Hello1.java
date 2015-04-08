package com.sosnoski.concur.article5java;

import akka.actor.*;

/** Simple hello from an actor in Java. */
public class Hello1 {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("actor-demo-java");
        ActorRef hello = system.actorOf(Props.create(Hello.class));
        hello.tell("Bob", ActorRef.noSender());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { /* ignore */ }
        system.shutdown();
    }

    private static class Hello extends UntypedActor {
        
        public void onReceive(Object message) throws Exception {
            if (message instanceof String) {
                System.out.println("Hello " + message);
            }
        }
    }
}
