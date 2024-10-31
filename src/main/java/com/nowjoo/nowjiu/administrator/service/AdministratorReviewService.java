package com.nowjoo.nowjiu.administrator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.reivew.dto.ReviewDto;
import com.nowjoo.nowjiu.reivew.service.ReviewService;

@Service
public class AdministratorReviewService {

	private ReviewService reviewService;
	
	public AdministratorReviewService(
			ReviewService reviewService) {
		
		this.reviewService = reviewService;
	}
	
	public List<ReviewDto> getOrderHistroy(){
		return reviewService.getReviewList();
	}
}
