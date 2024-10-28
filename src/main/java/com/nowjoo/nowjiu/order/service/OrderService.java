package com.nowjoo.nowjiu.order.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.cart.domain.Cart;
import com.nowjoo.nowjiu.cart.service.CartService;
import com.nowjoo.nowjiu.goods.domain.Goods;
import com.nowjoo.nowjiu.goods.serviece.GoodsService;
import com.nowjoo.nowjiu.order.domain.Order;
import com.nowjoo.nowjiu.order.dto.CartOrderDto;
import com.nowjoo.nowjiu.order.dto.DirectOrderDto;
import com.nowjoo.nowjiu.order.respository.OrderRepository;
import com.nowjoo.nowjiu.payment.service.PaymentService;
import com.nowjoo.nowjiu.user.domain.User;
import com.nowjoo.nowjiu.user.service.UserService;

@Service
public class OrderService {

	private CartService cartService;
	private UserService userService;
	private GoodsService goodsService;
	private OrderRepository orderRepository;
	
	public OrderService(
			CartService cartService
			, UserService userService
			, GoodsService goodsService
			, OrderRepository orderRepository
			, PaymentService paymentService
			) {
		this.cartService = cartService;
		this.userService = userService;
		this.goodsService = goodsService;
		this.orderRepository= orderRepository;
	}

	// 주문 리스트 저장
	
	
	// 주문 기록 저장
	public Order insertOrder(Order request, int userId) {
		// 상품id
		int goodsId = goodsService.getGoodsID(request.getGoodsName());
		
		// 주문기록 저장
		Order order = Order.builder()
						.merchantUid(request.getMerchantUid())
						.userId(userId)
						.goodsId(goodsId)
						.payMethod(request.getPayMethod())
						.userName(request.getUserName())
						.goodsName(request.getGoodsName())
						.amount(request.getAmount())
						.phone(request.getPhone())
						.address(request.getAddress())
						.postcode(request.getPostcode())
						.build();
		order = orderRepository.save(order);
		
		// 장바구니 삭제
		cartService.deleteCartByUesrIdAndGoodsId(userId,goodsId);
		
		return order;
	}
	
	
	// 상세페이지 => 주문페이지 이동
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
	
	// 장바구니페이지 => 주문페이지 이동
	public CartOrderDto getcartOrder(List<Integer> cartIdList, int userId) {
//		//카트 id
//		List<Integer> cartIdList = new ArrayList<>();
//		String cartStr[] = cartIdStr.split(",");
//		for (String cart : cartStr) {
//			int cartId = Integer.parseInt(cart);
//			cartIdList.add(cartId);
//		}
		
		// 회원정보 조회
		User user = userService.getOneUser(userId);
				
		String phone[] = user.getPhoneNumber().split("-");
		// 구매 리스트
		List<Goods> goodsList = new ArrayList<>();
		int total = 0;
		for(int cartId : cartIdList) {
			int goodsId = cartService.getcart(cartId).getGoodsId();
			Goods goods = goodsService.getGoods(goodsId);
			total += goods.getPrice();
			
			goodsList.add(goods);
		}
		CartOrderDto cartOrderDto = CartOrderDto.builder()
									.userId(userId)
									.userName(user.getName())
									.phoneNum2(phone[1])
									.phoneNum3(phone[2])
									.userZipCode(user.getZipCode())
									.userAddress(user.getAddress())
									.userDetaileAddress(user.getDetaileAddress())
									.goodList(goodsList)
									.totalPrice(total)
									.build();
		
		return cartOrderDto;
	}
	
}
