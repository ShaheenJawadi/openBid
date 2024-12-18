package com.example.openbid.repositories;

import com.example.openbid.model.Category;
import com.example.openbid.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item,Long> {
    List<Item> findBySellerId(Long sellerId);
    List<Item> findByCategory(Category category);

    Optional<Item> findBySlug(String slug);


}
