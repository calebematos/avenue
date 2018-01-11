package com.avenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avenue.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

	public Image findById(Long id);

}
