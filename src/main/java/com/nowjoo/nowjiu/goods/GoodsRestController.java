package com.nowjoo.nowjiu.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	// 상품삭제
	@DeleteMapping("/goods-delete")
	public Map<String, String> delectPost(
			@RequestParam("goodsId") int postId
			){
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(goodsService.deleteGoods(postId)) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
	//상품 추가
	@PostMapping("/goods-add")
	public Map<String, String> addGoods(
			@RequestParam("name") String name
			, @RequestParam("price") int price
			, @RequestParam("brand") String brand
			, @RequestParam("category") String category
			, @RequestParam("description") String description
			, @RequestParam("mainImage") MultipartFile mainImage
			, @RequestParam("InfoImage") List<MultipartFile> infoImage
			, HttpSession session
			){
		int userId = (Integer)session.getAttribute("userId");
		
		boolean addgoods = goodsService.insertGoods(userId, name, price, brand, category, description, mainImage, infoImage);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if (addgoods) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	
}
