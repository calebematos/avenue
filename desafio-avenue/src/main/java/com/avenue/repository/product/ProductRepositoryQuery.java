package com.avenue.repository.product;

import java.util.List;

import com.avenue.model.Product;

public interface ProductRepositoryQuery {
	
	public List<Product> findProductsWithoutChild();

}
