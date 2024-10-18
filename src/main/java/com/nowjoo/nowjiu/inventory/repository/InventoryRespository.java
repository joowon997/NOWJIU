package com.nowjoo.nowjiu.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowjoo.nowjiu.inventory.domain.Inventory;

public interface InventoryRespository extends JpaRepository<Inventory, Integer> {

}
