package com.nowjoo.nowjiu.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowjiu.user.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
