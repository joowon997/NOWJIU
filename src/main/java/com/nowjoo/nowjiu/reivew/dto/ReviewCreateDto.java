package com.nowjoo.nowjiu.reivew.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewCreateDto {

	private int goodsId;
	
	private String goodsName;
	private String goodsImage;
	
}
