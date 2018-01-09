package com.avenue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avenue.model.Product;
import com.avenue.repository.ImageRepository;
import com.avenue.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private ProductRepository productRepository;

	public Product createNewProduct(Product product) {
		return productRepository.save(product);
	}

	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	public void remove(Long productId) {
		productRepository.delete(productId);
	}

}
