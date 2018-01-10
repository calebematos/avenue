package com.avenue;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.avenue.resource.ImageResource;
import com.avenue.resource.ProductResource;

@Component
@ApplicationPath("/avenue")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(ProductResource.class);
		register(ImageResource.class);
	}
}