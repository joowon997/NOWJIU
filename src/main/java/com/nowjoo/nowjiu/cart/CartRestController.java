package com.nowjoo.nowjiu.cart;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nowjoo.nowjiu.cart.domain.Cart;
import com.nowjoo.nowjiu.cart.service.CartService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class CartRestController {

	private CartService cartService;
	
	public CartRestController(
			CartService cartService
			) {
		this.cartService = cartService;
	}
	
	@DeleteMapping("/cart-delete")
	public Map<String, String> deleteCart(
			@RequestParam ("cartId") int cartId
			){
		
		boolean isDelete = cartService.cartdelete(cartId);
		
		Map<String, String> resultMap = new HashMap<>();
		if (isDelete) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
	// 장바구니 추가
	@PostMapping("/cart-add")
	public Map<String, String> addCart(
			@RequestParam ("goodsId") int goodsId
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		Cart cart = cartService.insertCart(goodsId, userId);
		
		Map<String, String> resultMap = new HashMap<>();
		if (cart != null) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
}
