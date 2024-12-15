package com.example.openbid.services;


import com.example.openbid.model.Item;

import java.util.List;
import java.util.Optional;

interface ItemServiceInterface {
    Item createItem(Item item);
    Optional<Item> getItemById(Long id);
    List<Item> getAllItems();
    List<Item> getItemsBySeller(Long sellerId);
}

public class ItemService {
}
