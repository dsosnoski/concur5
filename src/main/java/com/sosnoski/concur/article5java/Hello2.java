package com.sosnoski.concur.article5java;

import akka.actor.*;

/** Simple hello from an actor with state. */
public class Hello2 {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("actor-demo-java");
        ActorRef hello = system.actorOf(Props.create(Hello.class));
        hello.tell(new Greeting("Hello"), ActorRef.noSender());
        hello.tell(new Greet("Bob"), ActorRef.noSender());
        hello.tell(new Greet("Alice"), ActorRef.noSender());
        hello.tell(new Greeting("Hola"), ActorRef.noSender());
        hello.tell(new Greet("Alice"), ActorRef.noSender());
        hello.tell(new Greet("Bob"), ActorRef.noSender());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { /* ignore */ }
        system.shutdown();
    }
    
    private static class Greeting {
        public final String greeting;
        
        public Greeting(String text) {
            greeting = text;
        }
    }
    
    private static class Greet {
        public final String name;
        
        public Greet(String text) {
            name = text;
        }
    }

    private static class Hello extends UntypedActor {
        private String greeting;
        
        public void onReceive(Object message) throws Exception {
            if (message instanceof Greeting) {
                greeting = ((Greeting)message).greeting;
            } else if (message instanceof Greet) {
                System.out.println(greeting + " " + ((Greet)message).name);
            }
        }
    }
}
