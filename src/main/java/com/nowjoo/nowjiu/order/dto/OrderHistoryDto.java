package com.nowjoo.nowjiu.order.dto;

import java.util.List;

import com.nowjoo.nowjiu.goods.domain.Goods;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderHistoryDto {
	private String userName;

	private String merchantUid;
	private int totalPrice;
	private String address;
	private List<Goods> goods;
}
