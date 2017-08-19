package com.example.services;

import com.example.entities.Post;

import java.util.UUID;

public interface PostsService {
    UUID createPost(String userName, Post post);
}
