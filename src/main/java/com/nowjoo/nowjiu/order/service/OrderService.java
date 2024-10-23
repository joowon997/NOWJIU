package com.nowjoo.nowjiu.order.service;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.cart.domain.Cart;
import com.nowjoo.nowjiu.cart.service.CartService;
import com.nowjoo.nowjiu.goods.domain.Goods;
import com.nowjoo.nowjiu.goods.serviece.GoodsService;
import com.nowjoo.nowjiu.order.dto.DirectOrderDto;
import com.nowjoo.nowjiu.user.domain.User;
import com.nowjoo.nowjiu.user.service.UserService;

@Service
public class OrderService {

	private CartService cartService;
	private UserService userService;
	private GoodsService goodsService;
	
	public OrderService(
			CartService cartService
			, UserService userService
			, GoodsService goodsService
			) {
		this.cartService = cartService;
		this.userService = userService;
		this.goodsService = goodsService; 
	}

	
	public DirectOrderDto getDirectOrder(int goodsId, int userId) {
		Cart cart = cartService.getcart(goodsId, userId);
		// 장바구니 없을때 추가
		if (cart == null) {
			cart = cartService.insertCart(goodsId, userId);
		}
		// 회원정보 조회
		User user = userService.getOneUser(userId);
		
		String phone[] = user.getPhoneNumber().split("-");
		
		// 상품정보 조회
		Goods goods = goodsService.getGoods(goodsId);
		
		DirectOrderDto directOrderDto = DirectOrderDto.builder()
										.userId(userId)
										.goodsId(goodsId)
										.cartId(cart.getId())
										.userName(user.getName())
										.phoneNum2(phone[1])
										.phoneNum3(phone[2])
										.userZipCode(user.getZipCode())
										.userAddress(user.getAddress())
										.userDetaileAddress(user.getDetaileAddress())
										.goodsImage(goods.getImage())
										.goodsName(goods.getName())
										.goodsPrice(goods.getPrice())
										.build();
		return directOrderDto;
	}
	
}
