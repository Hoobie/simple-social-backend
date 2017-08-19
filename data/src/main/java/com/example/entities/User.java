package com.example.entities;

import javax.validation.constraints.NotNull;
import java.util.*;

public final class User {

    @NotNull
    private final String name;
    private final Set<Post> posts = new LinkedHashSet<>();
    private final Set<User> followed = new HashSet<>();

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public Set<User> getFollowed() {
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
