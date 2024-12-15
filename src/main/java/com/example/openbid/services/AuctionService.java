package com.example.openbid.services;


import com.example.openbid.model.Auction;
import com.example.openbid.model.Bid;
import com.example.openbid.model.Item;
import com.example.openbid.repositories.AuctionRepository;
import com.example.openbid.repositories.BidRepository;
import com.example.openbid.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

interface AuctionServiceInterface {
    Auction startAuction(Long itemId);
    Auction endAuction(Long itemId);
    Optional<Auction> getAuctionByItem(Long itemId);
}

@Service
public class AuctionService  implements AuctionServiceInterface {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BidRepository bidRepository;

    @Override
    public Auction startAuction(Long itemId) {


        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Optional<Auction> existingAuction = auctionRepository.findByItemId(itemId);
        if (existingAuction.isPresent() && existingAuction.get().getStatus() != Auction.Status.PENDING) {
            throw new RuntimeException("Auction has already started");
        }

        Auction auction = new Auction();
        auction.setItem(item);
        auction.setStatus(Auction.Status.ONGOING);

        return auctionRepository.save(auction);
    }

    @Override
    public Auction endAuction(Long itemId) {
        Auction auction = auctionRepository.findByItemId(itemId)
                .orElseThrow(() -> new RuntimeException("Auction not found"));

        List<Bid> bids = bidRepository.findByItemId(itemId);
        bids.stream()
                .max(Comparator.comparingDouble(Bid::getAmount))
                .ifPresent(highestBid -> {
                    auction.setWinner(highestBid.getBidder());
                    auction.setFinalPrice(highestBid.getAmount());
                });

        auction.setStatus(Auction.Status.COMPLETED);

        return auctionRepository.save(auction);
    }

    @Override
    public Optional<Auction> getAuctionByItem(Long itemId) {
        return auctionRepository.findByItemId(itemId);
    }
}