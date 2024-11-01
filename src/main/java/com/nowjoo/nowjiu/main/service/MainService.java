package com.nowjoo.nowjiu.main.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.brand.domain.Brand;
import com.nowjoo.nowjiu.brand.service.BrandService;
import com.nowjoo.nowjiu.main.dto.MainDto;

@Service
public class MainService {

	private BrandService brandService;
	
	public MainService(
			BrandService brandService
			) {
		this.brandService = brandService;
	}
	
	public MainDto mainview() {
		List<Brand> brandList = brandService.getBrand(6);
		
		MainDto mainDto = MainDto.builder()
								.brandList(brandList)
								.build();
		return mainDto;
	}
	
	
	
}
