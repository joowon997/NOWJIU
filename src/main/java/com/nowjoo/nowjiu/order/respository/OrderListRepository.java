package com.nowjoo.nowjiu.order.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowjiu.order.domain.OrderList;

public interface OrderListRepository extends JpaRepository<OrderList, Integer>{

	public List<OrderList> findByUserId(int userId);
	
	public List<OrderList> findByOrderId(int orderId);
}
