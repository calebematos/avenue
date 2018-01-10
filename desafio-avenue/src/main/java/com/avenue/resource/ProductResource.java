package com.avenue.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

import com.avenue.DTO.ProductResumed;
import com.avenue.event.NewResourceEvent;
import com.avenue.model.Image;
import com.avenue.model.Product;
import com.avenue.service.ProductService;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

	@Autowired
	private ProductService productService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@POST
	@Transactional
	public Response createProduct(Product product, @Context HttpServletResponse response) {
		Product newProduct = productService.createNewProduct(product);
		publisher.publishEvent(new NewResourceEvent(this, response, newProduct.getId()));
		return Response.status(Status.CREATED).build();
	}

	@PUT
	@Transactional
	@Path("/{id}")
	public Response updateProduct(@PathParam("id") Long productId, Product product) {
		Product updatedProduct = productService.updateProduct(productId, product);
		return Response.ok(updatedProduct).build();
	}

	@DELETE
	@Transactional
	@Path("/{id}")
	public Response deleteProduct(@PathParam("id") Long productId) {
		productService.remove(productId);
		return Response.noContent().build();
	}

	@GET
	@Path("/only")
	public Response getOnlyProducts() {
		List<ProductResumed> products = productService.getOnlyProducts();
		return Response.ok(products).build();
	}

	@GET
	@Path("/only/{id}")
	public Response getOnlyProductsById(@PathParam("id") Long productId) {
		ProductResumed product = productService.getOnlyProductById(productId);
		if (product != null)
			return Response.ok(product).build();
		return Response.noContent().build();
	}

	@GET
	public Response getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return Response.ok(products).build();
	}

	@GET
	@Path("/{id}")
	public Response getProductsById(@PathParam("id") Long productId) {
		Product product = productService.getProductById(productId);
		if (product != null)
			return Response.ok(product).build();
		return Response.noContent().build();
	}

	@GET
	@Path("/childs/{id}")
	public Response getChildFromProductId(@PathParam("id") Long productId) {
		List<Product> products = productService.getChildFromProductId(productId);
		if (products != null)
			return Response.ok(products).build();
		return Response.noContent().build();
	}

	@GET
	@Path("/images/{id}")
	public Response getImagesFromProductId(@PathParam("id") Long productId) {
		List<Image> images = productService.getImagesFromProductId(productId);
		if (images != null)
			return Response.ok(images).build();
		return Response.noContent().build();
	}

}
