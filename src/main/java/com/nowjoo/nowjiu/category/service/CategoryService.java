package com.nowjoo.nowjiu.category.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.brand.domain.Brand;
import com.nowjoo.nowjiu.category.domian.Category;
import com.nowjoo.nowjiu.category.repository.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;
	
	public CategoryService(
			CategoryRepository categoryRepository
			) {
		this.categoryRepository = categoryRepository;
	}
	public List<Category> allcategory(){
		return categoryRepository.findAll();
	}
	
	
	// 카테고리 추가
	public Category addCategory(String name){
		
		Category category = Category.builder()
								.name(name)
								.build();
		
		return categoryRepository.save(category);
	}
	
	// 입력 카테고리 조회
	public Category getCategory(String name) {
		Optional<Category> optionalCategory = categoryRepository.findByName(name);
		Category category = optionalCategory.orElse(null);
		
		return category;
	}

}
