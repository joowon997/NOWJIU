package com.nowjoo.nowjiu.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nowjoo.nowjiu.user.domain.User;
import com.nowjoo.nowjiu.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class UserRestController {

	private UserService userService;
	
	public UserRestController(
			UserService userService
			) {
		this.userService = userService;
	}
	
	// 로그인 기능
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestParam ("loginId") String loginId
			, @RequestParam ("password") String password
			, HttpSession session){
		
		User user = userService.getUser(loginId, password);
		
		Map<String, String> result = new HashMap<>();
		if (user != null) {
			result.put("result", "success");
			//사용자 정보
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
		}else {
			result.put("result", "fail");
		}
		return result;
	}
	
	//중복확인 기능
	@GetMapping("/duplicate-id")
	public Map<String, Boolean> isDuplicate(
			@RequestParam ("loginId") String loginId){
		
		Map<String, Boolean> result = new HashMap<>();
		
		if (userService.isDuplicateId(loginId)) {
			result.put("isDuplicate", true);
		}else {
			result.put("isDuplicate", false);
		}
		return result;
	}
	
	//회원가입 기능
	@PostMapping("/user-join")
	public Map<String, String> userJoin(
			@RequestParam ("loginId") String loginId
			, @RequestParam ("password") String password
			, @RequestParam ("name") String name
			, @RequestParam ("email") String email
			, @RequestParam ("phoneNumber") String phoneNumber
			, @RequestParam ("zipCode") int zipCode
			, @RequestParam ("address") String address
			, @RequestParam ("detaileAddress") String detaileAddress
			){
		
		User user = userService.addUser(loginId, password, name, email, phoneNumber, zipCode, address, detaileAddress);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if (user != null) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
}
