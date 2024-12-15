package com.example.openbid.services;


import com.example.openbid.model.Item;
import com.example.openbid.repositories.ItemRepository;
import com.example.openbid.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

interface ItemServiceInterface {
    Item createItem(Item item);
    Optional<Item> getItemById(Long id);
    List<Item> getAllItems();
    List<Item> getItemsBySeller(Long sellerId);
}


@Service
public class ItemService  implements ItemServiceInterface {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Item createItem(Item item) {
        userRepository.findById(item.getSeller().getId())
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        return itemRepository.save(item);
    }

    @Override
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public List<Item> getAllItems() {
        return (List<Item>) itemRepository.findAll();
    }

    @Override
    public List<Item> getItemsBySeller(Long sellerId) {
        return itemRepository.findBySellerId(sellerId);
    }
}