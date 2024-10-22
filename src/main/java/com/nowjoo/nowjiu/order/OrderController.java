package com.nowjoo.nowjiu.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nowjiu")
public class OrderController {

	@GetMapping("/order")
	public String order(){
		return "goods/order";
	}
	
	@GetMapping("/order-cart")
	public String orderCart(){
		return "goods/order";
	}
}
