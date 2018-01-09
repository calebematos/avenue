package com.avenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avenue.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public Product findById(Long id);
}
