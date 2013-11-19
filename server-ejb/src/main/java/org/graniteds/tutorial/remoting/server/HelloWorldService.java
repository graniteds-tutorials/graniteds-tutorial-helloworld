package org.graniteds.tutorial.remoting.server;

import org.granite.messaging.service.annotations.RemoteDestination;

import javax.ejb.Local;

// tag::remote-destination[]
@RemoteDestination
@Local
public interface HelloWorldService {

    public String hello(String name);
}
// end::remote-destination[]
