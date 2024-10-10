package com.nowjoo.nowjiu.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nowjoo.nowjiu.user.domain.User;
import com.nowjoo.nowjiu.user.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {

	private UserService userService;
	
	public UserRestController(
			UserService userService
			) {
		this.userService = userService;
	}
	//중복확인 기능
	
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
