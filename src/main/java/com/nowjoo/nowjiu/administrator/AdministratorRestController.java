package com.nowjoo.nowjiu.administrator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nowjoo.nowjiu.administrator.domain.Administrator;
import com.nowjoo.nowjiu.administrator.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/administrator")
public class AdministratorRestController {

	private AdministratorService administratorService;
	
	public AdministratorRestController(
			AdministratorService administratorService) {
		this.administratorService = administratorService;
	};
	
	// 관리자 로그인 기능
		@PostMapping("/login")
		public Map<String, String> login(
				@RequestParam ("loginId") String loginId
				, @RequestParam ("password") String password
				, HttpSession session){
			
			Administrator administrator = administratorService.getAdministrator(loginId, password);
			
			Map<String, String> result = new HashMap<>();
			if (administrator != null) {
				result.put("result", "success");
				//사용자 정보
				session.setAttribute("userId", administrator.getId());
				session.setAttribute("userName", administrator.getName());
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
			
			if (administratorService.isDuplicateId(loginId)) {
				result.put("isDuplicate", true);
			}else {
				result.put("isDuplicate", false);
			}
			return result;
		}
	
	//회원가입 기능
		@PostMapping("/join")
		public Map<String, String> userJoin(
				@RequestParam ("loginId") String loginId
				, @RequestParam ("password") String password
				, @RequestParam ("name") String name
				){
			
			Administrator administrator = administratorService.addAdministrator(loginId, password, name);
			
			Map<String, String> resultMap = new HashMap<>();
			
			if (administrator != null) {
				resultMap.put("result", "success");
			} else {
				resultMap.put("result", "fail");
			}
			
			return resultMap;
		}
	
}
