package com.nowjoo.nowjiu.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.cart.domain.Cart;
import com.nowjoo.nowjiu.cart.service.CartService;
import com.nowjoo.nowjiu.goods.domain.Goods;
import com.nowjoo.nowjiu.goods.serviece.GoodsService;
import com.nowjoo.nowjiu.order.domain.Order;
import com.nowjoo.nowjiu.order.domain.OrderList;
import com.nowjoo.nowjiu.order.dto.CartOrderDto;
import com.nowjoo.nowjiu.order.dto.CartOrderListDto;
import com.nowjoo.nowjiu.order.dto.DirectOrderDto;
import com.nowjoo.nowjiu.order.dto.OrderListDto;
import com.nowjoo.nowjiu.order.dto.OrderHistoryDto;
import com.nowjoo.nowjiu.order.respository.OrderListRepository;
import com.nowjoo.nowjiu.order.respository.OrderRepository;
import com.nowjoo.nowjiu.user.domain.User;
import com.nowjoo.nowjiu.user.service.UserService;

@Service
public class OrderService {

	private CartService cartService;
	private UserService userService;
	private GoodsService goodsService;
	private OrderRepository orderRepository;
	private	OrderListRepository orderListRepository;
	
	public OrderService(
			CartService cartService
			, UserService userService
			, GoodsService goodsService
			, OrderRepository orderRepository
			, OrderListRepository orderListRepository
			) {
		this.cartService = cartService;
		this.userService = userService;
		this.goodsService = goodsService;
		this.orderRepository= orderRepository;
		this.orderListRepository = orderListRepository;
	}
		// 리뷰전 상품주문 유무
		public boolean isOrder(int userId, int goodsId) {
			Optional<OrderList> optionalList = orderListRepository.findByUserIdAndGoodsId(userId, goodsId);
			OrderList orderList = optionalList.orElse(null);
			
			if (orderList != null) {
				return true;
			}else {
				return false;
			}
		}
	
	
		// 모든 주문수 조회
		public long getOrderCount(){
			long count = orderRepository.count();
			return count;
		}
		
		// 관리자 페이지 상품구매 목록
		public List<OrderHistoryDto> getOrderHistroy () {
			List<OrderList> orderList = orderListRepository.findAll();
			List<OrderHistoryDto> orderHistoryDtoList = new ArrayList<>();
			// 상품번호에 대한 상품 목록 생성
			int orderId = 0;
			for(OrderList list : orderList) {
				int getorderId = list.getOrderId();
				if(orderId != getorderId) {
				orderId = getorderId;
				Optional<Order> optionalOrder = orderRepository.findById(orderId);
				Order order = optionalOrder.orElse(null);
				
				String merchantUid = order.getMerchantUid();
				int totalPrice = order.getAmount();
				String address = order.getAddress();
				
				List<OrderList> forGoodList = orderListRepository.findByOrderId(orderId);
				List<Goods> goodsList = new ArrayList<>();
				for(OrderList forlist : forGoodList) {
					int goodsId = forlist.getGoodsId();
					Goods goods = goodsService.getGoods(goodsId);
					goodsList.add(goods);
				}
				
				// 회원이름
				int userId = list.getUserId();
				User user = userService.getOneUser(userId);
				String userName = user.getName();
				
				OrderHistoryDto orderHistoryDto = OrderHistoryDto.builder()
														.userName(userName)
														.merchantUid(merchantUid)
														.totalPrice(totalPrice)
														.address(address)
														.goods(goodsList)
														.build();
				orderHistoryDtoList.add(orderHistoryDto);
				}
			}
			return orderHistoryDtoList;
			
		};
		
	// 상품구매 목록
	public List<OrderHistoryDto> getOrderHistroy (int userId) {
		List<OrderList> orderList = orderListRepository.findByUserId(userId);
		List<OrderHistoryDto> orderHistoryDtoList = new ArrayList<>();
		// 상품번호에 대한 상품 목록 생성
		int orderId = 0;
		for(OrderList list : orderList) {
			int getorderId = list.getOrderId();
			if(orderId != getorderId) {
			orderId = getorderId;
			Optional<Order> optionalOrder = orderRepository.findById(orderId);
			Order order = optionalOrder.orElse(null);
			
			String merchantUid = order.getMerchantUid();
			int totalPrice = order.getAmount();
			String address = order.getAddress();
			
			List<OrderList> forGoodList = orderListRepository.findByOrderId(orderId);
			List<Goods> goodsList = new ArrayList<>();
			for(OrderList forlist : forGoodList) {
				int goodsId = forlist.getGoodsId();
				Goods goods = goodsService.getGoods(goodsId);
				goodsList.add(goods);
			}
			
			OrderHistoryDto orderHistoryDto = OrderHistoryDto.builder()
													.merchantUid(merchantUid)
													.totalPrice(totalPrice)
													.address(address)
													.goods(goodsList)
													.build();
			orderHistoryDtoList.add(orderHistoryDto);
			}
		}
		return orderHistoryDtoList;
		
	};
	
	// 단건 주문 기록 저장
	public Order insertOrder(Order request) {
		// 주문기록 저장
		Order order = Order.builder()
						.merchantUid(request.getMerchantUid())
						.payMethod(request.getPayMethod())
						.userName(request.getUserName())
						.amount(request.getAmount())
						.phone(request.getPhone())
						.address(request.getAddress())
						.postcode(request.getPostcode())
						.build();
		order = orderRepository.save(order);
		
		return order;
	}
	
	// 주문목록 저장
	public OrderList insertOrderList(OrderListDto request, int userId) {
		// 상품 id
		int goodId = request.getGoodsId();
		// 주문기록 id
		String merchantUid = request.getMerchantUid();
		Optional<Order> orderOptional = orderRepository.findByMerchantUid(merchantUid);
		Order order = orderOptional.orElse(null);
		
		int orderId = order.getId();
		
		OrderList orderList = OrderList.builder()
									.goodsId(goodId)
									.userId(userId)
									.orderId(orderId)
									.build();
		
		cartService.deleteCartByUesrIdAndGoodsId(userId, goodId);
		
		return orderListRepository.save(orderList);
	}
	
	// 다건 주문목록 저장
		public OrderList insertCartOrderList(CartOrderListDto request, int userId) {
			// 주문기록 id
			String merchantUid = request.getMerchantUid();
			Optional<Order> orderOptional = orderRepository.findByMerchantUid(merchantUid);
			Order order = orderOptional.orElse(null);
			
			int orderId = order.getId();
			
			// 상품 id
			List<Integer> goodIdList = request.getGoodsId();
			OrderList orderList = null;
			for(int goodId : goodIdList) {
			orderList = OrderList.builder()
								.goodsId(goodId)
								.userId(userId)
								.orderId(orderId)
								.build();
			
			cartService.deleteCartByUesrIdAndGoodsId(userId, goodId);
			orderListRepository.save(orderList);
			}
			return orderList;
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
	
	// 선택상품 주문페이지 이동
	public CartOrderDto getcartOrder(List<Integer> cartIdList, int userId) {
		
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
	
	// 전체상품
	public CartOrderDto getcartOrder(int userId) {
		// 회원정보 조회
		User user = userService.getOneUser(userId);
				
		String phone[] = user.getPhoneNumber().split("-");
		// 구매 리스트
		List<Cart> cartList = cartService.orderGetUserCart(userId);
		List<Goods> goodsList = new ArrayList<>();
		int total = 0;
		for(Cart cart : cartList) {
			int goodsId = cart.getGoodsId();
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
