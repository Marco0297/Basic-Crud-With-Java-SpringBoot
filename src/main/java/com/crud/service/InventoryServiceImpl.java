package com.crud.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.dao.IInventoruDao;
import com.crud.model.Inventory;
import com.crud.response.InventoryResponseRest;

@Service
public class InventoryServiceImpl implements IInventoryService{
	
	@Autowired
	private IInventoruDao inventoryDao;

	@Override
	@Transactional
	public ResponseEntity<InventoryResponseRest> save(Inventory inventory) {
		InventoryResponseRest response = new InventoryResponseRest();
		List<Inventory>list = new ArrayList<>();
		try {
			Inventory inventorySave = inventoryDao.save(inventory);
			if(inventorySave !=null) {
				list.add(inventorySave);
				response.getInventoryResponse().setInventory(list);
			}else {
				response.setMetadata("Respuesta NO ok", "-1", "Categoria NO guardada");
			}
		}catch(Exception e) {
			response.setMetadata("Respuesta NO ok", "-1", "Error al guardar categoria");
			e.getStackTrace();
			return new ResponseEntity<InventoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<InventoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	@Transactional
	public ResponseEntity<InventoryResponseRest> showAll() {
		InventoryResponseRest response = new InventoryResponseRest();
		try {
			List<Inventory>inventory = (List<Inventory>)inventoryDao.findAll();
			response.getInventoryResponse().setInventory(inventory);
			response.setMetadata("Respuesta ok", "00", "Respuesta Exitosa");
		}catch(Exception e) {
			response.setMetadata("Respuesta NO ok", "-1", "Error al consultar");
			e.getStackTrace();// imprime exception
		}
		return new ResponseEntity<InventoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<InventoryResponseRest> findById(Long id) {
		InventoryResponseRest response = new InventoryResponseRest();
		List<Inventory>list = new ArrayList<>();
		try {
			Optional<Inventory>inventory = inventoryDao.findById(id);
			if(inventory.isPresent()) {
				list.add(inventory.get());
				response.getInventoryResponse().setInventory(list);
				response.setMetadata("Respuesta ok", "00", "Categoria Encontrada");
			}
			
		}catch(Exception e) {
			response.setMetadata("Respuesta NO ok", "-1", "Error al consultar por id");
			e.getStackTrace();// imprime exception
			return new ResponseEntity<InventoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<InventoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<InventoryResponseRest> deleteById(Long id) {
		InventoryResponseRest response = new InventoryResponseRest();
		try {
			inventoryDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Registro Eliminado");
		}catch(Exception e) {
			response.setMetadata("Respuesta NO ok", "-1", "Error Al Eliminar");
			e.getStackTrace();// imprime exception
			return new ResponseEntity<InventoryResponseRest>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<InventoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<InventoryResponseRest> update(Inventory inventory, Long id) {
		InventoryResponseRest response = new InventoryResponseRest();
		List<Inventory>list = new ArrayList<>();
		try {
			Optional<Inventory>inventorySearch = inventoryDao.findById(id);
			if(inventorySearch.isPresent()) {
				inventorySearch.get().setNombre(inventory.getNombre());
				inventorySearch.get().setDescripcion(inventory.getDescripcion());
				
				Inventory inventoryToUpdate = inventoryDao.save(inventorySearch.get());
				
				if(inventoryToUpdate != null) {
					list.add(inventoryToUpdate);
					response.getInventoryResponse().setInventory(list);
					response.setMetadata("Respuesta ok", "00", "Categoria Actualizada");
				}else {
					response.setMetadata("Respuesta NO ok", "-1", "Categoria NO actualizada");
					return new ResponseEntity<InventoryResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}else {
				response.setMetadata("Respuesta NO ok", "-1", "Categoria NO actualizada");
				return new ResponseEntity<InventoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			response.setMetadata("Respuesta NO ok", "-1", "Error al consultar por id");
			e.getStackTrace();// imprime exception
			return new ResponseEntity<InventoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<InventoryResponseRest>(response, HttpStatus.OK);
	}

}
