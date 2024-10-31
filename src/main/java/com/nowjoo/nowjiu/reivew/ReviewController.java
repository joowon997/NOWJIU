package com.nowjoo.nowjiu.reivew;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowjoo.nowjiu.goods.dto.GoodsListDto;
import com.nowjoo.nowjiu.reivew.dto.ReviewCreateDto;
import com.nowjoo.nowjiu.reivew.dto.ReviewDto;
import com.nowjoo.nowjiu.reivew.service.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/nowjiu")
public class ReviewController {

	private ReviewService reviewService;
	
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@GetMapping("/review")
	public String list(Model model){
		
		List<ReviewDto> reviewDtoList = reviewService.getReviewList();
		
		model.addAttribute("reviews", reviewDtoList);
		
		return "review/list";
	}

	@GetMapping("/review-create")
	public String create(
			@RequestParam("goodsId") int goodsId
			, Model model
			){
		ReviewCreateDto reviewCreateDto = reviewService.reivewForGoodsInfo(goodsId);
		
		model.addAttribute("goods", reviewCreateDto);
		
		return "review/create";
	}
}
