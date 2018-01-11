package com.avenue.repository.product;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.avenue.model.Product;

public class ProductRepositoryImpl implements ProductRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Product> findProductsWithoutChild() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		 CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);
		
		criteria.where(builder.isNull(root.get("parentProduct")));
		
		TypedQuery<Product> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

}
