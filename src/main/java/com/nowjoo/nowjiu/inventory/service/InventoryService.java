package com.nowjoo.nowjiu.inventory.service;

import org.springframework.stereotype.Service;

import com.nowjoo.nowjiu.inventory.repository.InventoryRespository;

@Service
public class InventoryService {

	private InventoryRespository inventoryRespository;
	
	public InventoryService(
			InventoryRespository inventoryRespository
			) {
		this.inventoryRespository = inventoryRespository;
	}
	
	
}
