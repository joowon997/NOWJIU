package com.nowjoo.nowjiu.administrator.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GoodsDto{

	private int goodsId;
	private String mainImage;
	private String name;
	private int price;
}
