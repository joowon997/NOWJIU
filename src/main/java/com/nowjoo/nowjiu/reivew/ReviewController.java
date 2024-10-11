package com.nowjoo.nowjiu.reivew;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nowjiu")
public class ReviewController {

	@GetMapping("/review")
	public String list(){
		return "review/list";
	}

	@GetMapping("/review-create")
	public String create(){
		return "review/create";
	}
}
