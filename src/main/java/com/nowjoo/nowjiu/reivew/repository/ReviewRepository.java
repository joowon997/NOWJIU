package com.nowjoo.nowjiu.reivew.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowjiu.reivew.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{

	public Optional<Review> findByUserIdAndGoodsId(int userId, int goodsId);
}
