package com.example.repositories;

import com.example.entities.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UsersRepository implements BasicRepository<String, User> {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public void create(String name, User entity) {
        users.put(name, entity);
    }

    @Override
    public User read(String name) {
        return users.get(name);
    }
}
