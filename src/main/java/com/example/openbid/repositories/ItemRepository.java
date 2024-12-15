package com.example.openbid.repositories;

import com.example.openbid.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item,Long> {
    List<Item> findBySellerId(Long sellerId);
}
