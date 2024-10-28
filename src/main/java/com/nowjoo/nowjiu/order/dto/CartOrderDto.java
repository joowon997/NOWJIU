package com.nowjoo.nowjiu.order.dto;

import java.util.List;

import com.nowjoo.nowjiu.cart.domain.Cart;
import com.nowjoo.nowjiu.goods.domain.Goods;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartOrderDto {

	private int userId;
	
	private String userName;
	private String phoneNum2;
	private String phoneNum3;
	private int userZipCode;
	private String userAddress;
	private String userDetaileAddress;
	
	private List<Goods> goodList;
	
	private int totalPrice;
}
