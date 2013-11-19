package org.graniteds.tutorial.remoting.server;

import org.granite.config.servlet3.ServerFilter;
import org.granite.tide.cdi.CDIConfigProvider;

// tag::server-filter[]
@ServerFilter(configProviderClass=CDIConfigProvider.class)
public class HelloWorldApplication {
}
// end::server-filter[]
