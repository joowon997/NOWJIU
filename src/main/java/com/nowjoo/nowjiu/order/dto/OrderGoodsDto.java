package com.nowjoo.nowjiu.order.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderGoodsDto {

	private String goodsName;
	private String goodsImage;
	private int goodsPrice;
	private int goodsId;
	
	private boolean isReview;
}
