package com.avenue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avenue.model.Product;
import com.avenue.repository.product.ProductRepositoryQuery;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQuery {

	public Product findById(Long id);
	
	public List<Product> findByParentProduct(Product product);
}
