package com.nowjoo.nowjiu.goods.dto;

import java.util.List;

import com.nowjoo.nowjiu.administrator.dto.GoodsDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GoodsListDto {

	private int count;
	private List<GoodsDto> goodsList;
	
}
