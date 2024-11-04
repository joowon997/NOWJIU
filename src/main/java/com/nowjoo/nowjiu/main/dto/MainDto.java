package com.nowjoo.nowjiu.main.dto;

import java.util.List;

import com.nowjoo.nowjiu.brand.domain.Brand;
import com.nowjoo.nowjiu.brand.dto.BrandDto;
import com.nowjoo.nowjiu.goods.domain.Goods;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MainDto {
	
	private List<BrandDto> brandList;
	
	private List<Goods> giList;
	private List<Goods> noGiList;
	private List<Goods> beltList;
	private List<Goods> protectiveList;
	private List<Goods> clothesList;
	private List<Goods> accessoriesList;

	private List<Goods> newList;
	
	
	
}
