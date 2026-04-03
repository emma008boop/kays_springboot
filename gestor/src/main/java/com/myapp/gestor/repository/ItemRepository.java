package com.myapp.gestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.gestor.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
