package com.example.gymCrmSystem.repository.impl;

import com.example.gymCrmSystem.entity.TrainingType;
import com.example.gymCrmSystem.repository.TrainingTypeRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component
public class TrainingTypeRepositoryImpl implements TrainingTypeRepository {

  private final Map<Long, TrainingType> repository = new HashMap<>();
  private final AtomicLong generatedId = new AtomicLong(1L);

  @Override
  public TrainingType save(TrainingType trainingType) {
    long id = generatedId.getAndIncrement();
    trainingType.setId(id);
    repository.put(id, trainingType);
    return trainingType;
  }

  @Override
  public Optional<TrainingType> findById(Long primaryKey) {
    return Optional.ofNullable(repository.get(primaryKey));
  }

  @Override
  public Iterable<TrainingType> findAll() {
    return repository.values();
  }

  @Override
  public long count() {
    return repository.size();
  }

  @Override
  public void delete(TrainingType trainingType) {
    Long trainingTypeId = trainingType.getId();
    repository.remove(trainingTypeId);
  }

  @Override
  public boolean existsById(Long primaryKey) {
    return repository.containsKey(primaryKey);
  }
}
