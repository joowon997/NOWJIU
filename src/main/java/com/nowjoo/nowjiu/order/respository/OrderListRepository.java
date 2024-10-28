package com.nowjoo.nowjiu.order.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowjiu.order.domain.OrderList;

public interface OrderListRepository extends JpaRepository<OrderList, Integer>{

}
