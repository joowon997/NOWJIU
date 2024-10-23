package com.nowjoo.nowjiu.order.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DirectOrderDto {

	private int userId;
	private int goodsId;
	private int cartId;
	
	private String userName;
	private String phoneNum2;
	private String phoneNum3;
	private int userZipCode;
	private String userAddress;
	private String userDetaileAddress;
	
	private String goodsImage;
	private String goodsName;
	private int goodsPrice;
	
	private int total;
	
}
