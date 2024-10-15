package com.nowjoo.nowjiu.administrator.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberDto {
	
	private int userId;
	private String loginId;
	private String email;
	private String name;
	private String phoneNumber;
}
