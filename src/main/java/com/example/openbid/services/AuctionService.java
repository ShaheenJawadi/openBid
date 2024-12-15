package com.example.openbid.services;


import com.example.openbid.model.Auction;

import java.util.Optional;

interface AuctionServiceInterface {
    Auction startAuction(Long itemId);
    Auction endAuction(Long itemId);
    Optional<Auction> getAuctionByItem(Long itemId);
}
public class AuctionService {
}
