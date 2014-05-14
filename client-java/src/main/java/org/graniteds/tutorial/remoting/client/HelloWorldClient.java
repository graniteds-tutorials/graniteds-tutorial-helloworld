package org.graniteds.tutorial.remoting.client;

import org.granite.client.tide.Context;
import org.granite.client.tide.impl.ComponentImpl;
import org.granite.client.tide.impl.SimpleContextManager;
import org.granite.client.tide.server.Component;
import org.granite.client.tide.server.ServerSession;
import org.granite.client.tide.server.TideFaultEvent;
import org.granite.client.tide.server.TideResponder;
import org.granite.client.tide.server.TideResultEvent;


public class HelloWorldClient {

    public static void main(String[] args) throws Exception {
    	if (args == null || args.length == 0) {
    		System.out.println("Please add an argument to call the service");
    		return;
    	}
    	
        // tag::client-app[]
        Context context = new SimpleContextManager().getContext(); // <1>
        ServerSession serverSession = context.set(
                new ServerSession("/helloworld", "localhost", 8080)); // <2>
        Component helloWorldService = context.set("helloWorldService",
                new ComponentImpl(serverSession)); // <3>
        
        serverSession.start();      //  <4>
        
        helloWorldService.call("hello", args[0], new TideResponder<String>() { // <5>
            @Override
            public void result(TideResultEvent<String> event) { // <6>
                System.out.println("-----------------------------");
                System.out.println(event.getResult());
                System.out.println("-----------------------------");
            }

            @Override
            public void fault(TideFaultEvent event) { // <7>
                System.err.println("-----------------------------");
                System.err.println("Fault: " + event.getFault().getCode() + ": "
                        + event.getFault().getFaultDescription());
                System.err.println("-----------------------------");
            }
        }).get(); // <8>

        serverSession.stop(); // <9>
        // end::client-app[]
    }
}
