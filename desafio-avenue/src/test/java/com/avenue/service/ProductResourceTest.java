package com.avenue.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.junit.Assert.*;

import io.restassured.RestAssured;
import com.avenue.DTO.ProductResumed;
import com.avenue.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ProductResourceTest {

	private Product product;
	private Product childProduct;

	@LocalServerPort
	private Integer port;

	@Before
	public void setUp() {
		RestAssured.port = port;

		product = new Product();
		product.setName("Console");
		product.setDescription("Console players");

		childProduct = new Product();
		childProduct.setName("PS4");
		childProduct.setDescription("PS4 console");
		childProduct.setParentProduct(product);
	}

	@Test
	public void shouldCreateProduct() throws Exception {
		insertProductExpecting201(product);
	}

	@Test
	public void shouldUpdateProduct() throws Exception {
		String productUri = insertProductExpecting201(product);
		Product product = getProduct(productUri);
		product.setName("Consoles");

		given().body(product).and().header("Content-Type", "application/json").expect().statusCode(200).when()
				.put("/avenue/products/" + product.getId()).andReturn().as(Product.class);
	}

	@Test
	public void shouldDeleteProduct() throws Exception {
		String productUri = insertProductExpecting201(product);
		Product product = getProduct(productUri);

		given().body(product).and().header("Content-Type", "application/json").expect().statusCode(204).when()
				.delete("/avenue/products/" + product.getId());
	}

	@Test
	public void shouldGetAllProductsWithoutRelations() throws Exception {
		insertProductExpecting201(product);
		Product[] products = get("/avenue/products/only").andReturn().as(Product[].class);

		for (Product product : products) {
			assertNull(product.getParentProduct());
			assertNull(product.getImages());
		}
	}

	private String insertProductExpecting201(Product product) throws JsonProcessingException {
		String json = new ObjectMapper().writerWithView(ProductResumed.class).writeValueAsString(product);

		return given().body(json).and().header("Content-Type", "application/json").expect().statusCode(201).when()
				.post("/avenue/products").andReturn().header("Location");
	}

	private Product getProduct(String productUri) {
		return given().baseUri(productUri).and().expect().statusCode(200).when().get().andReturn().as(Product.class);
	}

}
