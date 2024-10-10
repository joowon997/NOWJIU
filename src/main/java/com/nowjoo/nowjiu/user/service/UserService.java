package com.nowjoo.nowjiu.user.service;

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
