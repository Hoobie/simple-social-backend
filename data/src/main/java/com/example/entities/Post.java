package com.example.entities;

import java.time.OffsetDateTime;
import java.util.UUID;

public class Post {
    private final UUID id = UUID.randomUUID();
    private final OffsetDateTime date = OffsetDateTime.now();
    private String message;

    public Post(String message) {
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        return id.equals(post.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
