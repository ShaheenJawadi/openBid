package com.example.openbid.repositories;

import com.example.openbid.model.Auction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuctionRepository extends CrudRepository<Auction,Long> {
    Optional<Auction> findByItemId(Long itemId);
}
