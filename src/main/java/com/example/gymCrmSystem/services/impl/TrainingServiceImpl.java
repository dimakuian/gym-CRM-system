package com.example.gymCrmSystem.services.impl;

import com.example.gymCrmSystem.entity.Training;
import com.example.gymCrmSystem.repository.TrainingRepository;
import com.example.gymCrmSystem.services.TrainingService;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrainingServiceImpl implements TrainingService {

  private static final Logger logger = LoggerFactory.getLogger(TrainingServiceImpl.class);
  private final TrainingRepository trainingRepository;

  public TrainingServiceImpl(@Autowired TrainingRepository trainingRepository) {
    this.trainingRepository = trainingRepository;
  }

  @Override
  public void addTraining(Training training) {
    trainingRepository.save(training);
    logger.info("Added training with ID: {}", training.getId());
  }

  @Override
  public Training getTrainingById(long id) {
    return trainingRepository.findById(id)
        .orElseThrow(() -> {
          logger.warn("Training not found with ID: {}", id);
          return new NoSuchElementException("Training not found with ID: " + id);
        });
  }
}
