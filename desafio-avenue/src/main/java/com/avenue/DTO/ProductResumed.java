package com.avenue.DTO;

public class ProductResumed {

	private String name;

	private String description;

	public ProductResumed() {
		// Auto-generated constructor stub
	}

	public ProductResumed(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
