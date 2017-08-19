package com.example.repositories;

import com.example.entities.Post;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class PostsRepository implements BasicRepository<UUID, Post> {

    private final Map<UUID, Post> posts = new LinkedHashMap<>();

    @Override
    public void create(UUID id, Post entity) {
        posts.put(id, entity);
    }

    @Override
    public Post read(UUID id) {
        return posts.get(id);
    }
}
