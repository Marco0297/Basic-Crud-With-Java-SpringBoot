package com.crud.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.model.Inventory;
import com.crud.response.InventoryResponseRest;
import com.crud.service.IInventoryService;

@RestController
@RequestMapping("/Inventory")
public class Controller {
	
	@Autowired
	private IInventoryService service;
	
	@PostMapping("/Save")
	public ResponseEntity<InventoryResponseRest>save(@RequestBody Inventory inventory){
		ResponseEntity<InventoryResponseRest> response = service.save(inventory);
		return response;
	}
	
	@GetMapping("/ShowInventory")
	public ResponseEntity<InventoryResponseRest>findAll(){
		ResponseEntity<InventoryResponseRest> response = service.showAll();
		Map<String, String> mensaje = new HashMap<>();
		mensaje.put("contenido", "EXITOSO");
		return response;
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<InventoryResponseRest>findById(@PathVariable Long id){
		ResponseEntity<InventoryResponseRest> response = service.findById(id);
		return response;
	} 
	
	@PostMapping("/Update/{id}")
	public ResponseEntity<InventoryResponseRest>update(@RequestBody Inventory inventory, @PathVariable Long id){
		ResponseEntity<InventoryResponseRest> response = service.update(inventory, id);
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<InventoryResponseRest>delete(@PathVariable Long id){
		ResponseEntity<InventoryResponseRest> response = service.deleteById(id);
		return response;
	}

}
