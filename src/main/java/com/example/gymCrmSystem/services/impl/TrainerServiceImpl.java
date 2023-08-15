package com.example.gymCrmSystem.services.impl;

import com.example.gymCrmSystem.entity.Trainer;
import com.example.gymCrmSystem.repository.TrainerRepository;
import com.example.gymCrmSystem.services.TrainerService;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrainerServiceImpl implements TrainerService {

  private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);
  private final TrainerRepository trainerRepository;

  public TrainerServiceImpl(@Autowired TrainerRepository trainerRepository) {
    this.trainerRepository = trainerRepository;
  }

  @Override
  public void addTrainer(Trainer trainer) {
    trainerRepository.save(trainer);
    logger.info("Added trainer with ID: {}", trainer.getId());
  }

  @Override
  public Trainer getTrainerById(long id) {
    return trainerRepository.findById(id)
        .orElseThrow(() -> {
          logger.warn("Trainer not found with ID: {}", id);
          return new NoSuchElementException("Trainer not found with ID: " + id);
        });
  }

  @Override
  public Trainer updateTrainerById(long id, Trainer trainer) {
    if (trainerRepository.existsById(id)) {
      Trainer updatedTrainer = trainerRepository.save(trainer);
      logger.info("Updated trainer with ID: {}", id);
      return updatedTrainer;
    } else {
      logger.warn("Trainer not found with ID: {}", id);
      return null;
    }
  }
}
