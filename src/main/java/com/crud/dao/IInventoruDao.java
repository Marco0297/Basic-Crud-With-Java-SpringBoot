package com.crud.dao;

import org.springframework.data.repository.CrudRepository;

import com.crud.model.Inventory;

public interface IInventoruDao extends CrudRepository<Inventory, Long>{

}
