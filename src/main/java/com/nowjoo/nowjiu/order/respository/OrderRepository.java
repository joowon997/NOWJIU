package com.nowjoo.nowjiu.order.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowjiu.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	public Optional<Order> findByMerchantUid(String merchantUid);
}
