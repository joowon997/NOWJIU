package com.nowjoo.nowjiu.brand.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowjiu.brand.domain.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>{

	public Optional<Brand> findByName(String name);
}
