package com.nowjoo.nowjiu.administrator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.order.dto.OrderHistoryDto;
import com.nowjoo.nowjiu.order.service.OrderService;

@Service
public class AdministratorOrderService {

	private OrderService orderService;
	
	public AdministratorOrderService(
			OrderService orderService) {
		this.orderService = orderService;
	}
	
	public List<OrderHistoryDto> getOrderHistroy(){
		return orderService.getOrderHistroy();
	}
	
}
