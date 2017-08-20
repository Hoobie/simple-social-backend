package com.example.services;

import com.example.entities.Post;

import java.util.Collection;
import java.util.UUID;

public interface PostsService {
    UUID createPost(String userName, Post post);

    Collection<Post> getWall(String userName);

    Collection<Post> getTimeline(String userName);
}
