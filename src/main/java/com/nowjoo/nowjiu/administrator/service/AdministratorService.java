package com.nowjoo.nowjiu.administrator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.administrator.domain.Administrator;
import com.nowjoo.nowjiu.administrator.dto.AdministratorMainDto;
import com.nowjoo.nowjiu.administrator.dto.MemberDto;
import com.nowjoo.nowjiu.administrator.repository.AdministratorRespository;
import com.nowjoo.nowjiu.common.hash.HashingEncoder;
import com.nowjoo.nowjiu.goods.serviece.GoodsService;
import com.nowjoo.nowjiu.order.service.OrderService;
import com.nowjoo.nowjiu.user.domain.User;
import com.nowjoo.nowjiu.user.service.UserService;

@Service
public class AdministratorService {

	private AdministratorRespository administratorRespository;
	private UserService userService;
	private GoodsService goodsService;
	private OrderService orderService;
	private HashingEncoder encoder;
	
	public AdministratorService(
			AdministratorRespository administratorRespository
			, UserService userService
			, HashingEncoder encoder
			, GoodsService goodsService
			, OrderService orderService
			) {
		this.administratorRespository = administratorRespository;
		this.userService = userService;
		this.encoder = encoder;
		this.goodsService = goodsService;
		this.orderService = orderService;
	}
	// 메인페이지
	public AdministratorMainDto mainCount() {
		int userCount = userService.getUserCount();
		int goodsCount = goodsService.getGoodsCount();
		int orderCount = orderService.getOrderCount();
		int reviewCount = userService.getUserCount();
		
		AdministratorMainDto administratorMainDto = AdministratorMainDto.builder()
																		.userCount(userCount)
																		.goodsCount(goodsCount)
																		.orderCount(orderCount)
																		.build();
		return administratorMainDto;
	}
	
	// 관리자인지 아닌지
		public boolean isAdministrator(int id, String name){
			Optional<Administrator> optionalAdministrator = administratorRespository.findByIdAndName(id, name);
			Administrator administrator = optionalAdministrator.orElse(null);
			
			if (administrator != null) {
				return true;
			}else {
				return false;
			}
			
		}
	
	// 관리자 회원정보 조회
		public List<MemberDto> getMemberInfo(){
			List<User> userList = userService.getUserInfo();
			
			List<MemberDto> memberList = new ArrayList<>();
			for(User user:userList){
				
				MemberDto memberDto = MemberDto.builder()
										.userId(user.getId())
										.loginId(user.getLoginId())
										.email(user.getEmail())
										.name(user.getName())
										.phoneNumber(user.getPhoneNumber())
										.build();
			
				memberList.add(memberDto);
			}
			
			return memberList;
		}
	
	
	// 관리자 로그인
		public Administrator getAdministrator(
				String loginId
				, String password) {
			
			String encryptPassword = encoder.encode(password);
			
			Optional<Administrator> optionalAdministrator = administratorRespository.findByloginIdAndPassword(loginId, encryptPassword);
			Administrator administrator = optionalAdministrator.orElse(null);
			
			return administrator;
		}
	
	// 관리자 아이디 중복확인
		public boolean isDuplicateId(String loginId) {
			
			Optional<Administrator> optionalAdministrator = administratorRespository.findByloginId(loginId);
			Administrator administrator = optionalAdministrator.orElse(null);
			
			if(administrator != null) {
				return true;
			}else {
				return false;
			}
		}
	
	// 관리자 회원가입
	public Administrator addAdministrator(
			String loginId
			, String password
			, String name) {
		
		// 비밀번호 암호화
		String encryptPassword = encoder.encode(password);
		
		Administrator administrator = Administrator.builder()
										.loginId(loginId)
										.password(encryptPassword)
										.name(name)
										.build();
		
		return administratorRespository.save(administrator);
	}
	
}
