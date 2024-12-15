package com.example.openbid.repositories;

import com.example.openbid.model.Auction;
import org.springframework.data.repository.CrudRepository;

public interface AuctionRepository extends CrudRepository<Auction,Long> {
}
