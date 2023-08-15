package com.example.gymCrmSystem.services.impl;

import com.example.gymCrmSystem.entity.Trainee;
import com.example.gymCrmSystem.repository.TraineeRepository;
import com.example.gymCrmSystem.services.TraineeService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TraineeServiceImpl implements TraineeService {

  private static final Logger logger = LoggerFactory.getLogger(TraineeServiceImpl.class);
  private final TraineeRepository traineeRepository;

  public TraineeServiceImpl(@Autowired TraineeRepository traineeRepository) {
    this.traineeRepository = traineeRepository;
  }

  @Override
  public void addTrainee(Trainee trainee) {
    traineeRepository.save(trainee);
    logger.info("Added trainee with ID: {}", trainee.getId());
  }

  @Override
  public Trainee getTraineeById(long id) {
    return traineeRepository.findById(id)
        .orElseThrow(() -> {
          logger.warn("Trainee not found with ID: {}", id);
          return new NoSuchElementException("Trainee not found with ID: " + id);
        });
  }

  @Override
  public List<Trainee> getAllTrainees() {
    logger.info("Getting all trainees");
    return StreamSupport.stream(traineeRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public void removeTraineeById(long id) {
    if (traineeRepository.existsById(id)) {
      Trainee trainee = traineeRepository.findById(id).get();
      traineeRepository.delete(trainee);
      logger.info("Deleted trainee with ID: {}", id);
    } else {
      logger.warn("Trainee not found with ID: {}", id);
    }
  }

  @Override
  public Trainee updateTraineeById(long id, Trainee trainee) {
    if (traineeRepository.existsById(id)) {
      Trainee updatedTrainee = traineeRepository.save(trainee);
      logger.info("Updated trainee with ID: {}", id);
      return updatedTrainee;
    } else {
      logger.warn("Trainee not found with ID: {}", id);
      return null;
    }
  }
}
