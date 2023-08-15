package com.example.gymCrmSystem.repository.impl;

import com.example.gymCrmSystem.entity.Trainee;
import com.example.gymCrmSystem.repository.TraineeRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component
public class TraineeRepositoryImpl implements TraineeRepository {

  private final Map<Long, Trainee> repository = new HashMap<>();
  private final AtomicLong generatedId = new AtomicLong(1L);

  @Override
  public Trainee save(Trainee trainee) {
    long id = generatedId.getAndIncrement();
    trainee.setId(id);
    repository.put(id, trainee);
    return trainee;
  }

  @Override
  public Optional<Trainee> findById(Long primaryKey) {
    return Optional.ofNullable(repository.get(primaryKey));
  }

  @Override
  public Iterable<Trainee> findAll() {
    return repository.values();
  }

  @Override
  public long count() {
    return repository.size();
  }

  @Override
  public void delete(Trainee trainee) {
    if (trainee.getId() != null) {
      repository.remove(trainee.getId());
    }
  }

  @Override
  public boolean existsById(Long primaryKey) {
    return repository.containsKey(primaryKey);
  }
}
