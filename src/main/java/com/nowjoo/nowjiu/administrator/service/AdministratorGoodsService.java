package com.nowjoo.nowjiu.administrator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.administrator.dto.AddGoodsdDto;
import com.nowjoo.nowjiu.brand.domain.Brand;
import com.nowjoo.nowjiu.brand.service.BrandService;
import com.nowjoo.nowjiu.category.domian.Category;
import com.nowjoo.nowjiu.category.service.CategoryService;

@Service
public class AdministratorGoodsService {

	private BrandService brandService;
	private CategoryService categoryService;
	
	public AdministratorGoodsService(
			BrandService brandService
			, CategoryService categoryService
			) {
		this.brandService = brandService;
		this.categoryService = categoryService;
	}
	// 
	public AddGoodsdDto brandAndCategoryOption() {
		
		List<Category> categories = categoryService.allcategory();
		List<Brand> brands = brandService.allcategory();
		
		AddGoodsdDto addGoodsdDto = AddGoodsdDto.builder()
										.categoryList(categories)
										.brandList(brands)
										.build();
		return addGoodsdDto;
	}
	
}
