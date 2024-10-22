package com.nowjoo.nowjiu.goods.dto;

import java.util.List;

import com.nowjoo.nowjiu.goods.domain.GoodsImage;

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
	private List<GoodsImage> infoImage;
	
}
