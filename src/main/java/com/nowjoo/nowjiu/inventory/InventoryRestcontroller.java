package com.nowjoo.nowjiu.inventory;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InventoryRestcontroller {

	@PostMapping("/inventory-add")
	public Map<String, String> addInventory(){
		
	}
}
