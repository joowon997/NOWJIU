package com.nowjoo.nowjiu.administrator.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GoodsListDto {

	private int goodsId;
	private String imagePath;
	private String name;
	private int price;
	
}
