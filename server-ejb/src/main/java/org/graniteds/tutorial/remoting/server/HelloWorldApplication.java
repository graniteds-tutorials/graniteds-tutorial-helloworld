package org.graniteds.tutorial.remoting.server;

import org.granite.config.servlet3.ServerFilter;
import org.granite.tide.ejb.EjbConfigProvider;

// tag::server-filter[]
@ServerFilter(configProviderClass=EjbConfigProvider.class)
public class HelloWorldApplication {
}
// end::server-filter[]
