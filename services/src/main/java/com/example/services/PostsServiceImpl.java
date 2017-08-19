package com.example.services;

import com.example.entities.Post;
import com.example.entities.User;
import com.example.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyList;

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
        user.getPosts().add(0, post);
        return post.getId();
    }

    @Override
    public Iterable<Post> getWall(String name) {
        Optional<User> user = usersService.findByName(name);
        if (user.isPresent()) {
            return user.get().getPosts();
        }
        return emptyList();
    }
}
