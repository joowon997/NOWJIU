package com.nowjoo.nowjiu.cart.dto;

import java.util.List;

import com.nowjoo.nowjiu.goods.domain.Goods;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartDto {

	private int userId;

	private List<Goods> goodsList;
}
