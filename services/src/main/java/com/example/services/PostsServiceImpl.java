package com.example.services;

import com.example.entities.Post;
import com.example.entities.User;
import com.example.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public Collection<Post> getWall(String userName) {
        Optional<User> user = usersService.findByName(userName);
        if (user.isPresent()) {
            return user.get().getPosts();
        }
        throw new IllegalArgumentException(userName + " does not exist");
    }

    @Override
    public Collection<Post> getTimeline(String userName) {
        Optional<User> user = usersService.findByName(userName);
        if (!user.isPresent()) {
            throw new IllegalArgumentException(userName + " does not exist");
        }

        return user.get().getFollowed().stream()
                .flatMap(followee -> followee.getPosts().stream())
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
