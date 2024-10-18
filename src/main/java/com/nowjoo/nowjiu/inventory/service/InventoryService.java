package com.nowjoo.nowjiu.inventory.service;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.goods.domain.Goods;
import com.nowjoo.nowjiu.goods.serviece.GoodsService;
import com.nowjoo.nowjiu.inventory.domain.Inventory;
import com.nowjoo.nowjiu.inventory.repository.InventoryRespository;

@Service
public class InventoryService {

	private InventoryRespository inventoryRespository;
	
	public InventoryService(
			InventoryRespository inventoryRespository
			) {
		this.inventoryRespository = inventoryRespository;
	}
	
	public Inventory addInventory(int goodsId, String size, int count) {
		
		Inventory inventory = Inventory.builder()
									.goodsId(goodsId)
									.size(size)
									.count(count)
									.build();
		
		return inventoryRespository.save(inventory);
	}
	
}
