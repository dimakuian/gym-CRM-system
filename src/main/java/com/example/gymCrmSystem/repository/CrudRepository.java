package com.example.gymCrmSystem.repository;

import java.util.Optional;

public interface CrudRepository<T, ID> {

  T save(T entity);

  Optional<T> findById(ID primaryKey);

  Iterable<T> findAll();

  long count();

  void delete(T entity);

  boolean existsById(ID primaryKey);
}
