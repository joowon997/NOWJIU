package com.nowjoo.nowjiu.goods.serviece;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nowjoo.nowjiu.administrator.dto.GoodsDto;
import com.nowjoo.nowjiu.brand.domain.Brand;
import com.nowjoo.nowjiu.brand.service.BrandService;
import com.nowjoo.nowjiu.category.domian.Category;
import com.nowjoo.nowjiu.category.service.CategoryService;
import com.nowjoo.nowjiu.common.FileManager;
import com.nowjoo.nowjiu.goods.domain.Goods;
import com.nowjoo.nowjiu.goods.domain.GoodsImage;
import com.nowjoo.nowjiu.goods.dto.GoodsInfoDto;
import com.nowjoo.nowjiu.goods.dto.GoodsListDto;
import com.nowjoo.nowjiu.goods.respository.GoodsImageRepository;
import com.nowjoo.nowjiu.goods.respository.GoodsRepository;
import com.nowjoo.nowjiu.inventory.service.InventoryService;

@Service
public class GoodsService {

	private GoodsRepository goodsRepository;
	private BrandService brandService;
	private	CategoryService categoryService;
	private GoodsImageRepository goodsImageRepository;
	
	public GoodsService(
			GoodsRepository goodsRepository
			, BrandService brandService
			, CategoryService categoryService
			, InventoryService inventoryService
			, GoodsImageRepository goodsImageRepository
			) {
		this.goodsRepository = goodsRepository;
		this.brandService = brandService;
		this.categoryService = categoryService;
		this.goodsImageRepository = goodsImageRepository;
	}
	
	// 모든 상품정보 조회
	public List<Goods> getGoodsList(){
		return goodsRepository.findAll();
	}
	
	// 하나정보 조회
	public Goods getGoods(int goodsId){
		Optional<Goods> optionalGoods = goodsRepository.findById(goodsId);
		Goods goods = optionalGoods.orElse(null);
		
		return goods;
	}
	// 상품 id 조회
	public int getGoodsID(String goodsName){
		Optional<Goods> optionalGoods = goodsRepository.findByName(goodsName);
		Goods goods = optionalGoods.orElse(null);
		
		return goods.getId();
	}

	// 상품 상세정보
	public GoodsInfoDto getGoodsInfo(int goodsId){
		// 상품 정보
		Optional<Goods> optionalGoods = goodsRepository.findById(goodsId);
		Goods goods = optionalGoods.orElse(null);
		// 상세 이미지
		List<GoodsImage> goodsImageList = goodsImageRepository.findByGoodsId(goodsId);
		
		
		GoodsInfoDto goodsInfo = GoodsInfoDto.builder()
								.goodsId(goodsId)
								.name(goods.getName())
								.price(goods.getPrice())
								.description(goods.getDescription())
								.mainImage(goods.getImage())
								.infoImage(goodsImageList)
								.build();
		return goodsInfo;
	}
	
	// 카테고리 상품정보 조회
	public GoodsListDto getCategoryGoodsList(String category){
		int categoryId = categoryService.getCategory(category).getId();
		
		List<Goods> goodsList = goodsRepository.findByCategoryId(categoryId);
		int count = goodsRepository.countByCategoryId(categoryId);
		
		List<GoodsDto> goodsDtoList = new ArrayList<>();
		for(Goods goods:goodsList) {
			
			GoodsDto goodsDto = GoodsDto.builder()
											.goodsId(goods.getId())
											.mainImage(goods.getImage())
											.name(goods.getName())
											.price(goods.getPrice())
											.build();
			goodsDtoList.add(goodsDto);
		}
		
		GoodsListDto listDto = GoodsListDto.builder()
							.goodsList(goodsDtoList)
							.count(count)
							.build();
		
		return listDto;
	}
	
	// 상품 삭제
	public boolean deleteGoods(int goodsId) {
			
			Optional<Goods> optionalGoods = goodsRepository.findById(goodsId);
			Goods goods = optionalGoods.orElse(null);
			
			List<GoodsImage> imageList = goodsImageRepository.findByGoodsId(goodsId);
			
			if (goods != null) {
				for(GoodsImage info : imageList) {
					FileManager.removeFile(goods.getImage());
					goodsImageRepository.delete(info);
				}
				FileManager.removeFile(goods.getImage());
				goodsRepository.delete(goods);
				return true;
			}else {
				return false;
			}
		
		}
	
	// 상품 추가
	public boolean insertGoods(
			int userId
			, String name
			, int price
			, String brand
			, String category
			, String description
			, MultipartFile mainImage
			, List<MultipartFile> infoImage
			) {
		// 이미지 경로 설정
		String urlPath = FileManager.saveFile(userId, mainImage);
		
		// 브랜드 있는지 확인후 저장
		Brand brandEntity = brandService.getBrand(brand);
		int brandId = 0;
		if (brandEntity != null) {
			brandId = brandEntity.getId();
		}else {
			brandId = brandService.addBrand(brand).getId();
		}

		// 카테고리가 있는지 확인후 저장
		Category categoryEntity = categoryService.getCategory(category);
		int categoryId = 0;
		if (categoryEntity != null) {
			categoryId = categoryEntity.getId();
		}else {
			categoryId = categoryService.addCategory(category).getId();
		}
		
		// 상품추가
		Goods goods = Goods.builder()
						.brandId(brandId)
						.categoryId(categoryId)
						.name(name)
						.price(price)
						.description(description)
						.image(urlPath)
						.build();
		
		goods = goodsRepository.save(goods);
		

		// 상품 상세 이미지
		GoodsImage goodsImage = new GoodsImage();
		for(MultipartFile image : infoImage) {
			
			String url = FileManager.saveFile(userId, image);
			
			goodsImage = GoodsImage.builder()
											.goodsId(goods.getId())
											.image(url)
											.build();
			
			goodsImage = goodsImageRepository.save(goodsImage);
		}
		
		if (goods != null) {
			return true;
		}else {
			return false;
		}
	}
	
}
