package com.example.repositories;

public interface BasicRepository<I, E> {
    void create(I id, E entity);
}
