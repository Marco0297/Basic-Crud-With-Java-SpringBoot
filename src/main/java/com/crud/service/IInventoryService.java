package com.crud.service;

import org.springframework.http.ResponseEntity;

import com.crud.model.Inventory;
import com.crud.response.InventoryResponseRest;

public interface IInventoryService {
	public ResponseEntity<InventoryResponseRest>save(Inventory inventory);
	public ResponseEntity<InventoryResponseRest>showAll();
	public ResponseEntity<InventoryResponseRest>findById(Long id);
	public ResponseEntity<InventoryResponseRest>update(Inventory inventory, Long id);
	public ResponseEntity<InventoryResponseRest>deleteById(Long id);
	
}
