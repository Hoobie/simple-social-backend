package com.example.services;

import com.example.entities.User;

public interface UsersService {
    User createIfNotExists(String name);
}
