package com.nowjoo.nowjiu.administrator.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AdministratorMainDto {

	private int userCount;
	private int goodsCount;
	private int orderCount;
	private int reviewCount;
}
