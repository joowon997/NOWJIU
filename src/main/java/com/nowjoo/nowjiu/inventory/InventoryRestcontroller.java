package com.nowjoo.nowjiu.inventory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nowjoo.nowjiu.inventory.domain.Inventory;
import com.nowjoo.nowjiu.inventory.service.InventoryService;

@RestController
@RequestMapping("/api")
public class InventoryRestcontroller {
	
	private InventoryService inventoryService;
	
	public InventoryRestcontroller(
			InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

//	@PostMapping("/inventory-add")
//	public Map<String, String> addInventory(
//			@RequestParam ("name") String name
//			, @RequestParam ("size") String size
//			, @RequestParam ("count") int count
//			){
//		
//		Inventory inventory = inventoryService.addInventory(name, size, count);
//		
//		Map<String, String> resultMap = new HashMap<>();
//		
//		if (inventory != null) {
//			resultMap.put("result", "success");
//		} else {
//			resultMap.put("result", "fail");
//		}
//		
//		return resultMap;
//	}
}
