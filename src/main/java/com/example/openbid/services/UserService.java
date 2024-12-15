package com.example.openbid.services;


import com.example.openbid.model.User;
import com.example.openbid.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

interface UserServiceInterface {
    User registerUser(User user);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
}

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

}
