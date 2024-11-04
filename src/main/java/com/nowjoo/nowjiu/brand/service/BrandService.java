package com.nowjoo.nowjiu.brand.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nowjoo.nowjiu.brand.domain.Brand;
import com.nowjoo.nowjiu.brand.domain.BrandImage;
import com.nowjoo.nowjiu.brand.dto.BrandDto;
import com.nowjoo.nowjiu.brand.repository.BrandImageRespository;
import com.nowjoo.nowjiu.brand.repository.BrandRepository;
import com.nowjoo.nowjiu.category.domian.Category;
import com.nowjoo.nowjiu.common.FileManager;

@Service
public class BrandService {

	private BrandRepository brandRepository;
	private BrandImageRespository brandImageRespository;
	
	public BrandService(
			BrandRepository brandRepository
			, BrandImageRespository brandImageRespository
			) {
		this.brandRepository = brandRepository;
		this.brandImageRespository = brandImageRespository;
	}
	//모든 브랜드 조회
	public List<Brand> allBrand(){
		return brandRepository.findAll();
	}

	//모든 브랜드 조회
	public BrandImage getBrandIamge(int brandId){
		Optional<BrandImage> optionalbrand = brandImageRespository.findByBrandId(brandId);
		BrandImage brandImage = optionalbrand.orElse(null);
		
		return brandImage;
	}

	//모든 브랜드 조회
	public List<BrandDto> getBrand(int count){
		//브랜드 랜덤배치
		List<Brand> allBrand = brandRepository.findAll();
		Collections.shuffle(allBrand);
		
		List<BrandDto> brandDtoList = new ArrayList<>();
		List<BrandDto> brandDtos = new ArrayList<>();
		for(Brand brand : allBrand) {
			int brandId = brand.getId();
			
			BrandImage brandIdImage = getBrandIamge(brandId);
			String image = null;
			if (brandIdImage != null) {
				image = brandIdImage.getImage();
			}
			
			BrandDto brandDto = BrandDto.builder()
										.brandId(brandId)
										.brandName(brand.getName())
										.brandImage(image)
										.build();
			brandDtoList.add(brandDto);
		}
		
		if(brandDtoList.size() > count) {
			for (int i = 0; i < count; i++) {
				brandDtos.add(brandDtoList.get(i));
			};
		}else {
			for (int i = 0; i < brandDtoList.size(); i++) {
				brandDtos.add(brandDtoList.get(i));
			};
		}
		return brandDtos;
	}
	
	
	// 브랜드 추가
	public Brand addBrand(String name){
		
		Brand brand = Brand.builder()
						.name(name)
						.build();
		return brandRepository.save(brand);
	}

	// 브랜드 이미지추가
	public BrandImage addBrandImage(MultipartFile image, int brandId){
		// 이미지 경로 설정
		String urlPath = FileManager.saveFile(brandId, image);
		
		BrandImage brandImage = BrandImage.builder()
										.brandId(brandId)
										.image(urlPath)
										.build();
		
		return brandImageRespository.save(brandImage);
	}
	
	// 입력브랜드 조회
	public Brand getBrand(String name) {
		Optional<Brand> optionalBrand = brandRepository.findByName(name);
		Brand brand = optionalBrand.orElse(null);
		
		return brand;
	}
}
