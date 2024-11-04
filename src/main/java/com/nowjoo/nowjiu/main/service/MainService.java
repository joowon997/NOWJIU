package com.nowjoo.nowjiu.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.brand.domain.Brand;
import com.nowjoo.nowjiu.brand.dto.BrandDto;
import com.nowjoo.nowjiu.brand.service.BrandService;
import com.nowjoo.nowjiu.goods.domain.Goods;
import com.nowjoo.nowjiu.goods.serviece.GoodsService;
import com.nowjoo.nowjiu.main.dto.MainDto;

@Service
public class MainService {

	private BrandService brandService;
	private GoodsService goodsService;
	
	public MainService(
			BrandService brandService
			, GoodsService goodsService
			) {
		this.brandService = brandService;
		this.goodsService = goodsService;
	}
	
	public MainDto mainview() {
		List<BrandDto> brandList = brandService.getBrand(6);
		List<Goods> newGoodsList = goodsService.getNewGoodsList(4);
		List<Goods> giGoodsList = goodsService.getMainGoodsList("도복");
		List<Goods> noGiGoodsList = goodsService.getMainGoodsList("노기");
		List<Goods> clothesList = goodsService.getMainGoodsList("의류");
		
		MainDto mainDto = MainDto.builder()
								.brandList(brandList)
								.newList(newGoodsList)
								.giList(giGoodsList)
								.noGiList(noGiGoodsList)
								.clothesList(clothesList)
								.build();
		return mainDto;
	}
	
	
	
}
