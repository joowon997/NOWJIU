package com.nowjoo.nowjiu.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nowjoo.nowjiu.order.dto.DirectOrderDto;
import com.nowjoo.nowjiu.order.service.OrderService;

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
	
//	@PostMapping("/order-direct")
//	public Map<String, String> addCart(
//			@RequestParam ("goodsId") int goodsId
//			, HttpSession session){
//		int userId = (Integer)session.getAttribute("userId");
//		
//		DirectOrderDto directOrderDto = orderService.getDirectOrder(goodsId, userId);
//		
//		Map<String, String> resultMap = new HashMap<>();
//		if (directOrderDto != null) {
//			resultMap.put("result", "success");
//		} else {
//			resultMap.put("result", "fail");
//		}
//		return resultMap;
//	}
}
