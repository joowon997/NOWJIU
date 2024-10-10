package com.nowjoo.nowjiu.goods;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nowjiu")
public class GoodsController {

	@GetMapping("/goods-list")
	public String goodsList(){
		return "goods/list";
	}

	@GetMapping("/goods-info")
	public String goodsInfo(){
		return "goods/info";
	}

	@GetMapping("/cart")
	public String cart(){
		return "goods/cart";
	}
}
