package org.graniteds.tutorial.remoting.server;

import org.granite.messaging.service.annotations.RemoteDestination;

// tag::remote-destination[]
@RemoteDestination
public interface HelloWorldService {

    public String hello(String name);
}
// end::remote-destination[]
