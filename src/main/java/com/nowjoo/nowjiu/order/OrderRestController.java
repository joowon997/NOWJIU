package com.nowjoo.nowjiu.order;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nowjoo.nowjiu.order.domain.Order;
import com.nowjoo.nowjiu.order.domain.OrderList;
import com.nowjoo.nowjiu.order.dto.DirectOrderDto;
import com.nowjoo.nowjiu.order.dto.OrderListDto;
import com.nowjoo.nowjiu.order.service.OrderService;
import com.siot.IamportRestClient.exception.IamportResponseException;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class OrderRestController {

	private OrderService orderService;
	
	public OrderRestController(
			OrderService orderService
			) {
		this.orderService = orderService;
	}
	
	@PostMapping("/order-save")
	public Map<String, String> saveOrder(
			@RequestBody Order request)
					throws IamportResponseException, IOException{
		
		Order order = orderService.insertOrder(request);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if (order != null) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}

	@PostMapping("/orderList-save")
	public Map<String, String> saveOrderList(
			@RequestBody OrderListDto request
			, HttpSession session)
					throws IamportResponseException, IOException{
		int userId = (Integer)session.getAttribute("userId");
		
		OrderList orderList = orderService.insertOrderList(request, userId);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if (orderList != null) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
}
