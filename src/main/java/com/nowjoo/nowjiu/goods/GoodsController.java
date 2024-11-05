package com.nowjoo.nowjiu.goods;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowjoo.nowjiu.goods.dto.GoodsInfoDto;
import com.nowjoo.nowjiu.goods.dto.GoodsListDto;
import com.nowjoo.nowjiu.goods.serviece.GoodsService;

@Controller
@RequestMapping("/nowjiu")
public class GoodsController {

	private GoodsService goodsService;
	
	public GoodsController(
			GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	@GetMapping("/goods")
	public String goodsList(
			@RequestParam("category") String category
			, Model model){
		
		GoodsListDto goodsList = goodsService.getCategoryGoodsList(category);
		
		model.addAttribute("goods", goodsList);
		
		return "goods/list";
	}

	@GetMapping("/goods-search")
	public String searchGoodsList(
			@RequestParam("search") String search
			, Model model){
		
		GoodsListDto goodsList = goodsService.getSearchGoodsList(search);
		
		model.addAttribute("goods", goodsList);
		
		return "goods/list";
	}

	@GetMapping("/brand-goods")
	public String brandGoodsList(
			@RequestParam("brandId") int brandId
			, Model model){
		
		GoodsListDto goodsList = goodsService.getBrandGoodsList(brandId);
		
		model.addAttribute("goods", goodsList);
		
		return "goods/list";
	}

	@GetMapping("/goods-info")
	public String goodsInfo(
			@RequestParam("goodsId") int goodsId
			, Model model){
		GoodsInfoDto goods = goodsService.getGoodsInfo(goodsId);
		
		model.addAttribute("goods", goods);
		
		return "goods/info";
	}

}
