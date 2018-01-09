package com.avenue.DTO;

import com.avenue.model.Image;
import com.avenue.model.Product;

public class ProductsDTO {

	private Product product;
	private Product productChild;
	private Image image;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProductChild() {
		return productChild;
	}

	public void setProductChild(Product productChild) {
		this.productChild = productChild;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
