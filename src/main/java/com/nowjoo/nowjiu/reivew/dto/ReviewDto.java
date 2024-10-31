package com.nowjoo.nowjiu.reivew.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewDto {

	private int reviewId;
	private int userId;
	private int goodsId;

	private String reviewImage;
	private String reviewTitle;
	private String reviewContents;
	private int reviewCount;
	private String reviewUserName;
	private LocalDateTime reviewDate;
	
	private String goodsImage;
	private String goodsname;
	
}
