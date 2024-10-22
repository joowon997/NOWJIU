package com.nowjoo.nowjiu.cart.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartGoodsDto {
	
	private int cartId;
	
	private String name;
	private int price;
	private String image;
	
}
