package com.nowjoo.nowjiu.brand.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nowjoo.nowjiu.brand.domain.Brand;
import com.nowjoo.nowjiu.brand.repository.BrandRepository;
import com.nowjoo.nowjiu.category.domian.Category;
import com.nowjoo.nowjiu.common.FileManager;

@Service
public class BrandService {

	private BrandRepository brandRepository;
	
	public BrandService(
			BrandRepository brandRepository
			) {
		this.brandRepository = brandRepository;
	}
	//모든 브랜드 조회
	public List<Brand> allBrand(){
		return brandRepository.findAll();
	}

	//모든 브랜드 조회
	public List<Brand> getBrand(int count){
		List<Brand> allBrand = brandRepository.findAll();
		Collections.shuffle(allBrand);
		List<Brand> allBrands = new ArrayList<>();
		if(allBrand.size() > count) {
			for (int i = 0; i < count; i++) {
				allBrands.add(allBrand.get(i));
			};
		}
		return allBrands;
	}
	
	
	// 브랜드 추가
	public Brand addBrand(String name){
		
		Brand brand = Brand.builder()
						.name(name)
						.build();
		return brandRepository.save(brand);
	}
	
	// 입력브랜드 조회
	public Brand getBrand(String name) {
		Optional<Brand> optionalBrand = brandRepository.findByName(name);
		Brand brand = optionalBrand.orElse(null);
		
		return brand;
	}
}
