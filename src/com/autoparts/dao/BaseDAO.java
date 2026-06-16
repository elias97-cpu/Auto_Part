package com.autoparts.dao;

import java.util.List;

/**
 * Base DAO interface for generic CRUD operations.
 * Demonstrates Abstraction.
 * @param <T> The entity type
 */
public interface BaseDAO<T> {
    void add(T item);
    T getById(int id);
    List<T> getAll();
    void update(T item);
    void delete(int id);
}
