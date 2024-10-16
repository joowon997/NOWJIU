package com.nowjoo.nowjiu.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.common.hash.HashingEncoder;
import com.nowjoo.nowjiu.user.domain.User;
import com.nowjoo.nowjiu.user.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private HashingEncoder encoder;
	
	public UserService(
			UserRepository userRepository
			, HashingEncoder encoder) {
		this.userRepository = userRepository;
		this.encoder = encoder;
	}
	// 모든 회원정보 조회
	public List<User> getUserInfo(){
		return userRepository.findAll();
	}
	
	// 로그인
	public User getUser(
			String loginId
			, String password) {
		
		String encryptPassword = encoder.encode(password);
		
		Optional<User> optionalUser = userRepository.findByloginIdAndPassword(loginId, encryptPassword);
		User user = optionalUser.orElse(null);
		
		return user;
	}
	
	// 중복확인
	public boolean isDuplicateId(String loginId) {
		
		Optional<User> optionalUser = userRepository.findByloginId(loginId);
		User user = optionalUser.orElse(null);
		
		if(user != null) {
			return true;
		}else {
			return false;
		}
	}
	
	// 회원가입
	public User addUser(
			String loginId
			, String password
			, String name
			, String email
			, String phoneNumber
			, int zipCode
			, String address
			, String detaileAddress
			) {
		String encryptPassword = encoder.encode(password);
		
		User user = User.builder()
					.loginId(loginId)
					.password(encryptPassword)
					.name(name)
					.email(email)
					.phoneNumber(phoneNumber)
					.zipCode(zipCode)
					.address(address)
					.detaileAddress(detaileAddress)
					.build();
		
		return userRepository.save(user);
	}
}
