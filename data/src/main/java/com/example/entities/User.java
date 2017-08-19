package com.example.entities;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class User {

    @NotEmpty
    private final String name;
    private final List<Post> posts = new LinkedList<>();
    private final List<User> followed = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<User> getFollowed() {
        return followed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
