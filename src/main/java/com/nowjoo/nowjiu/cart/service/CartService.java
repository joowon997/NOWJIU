package com.nowjoo.nowjiu.cart.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.cart.domain.Cart;
import com.nowjoo.nowjiu.cart.dto.CartDto;
import com.nowjoo.nowjiu.cart.respository.CartRespository;
import com.nowjoo.nowjiu.goods.domain.Goods;
import com.nowjoo.nowjiu.goods.serviece.GoodsService;

@Service
public class CartService {
	
	private CartRespository cartRespository;
	private GoodsService goodsService;
	
	public CartService(
			CartRespository cartRespository
			,GoodsService goodsService) {
		this.cartRespository = cartRespository;
		this.goodsService = goodsService;
	}
	
	// 회원 장바구니 조회
	public CartDto getUserCart(int userId) {
		List<Cart> cartList = cartRespository.findByUserId(userId);
		
		List<Goods> goodsList = new ArrayList<>();
		for(Cart cart : cartList){
			int goodId = cart.getGoodsId();
			Goods goods = goodsService.getGoods(goodId);
			
			goodsList.add(goods);
		}
		
		CartDto cartDto = CartDto.builder()
								.userId(userId)
								.goodsList(goodsList)
								.build();
		return cartDto;
	}
	
	// 장바구니 추가
	public Cart insertCart(int goodId, int userId) {
		
		Optional<Cart> optionalCart = cartRespository.findByGoodsIdAndUserId(goodId, userId);
		Cart cart = optionalCart.orElse(null);
		
		if(cart == null) {
			cart = Cart.builder()
						.goodsId(goodId)
						.userId(userId)
						.build();
			
			return cartRespository.save(cart);
		}else {
			
			return cart;
		}
	}
}
