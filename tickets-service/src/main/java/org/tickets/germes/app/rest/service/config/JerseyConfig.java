package org.tickets.germes.app.rest.service.config;

import javax.ws.rs.ApplicationPath;

import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
/**
 * REST web-service configuration for Jersey
 */
public class JerseyConfig extends Application {
}

