package com.example.services;

import com.example.entities.User;
import com.example.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User createIfNotExists(String name) {
        Optional<User> userOptional = findByName(name);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        User user = new User(name);
        usersRepository.create(name, user);
        return user;
    }

    @Override
    public void follow(String followerName, String followeeName) {
        Optional<User> follower = findByName(followerName);
        Optional<User> followee = findByName(followeeName);
        if (!follower.isPresent() || !followee.isPresent()) {
            throw new IllegalArgumentException("Both users must exist");
        }

        follower.get().getFollowed().add(followee.get());
    }

    @Override
    public Optional<User> findByName(String name) {
        return Optional.ofNullable(usersRepository.read(name));
    }
}
