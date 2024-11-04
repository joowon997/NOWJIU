package com.nowjoo.nowjiu.brand.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowjiu.brand.domain.BrandImage;

public interface BrandImageRespository extends JpaRepository<BrandImage, Integer>{

	public Optional<BrandImage> findByBrandId(int brandId);
}
