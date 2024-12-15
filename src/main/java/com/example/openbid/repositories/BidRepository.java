package com.example.openbid.repositories;

import com.example.openbid.model.Bid;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BidRepository extends CrudRepository<Bid,Long> {
    List<Bid> findByItemId(Long itemId);
}
