package com.example.openbid.services;


import com.example.openbid.model.Category;
import com.example.openbid.model.Item;
import com.example.openbid.repositories.CategoryRepository;
import com.example.openbid.repositories.ItemRepository;
import com.example.openbid.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

interface ItemServiceInterface {
    Item createItem(Item item, List<String> tags, Long categoryId);
    Optional<Item> getItemById(Long id);
    List<Item> getAllItems();
    List<Item> getItemsBySeller(Long sellerId);
    List<Item> getItemsByCategory(Long sellerId);
}


@Service
public class ItemService  implements ItemServiceInterface {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Item createItem(Item item, List<String> tags, Long categoryId) {
        userRepository.findById(item.getSeller().getId())
                .orElseThrow(() -> new RuntimeException("Seller not found"));
        if (tags != null && !tags.isEmpty()) {
            String tagString = String.join(",", tags);
            item.setTags(tagString);
        }


        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        item.setCategory(category);

        item.setSlug(generateSlug(item.getTitle()));

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


    @Override
    public List<Item> getItemsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return itemRepository.findByCategory(category);  // Fetch items by category
    }


    private String generateSlug(String title) {
        String normalized = Normalizer.normalize(title, Normalizer.Form.NFD);
        String slug = Pattern.compile("\\p{InCombiningDiacriticalMarks}+").matcher(normalized).replaceAll("");
        slug = slug.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("-$", "").replaceAll("^-", "");
        return slug;
    }
}