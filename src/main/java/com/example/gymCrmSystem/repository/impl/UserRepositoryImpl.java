package com.example.gymCrmSystem.repository.impl;

import com.example.gymCrmSystem.entity.User;
import com.example.gymCrmSystem.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

  private final Map<Long, User> repository = new HashMap<>();
  private final AtomicLong generatedId = new AtomicLong(1L);

  @Override
  public User save(User user) {
    long id = generatedId.getAndIncrement();
    user.setId(id);
    repository.put(id, user);
    return user;
  }

  @Override
  public Optional<User> findById(Long primaryKey) {
    return Optional.ofNullable(repository.get(primaryKey));
  }

  @Override
  public Iterable<User> findAll() {
    return repository.values();
  }

  @Override
  public long count() {
    return repository.size();
  }

  @Override
  public void delete(User entity) {
    Long userId = entity.getId();
    repository.remove(userId);
  }

  @Override
  public boolean existsById(Long primaryKey) {
    return repository.containsKey(primaryKey);
  }
}
