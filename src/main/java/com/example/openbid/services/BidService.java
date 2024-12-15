package com.example.openbid.services;


import com.example.openbid.model.Bid;
import com.example.openbid.model.Item;
import com.example.openbid.model.User;
import com.example.openbid.repositories.BidRepository;
import com.example.openbid.repositories.ItemRepository;
import com.example.openbid.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

interface BidServiceInterface {
    Bid placeBid(Long itemId, Long bidderId, Bid bid);
    List<Bid> getBidsByItem(Long itemId);
}

@Service
public class BidService implements BidServiceInterface {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Bid placeBid(Long itemId, Long bidderId, Bid bid) {
        // Ensure the item and bidder exist
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        User bidder = userRepository.findById(bidderId)
                .orElseThrow(() -> new RuntimeException("Bidder not found"));

        List<Bid> existingBids = bidRepository.findByItemId(itemId);
        existingBids.stream()
                .map(Bid::getAmount)
                .max(Double::compareTo)
                .ifPresent(highestBid -> {
                    if (bid.getAmount() <= 0) {
                        throw new RuntimeException("Bid must be higher than the current highest bid");
                    }
                });

        bid.setItem(item);
        bid.setBidder(bidder);

        return bidRepository.save(bid);
    }

    @Override
    public List<Bid> getBidsByItem(Long itemId) {
        return bidRepository.findByItemId(itemId);
    }
}