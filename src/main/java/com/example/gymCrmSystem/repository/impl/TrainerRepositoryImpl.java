package com.example.gymCrmSystem.repository.impl;

import com.example.gymCrmSystem.entity.Trainer;
import com.example.gymCrmSystem.repository.TrainerRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component
public class TrainerRepositoryImpl implements TrainerRepository {

  private final Map<Long, Trainer> repository = new HashMap<>();
  private final AtomicLong generatedId = new AtomicLong(1L);

  @Override
  public Trainer save(Trainer trainer) {
    long id = generatedId.getAndIncrement();
    trainer.setId(id);
    repository.put(id, trainer);
    return trainer;
  }

  @Override
  public Optional<Trainer> findById(Long primaryKey) {
    return Optional.ofNullable(repository.get(primaryKey));
  }

  @Override
  public Iterable<Trainer> findAll() {
    return repository.values();
  }

  @Override
  public long count() {
    return repository.size();
  }

  @Override
  public void delete(Trainer trainer) {
    Long userId = trainer.getId();
    repository.remove(userId);
  }

  @Override
  public boolean existsById(Long primaryKey) {
    return repository.containsKey(primaryKey);
  }
}
