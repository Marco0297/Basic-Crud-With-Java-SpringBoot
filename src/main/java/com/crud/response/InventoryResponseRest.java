package com.crud.response;

public class InventoryResponseRest extends  ResponseRest{

	private InventoryResponse inventoryResponse = new InventoryResponse();

	public InventoryResponse getInventoryResponse() {
		return inventoryResponse;
	}

	public void setInventoryResponse(InventoryResponse inventoryResponse) {
		this.inventoryResponse = inventoryResponse;
	}
}
