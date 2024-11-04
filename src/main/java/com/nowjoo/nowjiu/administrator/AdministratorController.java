package com.nowjoo.nowjiu.administrator;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nowjoo.nowjiu.administrator.dto.AddGoodsdDto;
import com.nowjoo.nowjiu.administrator.dto.AdministratorMainDto;
import com.nowjoo.nowjiu.administrator.dto.GoodsDto;
import com.nowjoo.nowjiu.administrator.dto.MemberDto;
import com.nowjoo.nowjiu.administrator.service.AdministratorService;
import com.nowjoo.nowjiu.brand.dto.BrandDto;
import com.nowjoo.nowjiu.order.dto.OrderHistoryDto;
import com.nowjoo.nowjiu.reivew.dto.ReviewDto;
import com.nowjoo.nowjiu.administrator.service.AdministratorGoodsService;
import com.nowjoo.nowjiu.administrator.service.AdministratorOrderService;
import com.nowjoo.nowjiu.administrator.service.AdministratorReviewService;


@Controller
@RequestMapping("/nowjiu/administrator")
public class AdministratorController {
	
	private AdministratorService administratorService;
	private AdministratorGoodsService administratorGoodsService;
	private AdministratorOrderService administratorOrderService;
	private AdministratorReviewService administratorReviewService;
	
	public AdministratorController(
			AdministratorService administratorService
			, AdministratorGoodsService administratorGoodsService
			, AdministratorOrderService administratorOrderService
			, AdministratorReviewService administratorReviewService
			) {
		this.administratorService = administratorService;
		this.administratorGoodsService = administratorGoodsService;
		this.administratorOrderService = administratorOrderService;
		this.administratorReviewService = administratorReviewService;
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
	public String administratorMain(
			Model model){
		AdministratorMainDto administratorMainDto = administratorService.mainCount();
		
		model.addAttribute("main", administratorMainDto);
		
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
		List<GoodsDto> goodsList = administratorGoodsService.getGoodsList();
		
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
	public String administratorOrder(
			Model model){
		
		List<OrderHistoryDto> orderHistoryDto = administratorOrderService.getOrderHistroy();
		
		model.addAttribute("order", orderHistoryDto);
		
		return "administrator/order";
	}
	@GetMapping("/review")
	public String administratorRiview(
			Model model){
		List<ReviewDto> reviewDtoList = administratorReviewService.getOrderHistroy();
		
		model.addAttribute("reivews", reviewDtoList);
		
		return "administrator/review";
	}
	
	@GetMapping("/goods-brand")
	public String administratorBrand(
			Model model){
		
		List<BrandDto> brandDtos = administratorGoodsService.getBrandDtos();
		
		model.addAttribute("brandList", brandDtos);
		
		return "administrator/brand";
	}
}
