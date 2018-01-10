package com.avenue.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avenue.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

	public Image findById(Long id);

	public Set<Image> findByProduct(Long productId);
}
