package com.nowjoo.nowjiu.administrator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nowjiu/administrator")
public class AdministratorController {

	@GetMapping("/join")
	public String administratorJoin(){
		return "administrator/join";
	}
	@GetMapping("/main")
	public String administratorMain(){
		return "administrator/main";
	}
	@GetMapping("/member")
	public String administratorMember(){
		return "administrator/member";
	}
	@GetMapping("/goods")
	public String administratorGoods(){
		return "administrator/goods";
	}
	@GetMapping("/goods-add")
	public String administratorGoodsAdd(){
		return "administrator/addGoods";
	}
	@GetMapping("/review")
	public String administratorReview(){
		return "administrator/join";
	}
	@GetMapping("/board")
	public String administratorBoard(){
		return "administrator/join";
	}
}
