package com.nowjoo.nowjiu.reivew;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nowjoo.nowjiu.reivew.service.ReviewService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class ReviewRestController {

	private ReviewService reviewService;
	
	public ReviewRestController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	//상품 추가
		@PostMapping("/review-add")
		public Map<String, String> addGoods(
				@RequestParam("goodsId") int goodsId
				, @RequestParam("title") String title
				, @RequestParam("count") int count
				, @RequestParam("review") String review
				, @RequestParam(value = "image" , required=false) MultipartFile image
				, HttpSession session
				){
			int userId = (Integer)session.getAttribute("userId");
			
			boolean addreview = reviewService.insertReview(goodsId, userId, title, review, count, image);
					
			Map<String, String> resultMap = new HashMap<>();
			
			if (addreview) {
				resultMap.put("result", "success");
			} else {
				resultMap.put("result", "fail");
			}
			
			return resultMap;
		}
		
		// 리뷰 삭제
		@DeleteMapping("/review-delete")
		public Map<String, String> delectPost(
				@RequestParam("reviewId") int reviewId
				){
			
			Map<String, String> resultMap = new HashMap<>();
			
			if(reviewService.deleteReview(reviewId)) {
				resultMap.put("result", "success");
			}else {
				resultMap.put("result", "fail");
			}
			return resultMap;
		}
}
