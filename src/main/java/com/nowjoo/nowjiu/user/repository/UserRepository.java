package com.nowjoo.nowjiu.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowjiu.user.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public Optional<User> findByloginId(String loginId);

	public Optional<User> findByloginIdAndPassword(String loginId, String password);
	
}
