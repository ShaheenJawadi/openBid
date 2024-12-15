package com.example.openbid.repositories;

import com.example.openbid.model.Bid;
import org.springframework.data.repository.CrudRepository;

public interface BidRepository extends CrudRepository<Bid,Long> {
}
