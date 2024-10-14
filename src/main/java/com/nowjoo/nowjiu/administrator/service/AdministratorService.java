package com.nowjoo.nowjiu.administrator.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.administrator.domain.Administrator;
import com.nowjoo.nowjiu.administrator.repository.AdministratorRespository;
import com.nowjoo.nowjiu.common.hash.HashingEncoder;

@Service
public class AdministratorService {

	private AdministratorRespository administratorRespository;
	private HashingEncoder encoder;
	
	public AdministratorService(
			AdministratorRespository administratorRespository
			, HashingEncoder encoder
			) {
		this.administratorRespository = administratorRespository;
		this.encoder = encoder;
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
