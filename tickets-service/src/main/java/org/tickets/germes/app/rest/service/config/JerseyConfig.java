package org.tickets.germes.app.rest.service.config;

import javax.ws.rs.ApplicationPath;

import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;
import org.tickets.germes.app.config.ComponentFeature;

@ApplicationPath("/api")
/**
 * REST web-service configuration for Jersey
 */
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        super(ComponentFeature.class);
        packages("org.tickets.germes.app.rest");
    }
}

