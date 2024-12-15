package com.example.openbid.repositories;

import com.example.openbid.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
