package com.example.repositories;

import com.example.entities.Post;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PostsRepository implements BasicRepository<UUID, Post> {

    private final Map<UUID, Post> posts = new ConcurrentHashMap<>();

    @Override
    public void create(UUID id, Post entity) {
        posts.put(id, entity);
    }

    @Override
    public Post read(UUID id) {
        return posts.get(id);
    }
}
