package org.graniteds.tutorial.remoting.server;

import javax.ejb.Stateless;

// tag::service-impl[]
@Stateless
public class HelloWorldServiceBean implements HelloWorldService {

    public String hello(String name) {
        return "Hello " + name;
    }
}
// end::service-impl[]
