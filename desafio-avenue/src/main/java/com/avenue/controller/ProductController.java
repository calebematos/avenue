package com.avenue.controller;

import java.net.URI;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avenue.model.Product;
import com.avenue.service.ProductService;

@Component
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {

	@Autowired
	ProductService productService;

	@POST
	@Transactional
	public Response createProduct(Product product) {
		productService.createNewProduct(product);
		// create event
		URI uri = URI.create("/products/" + product.getId());
		return Response.created(uri).build();
	}

	@PUT
	@Transactional
	public Response updateProduct(Product product) {
		Product updatedProduct = productService.updateProduct(product);
		return Response.ok(updatedProduct).build();
	}

	@DELETE
	@Transactional
	public Response deleteProduct(Long productId) {
		productService.remove(productId);
		return Response.noContent().build();
	}
}
