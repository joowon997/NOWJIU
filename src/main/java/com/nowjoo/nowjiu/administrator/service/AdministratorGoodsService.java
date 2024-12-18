package com.nowjoo.nowjiu.administrator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.administrator.dto.AddGoodsdDto;
import com.nowjoo.nowjiu.administrator.dto.GoodsDto;
import com.nowjoo.nowjiu.brand.domain.Brand;
import com.nowjoo.nowjiu.brand.domain.BrandImage;
import com.nowjoo.nowjiu.brand.dto.BrandDto;
import com.nowjoo.nowjiu.brand.service.BrandService;
import com.nowjoo.nowjiu.category.domian.Category;
import com.nowjoo.nowjiu.category.service.CategoryService;
import com.nowjoo.nowjiu.goods.domain.Goods;
import com.nowjoo.nowjiu.goods.serviece.GoodsService;

@Service
public class AdministratorGoodsService {

	private BrandService brandService;
	private CategoryService categoryService;
	private GoodsService goodsService;
	
	public AdministratorGoodsService(
			BrandService brandService
			, CategoryService categoryService
			, GoodsService goodsService
			) {
		this.brandService = brandService;
		this.categoryService = categoryService;
		this.goodsService = goodsService;
	}
	// 브랜드 리스트
	public List<BrandDto> getBrandDtos(){
		List<Brand> brandList = brandService.allBrand();
		
		List<BrandDto> brandDtosList = new ArrayList<>();
		for(Brand brand : brandList) {
			int brandId = brand.getId();
			
			BrandImage brandImage = brandService.getBrandIamge(brandId);
			String Image = null;
			if (brandImage != null) {
				Image = brandImage.getImage();
			}
			
			BrandDto brandDto = BrandDto.builder()
										.brandId(brandId)
										.brandName(brand.getName())
										.brandImage(Image)
										.build();
			brandDtosList.add(brandDto);
		}
		return brandDtosList;
	}
	
	
	// 상품 리스트
	public List<GoodsDto> getGoodsList(){
		List<Goods> goodsList = goodsService.getGoodsList();
		
		List<GoodsDto> goodsListDtoList = new ArrayList<>();
		for(Goods goods:goodsList) {
			
			GoodsDto goodsListDto = GoodsDto.builder()
											.goodsId(goods.getId())
											.mainImage(goods.getImage())
											.name(goods.getName())
											.price(goods.getPrice())
											.build();
			goodsListDtoList.add(goodsListDto);
		}
		return goodsListDtoList;
	}
	
	// 상품 등록 옵션
	public AddGoodsdDto brandAndCategoryOption() {
		
		List<Category> categories = categoryService.allcategory();
		List<Brand> brands = brandService.allBrand();
		
		AddGoodsdDto addGoodsdDto = AddGoodsdDto.builder()
										.categoryList(categories)
										.brandList(brands)
										.build();
		return addGoodsdDto;
	}
	
}
