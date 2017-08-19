package com.example.services;

import com.example.entities.Post;
import com.example.entities.User;
import com.example.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postRepository;
    private final UsersService usersService;

    @Autowired
    public PostsServiceImpl(PostsRepository postsRepository, UsersService usersService) {
        this.postRepository = postsRepository;
        this.usersService = usersService;
    }

    @Override
    public UUID createPost(String userName, Post post) {
        User user = usersService.createIfNotExists(userName);
        postRepository.create(post.getId(), post);
        user.getPosts().add(post);
        return post.getId();
    }
}
