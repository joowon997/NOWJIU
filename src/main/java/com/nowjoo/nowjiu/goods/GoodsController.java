package com.nowjoo.nowjiu.goods;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowjoo.nowjiu.goods.domain.Goods;
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
			@RequestParam("categoryId") int categoryId
			, Model model){
		
		List<Goods> goodsList = goodsService.getCategoryGoodsList(categoryId);
		
		model.addAttribute("goodsList", goodsList);
		
		return "goods/list";
	}

	@GetMapping("/goods-info")
	public String goodsInfo(){
		return "goods/info";
	}

	@GetMapping("/order")
	public String order(){
		return "goods/order";
	}

	@GetMapping("/cart")
	public String cart(){
		return "goods/cart";
	}
}
