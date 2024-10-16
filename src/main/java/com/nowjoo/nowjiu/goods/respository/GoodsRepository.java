package com.nowjoo.nowjiu.goods.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowjiu.goods.domain.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {

}
