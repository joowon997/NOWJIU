package com.nowjoo.nowjiu.category.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowjiu.category.domian.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	public Optional<Category> findByName(String name);
}
