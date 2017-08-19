package com.example.services;

import com.example.entities.User;
import com.example.repositories.BasicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private BasicRepository<String, User> usersRepository;

    @Override
    public void createIfNotExists(String name) {
        throw new UnsupportedOperationException();
    }
}
