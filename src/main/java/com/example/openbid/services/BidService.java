package com.example.openbid.services;


import com.example.openbid.model.Bid;

import java.util.List;

interface BidServiceInterface {
    Bid placeBid(Long itemId, Long bidderId, Bid bid);
    List<Bid> getBidsByItem(Long itemId);
}
public class BidService {
}
