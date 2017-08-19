package com.example.repositories;

import com.example.entities.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UsersRepository implements BasicRepository<String, User> {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void create(String id, User entity) {
        users.put(id, entity);
    }
}
