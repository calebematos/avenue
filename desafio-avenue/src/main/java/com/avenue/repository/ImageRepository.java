package com.avenue.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avenue.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

	public Set<Image> findById(Long id);
}
