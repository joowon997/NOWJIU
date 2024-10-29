package com.nowjoo.nowjiu.order;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowjoo.nowjiu.order.dto.CartOrderDto;
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
	
	@GetMapping("/order-selete-cart")
	public String orderSelectCart(
			@RequestParam ("cartIdList") List<Integer> catrIdList
			, Model model
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		CartOrderDto cartOrderDto = orderService.getcartOrder(catrIdList, userId);
		
		model.addAttribute("order", cartOrderDto);
		
		return "goods/orderCart";
	}
	
	@GetMapping("/order-all-cart")
	public String orderAllCart(
			 Model model
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		CartOrderDto cartOrderDto = orderService.getcartOrder(userId);
		
		model.addAttribute("order", cartOrderDto);
		
		return "goods/orderCart";
	}
	
	@GetMapping("/order-list")
	public String orderList(
			Model model
			, HttpSession session){
		return "goods/orderlist";
	}
}
