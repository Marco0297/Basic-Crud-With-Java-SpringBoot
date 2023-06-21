package com.crud.response;

import java.util.List;

import com.crud.model.Inventory;

public class InventoryResponse {
	
	private List<Inventory>inventory;

	public List<Inventory> getInventory() {
		return inventory;
	}

	public void setInventory(List<Inventory> inventory) {
		this.inventory = inventory;
	}
	
}
