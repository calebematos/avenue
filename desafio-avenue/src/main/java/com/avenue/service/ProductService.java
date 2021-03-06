package com.avenue.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avenue.DTO.ProductResumed;
import com.avenue.model.Image;
import com.avenue.model.Product;
import com.avenue.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product createNewProduct(Product product) {
		Product parentProduct = productRepository.save(product);
		if (!product.getParentProducts().isEmpty()) {
			for (Product p : product.getParentProducts()) {
				p.setParentProduct(parentProduct);
				productRepository.save(p);
			}
		}
		return parentProduct;
	}

	public Product updateProduct(Long productId, Product product) {
		Product productSaved = productRepository.findById(productId);
		BeanUtils.copyProperties(product, productSaved, "id");
		return productRepository.save(productSaved);
	}

	public void remove(Long productId) {
		Product product = productRepository.findById(productId);
		if (product != null)
			productRepository.delete(productId);
	}

	public List<ProductResumed> getOnlyProducts() {
		List<Product> allProducts = productRepository.findAll();
		List<ProductResumed> productsResumed = new ArrayList<>();

		allProducts.forEach(p -> {
			ProductResumed prod = new ProductResumed(p.getName(), p.getDescription());
			productsResumed.add(prod);
		});

		return productsResumed;
	}

	public ProductResumed getOnlyProductById(Long productId) {
		Product product = productRepository.findById(productId);
		ProductResumed prod = null;
		if (product != null) {
			prod = new ProductResumed(product.getName(), product.getDescription());
		}
		return prod;
	}

	public List<Product> getAllProducts() {
		return productRepository.findProductsWithoutChild();
	}

	public Product getProductById(Long productId) {
		return productRepository.findById(productId);
	}

	public List<Product> getChildFromProductId(Long productId) {
		Product product = productRepository.findById(productId);
		return productRepository.findByParentProduct(product);
	}

	public List<Image> getImagesFromProductId(Long productId) {
		Product product = productRepository.findById(productId);
		if (product != null && !product.getImages().isEmpty()) {
			return product.getImages();
		}
		return null;
	}

}
