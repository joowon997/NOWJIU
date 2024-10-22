package com.nowjoo.nowjiu.cart.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowjiu.cart.domain.Cart;

public interface CartRespository extends JpaRepository<Cart, Integer> {

	public Optional<Cart> findByGoodsIdAndUserId(int goodsId, int userId);
	
	public List<Cart> findByUserId(int userId);
}
