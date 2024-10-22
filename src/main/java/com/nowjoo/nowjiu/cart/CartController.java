package com.nowjoo.nowjiu.cart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nowjoo.nowjiu.cart.dto.CartDto;
import com.nowjoo.nowjiu.cart.service.CartService;
import com.nowjoo.nowjiu.goods.dto.GoodsInfoDto;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/nowjiu")
public class CartController {
	
	private CartService cartService;
	
	public CartController(
			CartService cartService
			){
		this.cartService = cartService;
	}
	
	@GetMapping("/cart")
	public String cart(
			Model model
			, HttpSession session){
		int userId = (Integer)session.getAttribute("userId");
		
		CartDto cartDto = cartService.getUserCart(userId);
				
		model.addAttribute("cartDto", cartDto);
		
		return "goods/cart";
	}
}
