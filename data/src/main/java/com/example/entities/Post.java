package com.example.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.UUID;

public class Post implements Comparable<Post> {

    private final UUID id = UUID.randomUUID();

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private OffsetDateTime date = OffsetDateTime.now();

    @NotNull
    @Size(max = 140)
    private String message;

    private Post() {
        // no-arg Jackson constructor
    }

    public Post(String message) {
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
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

    @Override
    public int compareTo(Post other) {
        if (other.equals(this)) return 0;
        return other.date.compareTo(date);
    }
}
