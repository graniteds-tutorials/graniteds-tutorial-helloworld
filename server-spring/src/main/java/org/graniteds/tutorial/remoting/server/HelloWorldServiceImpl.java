package org.graniteds.tutorial.remoting.server;

import org.springframework.stereotype.Service;

// tag::service-impl[]
@Service("helloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService {

    public String hello(String name) {
        return "Hello " + name;
    }
}
// end::service-impl[]
