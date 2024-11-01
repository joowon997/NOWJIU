package com.nowjoo.nowjiu.reivew.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nowjoo.nowjiu.brand.domain.Brand;
import com.nowjoo.nowjiu.category.domian.Category;
import com.nowjoo.nowjiu.common.FileManager;
import com.nowjoo.nowjiu.goods.domain.Goods;
import com.nowjoo.nowjiu.goods.domain.GoodsImage;
import com.nowjoo.nowjiu.goods.serviece.GoodsService;
import com.nowjoo.nowjiu.order.service.OrderService;
import com.nowjoo.nowjiu.reivew.domain.Review;
import com.nowjoo.nowjiu.reivew.dto.ReviewCreateDto;
import com.nowjoo.nowjiu.reivew.dto.ReviewDto;
import com.nowjoo.nowjiu.reivew.repository.ReviewRepository;
import com.nowjoo.nowjiu.user.domain.User;
import com.nowjoo.nowjiu.user.service.UserService;

@Service
public class ReviewService {

	private ReviewRepository reviewRepository;
	private GoodsService goodsService;
	private UserService userService;
	
	public ReviewService(
			ReviewRepository reviewRepository
			, GoodsService goodsService
			, UserService userService) {
		this.reviewRepository = reviewRepository;
		this.goodsService = goodsService;
		this.userService = userService;
	}
	
	// 모든 리뷰수 조회
		public long getUserCount(){
			long getReviewCount = reviewRepository.count();
			return getReviewCount;
		}
	
	// 리뷰페이지
	public List<ReviewDto> getReviewList() {
		List<Review> reviewList = reviewRepository.findAll();

		List<ReviewDto> reviewDtoList = new ArrayList<>();
		
		for(Review review : reviewList) {
			int userId = review.getUserId();
			int goodsId = review.getGoodsId();
			
			User user = userService.getOneUser(userId);
			Goods goods	= goodsService.getGoods(goodsId);
			
			ReviewDto reviewDto = ReviewDto.builder()
										.reviewId(review.getId())
										.userId(userId)
										.goodsId(goodsId)
										.reviewImage(review.getImage())
										.reviewTitle(review.getTitle())
										.reviewContents(review.getContents())
										.reviewCount(review.getCount())
										.reviewUserName(user.getName())
										.reviewDate(review.getCreatedAt())
										.goodsImage(goods.getImage())
										.goodsname(goods.getName())
										.build();
			reviewDtoList.add(reviewDto);
		}
		
		return reviewDtoList;
	}
	
	// 리뷰 작성 페이지
	public ReviewCreateDto reivewForGoodsInfo(int goodsId) {
		Goods goods = goodsService.getGoods(goodsId);
		
		ReviewCreateDto reviewCreateDto = ReviewCreateDto.builder()
														.goodsId(goodsId)
														.goodsName(goods.getName())
														.goodsImage(goods.getImage())
														.build();
		return reviewCreateDto;
	}
	
	// 리뷰 삭제
		public boolean deleteReview(int reviewId) {
				
				Optional<Review> optionalReview = reviewRepository.findById(reviewId);
				Review review = optionalReview.orElse(null);
				
				if (review != null) {
					FileManager.removeFile(review.getImage());
					reviewRepository.delete(review);
					return true;
				}else {
					return false;
				}
			}
	
	// 리뷰 추가
		public boolean insertReview(
				int goodsId
				, int userId
				, String title
				, String contents
				, int count
				, MultipartFile image
				) {
			// 이미지 경로 설정
			String urlPath = FileManager.saveFile(userId, image);
			
			Review review = Review.builder()
									.goodsId(goodsId)
									.userId(userId)
									.title(title)
									.contents(contents)
									.count(count)
									.image(urlPath)
									.build();
			
			review = reviewRepository.save(review);
			
			if (review != null) {
				return true;
			}else {
				return false;
			}
		}
	
}
