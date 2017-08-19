package com.example.services;

import java.util.UUID;

public interface PostsService {
    UUID createPost(String userName, String message);
}
