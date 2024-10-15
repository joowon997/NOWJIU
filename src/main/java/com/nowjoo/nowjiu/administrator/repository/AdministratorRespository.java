package com.nowjoo.nowjiu.administrator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowjiu.administrator.domain.Administrator;

public interface AdministratorRespository extends JpaRepository<Administrator, Integer> {

	public Optional<Administrator> findByloginId(String loginId);

	public Optional<Administrator> findByloginIdAndPassword(String loginId, String password);

	public Optional<Administrator> findByIdAndName(int id, String name);
}
