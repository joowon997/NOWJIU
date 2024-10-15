package com.nowjoo.nowjiu.administrator;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nowjoo.nowjiu.administrator.dto.MemberDto;
import com.nowjoo.nowjiu.administrator.service.AdministratorService;


@Controller
@RequestMapping("/nowjiu/administrator")
public class AdministratorController {
	
	private AdministratorService administratorService;
	
	public AdministratorController(
			AdministratorService administratorService
			) {
		this.administratorService = administratorService;
	}
	
	@GetMapping("/join")
	public String administratorJoin(){
		return "administrator/join";
	}
	
	@GetMapping("/login")
	public String administratorReview(){
		return "administrator/login";
	}
	
	@GetMapping("/main")
	public String administratorMain(){
		return "administrator/main";
	}
	
	@GetMapping("/member")
	public String administratorMember(
			Model model){
		List<MemberDto> memberList = administratorService.getMemberInfo();
		
		model.addAttribute("userInfo", memberList);
		
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
	@GetMapping("/board")
	public String administratorBoard(){
		return "administrator/join";
	}
	@GetMapping("/order")
	public String administratorOrder(){
		return "administrator/join";
	}
	@GetMapping("/riview")
	public String administratorRiview(){
		return "administrator/join";
	}
}
