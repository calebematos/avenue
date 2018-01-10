package com.avenue;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.avenue.resource.ProductResource;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(ProductResource.class);
	}
}