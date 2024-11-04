package com.nowjoo.nowjiu.brand.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BrandDto {

	private int brandId;
	private String brandName;
	private String brandImage;
	
}
