package com.example.services;

import com.example.entities.User;
import com.example.repositories.BasicRepository;
import com.example.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User createIfNotExists(String name) {
        User user = usersRepository.read(name);
        if (user == null) {
            user = new User(name);
            usersRepository.create(name, user);
        }
        return user;
    }
}
