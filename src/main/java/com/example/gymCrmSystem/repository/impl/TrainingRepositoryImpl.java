package com.example.gymCrmSystem.repository.impl;

import com.example.gymCrmSystem.entity.Training;
import com.example.gymCrmSystem.repository.TrainingRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component
public class TrainingRepositoryImpl implements TrainingRepository {

  private final Map<Long, Training> repository = new HashMap<>();
  private final AtomicLong generatedId = new AtomicLong(1L);

  @Override
  public Training save(Training training) {
    long id = generatedId.getAndIncrement();
    training.setId(id);
    repository.put(id, training);
    return training;
  }

  @Override
  public Optional<Training> findById(Long primaryKey) {
    return Optional.ofNullable(repository.get(primaryKey));
  }

  @Override
  public Iterable<Training> findAll() {
    return repository.values();
  }

  @Override
  public long count() {
    return repository.size();
  }

  @Override
  public void delete(Training training) {
    Long userId = training.getId();
    repository.remove(userId);
  }

  @Override
  public boolean existsById(Long primaryKey) {
    return repository.containsKey(primaryKey);
  }
}
