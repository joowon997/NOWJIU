package com.nowjoo.nowjiu.goods.serviece;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nowjoo.nowjiu.common.FileManager;
import com.nowjoo.nowjiu.goods.domain.Brand;
import com.nowjoo.nowjiu.goods.domain.Category;
import com.nowjoo.nowjiu.goods.respository.BrandRepository;
import com.nowjoo.nowjiu.goods.respository.CategoryRepository;

@Service
public class GoodsService {

	private BrandRepository brandRepository;
	private CategoryRepository categoryRepository;
	
	public GoodsService(
			BrandRepository brandRepository
			, CategoryRepository categoryRepository
			) {
		this.brandRepository = brandRepository;
		this.categoryRepository = categoryRepository;
	}
	
	// 브랜드 추가
	public Brand addBrand(int userId, String name, MultipartFile file){
		
		String urlPath = FileManager.saveFile(userId, file);
		
		Brand brand = Brand.builder()
						.name(name)
						.image(urlPath)
						.build();
		return brandRepository.save(brand);
	}

	// 카테고리 추가
	public Category addCategory(String name){
		
		Category category = Category.builder()
								.name(name)
								.build();
		
		return categoryRepository.save(category);
	}
}
