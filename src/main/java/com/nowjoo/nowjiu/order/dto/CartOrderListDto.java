package com.nowjoo.nowjiu.order.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartOrderListDto {

	private String merchantUid;
	private List<Integer> goodsId;
}
