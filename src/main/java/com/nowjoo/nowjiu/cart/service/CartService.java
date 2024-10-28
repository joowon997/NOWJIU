package com.nowjoo.nowjiu.cart.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.cart.domain.Cart;
import com.nowjoo.nowjiu.cart.dto.CartDto;
import com.nowjoo.nowjiu.cart.dto.CartGoodsDto;
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
	// 상품 장바구니 조회
	public Cart getcart(int goodId, int userId) {
		Optional<Cart> optionalCart = cartRespository.findByGoodsIdAndUserId(goodId, userId);
		Cart cart = optionalCart.orElse(null);
	
		return cart;
	}
	
	// 상품 장바구니 조회
	public Cart getcart(int cartId) {
		Optional<Cart> optionalCart = cartRespository.findById(cartId);
		Cart cart = optionalCart.orElse(null);
		return cart;
	}
	
	public List<Cart> orderGetUserCart(int userId) {
		List<Cart> cartlist = cartRespository.findByUserId(userId);
		return cartlist;
	}
	
	
	
	// 회원 장바구니 조회
	public CartDto getUserCart(int userId) {
		List<Cart> cartList = cartRespository.findByUserId(userId);
		
		List<CartGoodsDto> cartGoodsList = new ArrayList<>();
		int total = 0;
		for(Cart cart : cartList){
			int goodId = cart.getGoodsId();
			Goods goods = goodsService.getGoods(goodId);
			
			int cartId = cart.getId();
			
			total += goods.getPrice();
			CartGoodsDto cartGoods = CartGoodsDto.builder()
										.cartId(cartId)
										.name(goods.getName())
										.price(goods.getPrice())
										.image(goods.getImage())
										.build();
			
			cartGoodsList.add(cartGoods);
		}
		
		CartDto cartDto = CartDto.builder()
								.userId(userId)
								.cartGoods(cartGoodsList)
								.total(total)
								.build();
		return cartDto;
	}
	
	// 장바구니 삭제
	public boolean cartdelete(int cartId) {
		Optional<Cart> optionalCart = cartRespository.findById(cartId);
		Cart cart = optionalCart.orElse(null);
		
		if (cart != null) {
			cartRespository.delete(cart);
			return true;
		}else {
			return false;
		}
	}
	
	public void deleteCartByUesrIdAndGoodsId(int userId, int goodsId) {
		Optional<Cart> optionalCart = cartRespository.findByGoodsIdAndUserId(goodsId,userId);
		Cart cart = optionalCart.orElse(null);
		if (cart != null) {
			cartRespository.delete(cart);
		}
	}
		
	
	// 장바구니 추가
	public Cart insertCart(int goodId, int userId) {
		
		Cart cart = getcart(goodId, userId);
		
		if(cart == null) {
			cart = Cart.builder()
						.goodsId(goodId)
						.userId(userId)
						.build();
			
		}else {
			return null;
		}

		return cartRespository.save(cart);
	}
}
