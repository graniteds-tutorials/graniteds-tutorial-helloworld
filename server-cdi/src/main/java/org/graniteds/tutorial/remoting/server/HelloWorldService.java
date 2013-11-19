package org.graniteds.tutorial.remoting.server;

import org.granite.messaging.service.annotations.RemoteDestination;

import javax.inject.Named;

// tag::remote-destination[]
@RemoteDestination
@Named("helloWorldService")
public class HelloWorldService {

    public String hello(String name) {
        return "Hello " + name;
    }
}
// end::remote-destination[]
