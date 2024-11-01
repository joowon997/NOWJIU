package com.nowjoo.nowjiu.administrator.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AdministratorMainDto {

	private long userCount;
	private long goodsCount;
	private long orderCount;
	private long reviewCount;
}
