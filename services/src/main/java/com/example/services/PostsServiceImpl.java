package com.example.services;

import com.example.entities.Post;
import com.example.repositories.BasicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private BasicRepository<UUID, Post> repository;

    @Override
    public UUID createPost(String userName, String message) {
        throw new UnsupportedOperationException();
    }
}
