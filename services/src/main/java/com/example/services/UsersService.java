package com.example.services;

import com.example.entities.User;

import java.util.Optional;

public interface UsersService {
    User createIfNotExists(String name);

    Optional<User> findByName(String name);
}
