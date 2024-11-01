package com.nowjoo.nowjiu.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/nowjiu")
public class UserController {

	@GetMapping("/login")
	public String login(){
		return "user/login";
	}

	@GetMapping("/join")
	public String join(){
		return "user/join";
	}
	
	// 로그아웃 기능
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.removeAttribute("userId");
			session.removeAttribute("userName");
			
			return "redirect:/nowjiu/main";
		}
	
}
