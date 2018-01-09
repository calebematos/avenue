package com.avenue;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.avenue.controller.ProductController;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(ProductController.class);
	}
}