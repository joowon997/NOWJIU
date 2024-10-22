package com.nowjoo.nowjiu.goods.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowjiu.goods.domain.GoodsImage;

public interface GoodsImageRepository extends JpaRepository<GoodsImage, Integer>{
	
	public List<GoodsImage> findByGoodsId(int goodsId);
}
