package com.example.openbid.services;


import com.example.openbid.model.User;

import java.util.List;
import java.util.Optional;

interface UserServiceInterface {
    User registerUser(User user);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
}


public class UserService {
}
