package com.nowjoo.nowjiu.goods;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nowjoo.nowjiu.goods.domain.Goods;
import com.nowjoo.nowjiu.goods.serviece.GoodsService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class GoodsRestController {
	
	private GoodsService goodsService;
	
	public GoodsRestController(
			GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	@PostMapping("/goods-add")
	public Map<String, String> addGoods(
			@RequestParam("name") String name
			, @RequestParam("price") int price
			, @RequestParam("brand") String brand
			, @RequestParam("category") String category
			, @RequestParam("description") String description
			, @RequestParam("file") MultipartFile file
			, HttpSession session
			){
		int userId = (Integer)session.getAttribute("userId");
		
		Goods goods = goodsService.insertGoods(userId, name, price, brand, category, description, file);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if (goods != null) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	
}
