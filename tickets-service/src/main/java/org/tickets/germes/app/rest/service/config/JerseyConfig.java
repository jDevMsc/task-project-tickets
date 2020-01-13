package org.tickets.germes.app.rest.service.config;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
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

        register(ApiListingResource.class);
        register(SwaggerSerializers.class);
    }

    /**
     * Config for Swagger
     */
    private void initBeanConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[] { "http" });
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/");
        beanConfig.setResourcePackage("org.tickets.germes.app.rest.service");
        beanConfig.setScan(true);
    }
}

