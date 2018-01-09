package com.avenue.controller;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avenue.event.NewRecourseEvent;
import com.avenue.model.Product;
import com.avenue.service.ProductService;

@Component
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@POST
	@Transactional
	public Response createProduct(Product product, HttpServletResponse response) {
		Product newProduct = productService.createNewProduct(product);
		publisher.publishEvent(new NewRecourseEvent(this, response, newProduct.getId()));
		return Response.status(Status.CREATED).build();
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
