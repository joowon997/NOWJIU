package com.nowjoo.nowjiu.goods.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GoodsInfoDto {

	private int goodsId;
	private String name;
	private int price;
	private String description;
	private String mainImage;
	
}
