package com.nowjoo.nowjiu.goods.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowjiu.goods.domain.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {

	public Optional<Goods> findByName(String name);
	
	public List<Goods> findByCategoryId(int categoryId);

	public List<Goods> findByBrandId(int brandId);

	public List<Goods> findByNameContaining(String name);

	public List<Goods> findAllByOrderByIdDesc();
	
	public int countByCategoryId(int categoryId);

	public int countByBrandId(int brandId);

	public int countByNameContaining(String name);
	
	
}
