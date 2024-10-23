package com.nowjoo.nowjiu.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowjoo.nowjiu.order.dto.DirectOrderDto;
import com.nowjoo.nowjiu.order.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/nowjiu")
public class OrderController {

	private OrderService orderService;
	
	public OrderController(
			OrderService orderService
			) {
		this.orderService = orderService;
	}
	
	@GetMapping("/order")
	public String order(
			@RequestParam ("goodsId") int goodsId
			, Model model
			, HttpSession session){
		int userId = (Integer)session.getAttribute("userId");
		
		DirectOrderDto directOrderDto = orderService.getDirectOrder(goodsId, userId);
		
		model.addAttribute("order", directOrderDto);
		
		return "goods/order";
	}
	
	@GetMapping("/order-cart")
	public String orderCart(){
		return "goods/order";
	}
}
