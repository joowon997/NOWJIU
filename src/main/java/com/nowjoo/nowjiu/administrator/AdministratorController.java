package com.nowjoo.nowjiu.administrator;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nowjoo.nowjiu.administrator.dto.AddGoodsdDto;
import com.nowjoo.nowjiu.administrator.dto.GoodsListDto;
import com.nowjoo.nowjiu.administrator.dto.MemberDto;
import com.nowjoo.nowjiu.administrator.service.AdministratorService;
import com.nowjoo.nowjiu.administrator.service.AdministratorGoodsService;


@Controller
@RequestMapping("/nowjiu/administrator")
public class AdministratorController {
	
	private AdministratorService administratorService;
	private AdministratorGoodsService administratorGoodsService;
	
	public AdministratorController(
			AdministratorService administratorService
			, AdministratorGoodsService administratorGoodsService
			) {
		this.administratorService = administratorService;
		this.administratorGoodsService = administratorGoodsService;
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
	public String administratorGoods(
			Model model){
		List<GoodsListDto> goodsList = administratorGoodsService.getGoodsList();
		
		model.addAttribute("goodsList", goodsList);
		
		return "administrator/goods";
	}
	@GetMapping("/goods-add")
	public String administratorGoodsAdd(
			Model model){
		AddGoodsdDto addGoodsdDto = administratorGoodsService.brandAndCategoryOption();
		
		model.addAttribute("brandAndCategory", addGoodsdDto);
		
		return "administrator/addGoods";
	}
	@GetMapping("/goods-inventory")
	public String goodsInventory(){
		return "administrator/inventory";
	}
	@GetMapping("/board")
	public String administratorBoard(){
		return "administrator/board";
	}
	@GetMapping("/order")
	public String administratorOrder(){
		return "administrator/order";
	}
	@GetMapping("/review")
	public String administratorRiview(){
		return "administrator/review";
	}
}
