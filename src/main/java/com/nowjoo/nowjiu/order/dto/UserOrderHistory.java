package com.nowjoo.nowjiu.order.dto;

import java.util.List;

import com.nowjoo.nowjiu.order.domain.Order;

public class UserOrderHistory {

	private int orderListId;
	private int goodsId;
	private int orderId;
	
	private List<Order> orderList;
}
