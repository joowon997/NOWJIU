package com.nowjoo.nowjiu.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nowjiu")
public class UserController {

	@GetMapping("/main")
	public String main(){
		return "user/main";
	}

	@GetMapping("/login")
	public String login(){
		return "user/login";
	}

	@GetMapping("/join")
	public String join(){
		return "user/join";
	}
}
