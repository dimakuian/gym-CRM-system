package com.example.gymCrmSystem.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.gymCrmSystem.entity.Trainer;
import com.example.gymCrmSystem.repository.TrainerRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TrainerServiceImplTest {

  @Mock
  private TrainerRepository trainerRepository;

  private TrainerServiceImpl trainerService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    trainerService = new TrainerServiceImpl(trainerRepository);
  }

  @Test
  void testAddTrainer() {
    Trainer trainer = new Trainer();
    trainerService.addTrainer(trainer);
    verify(trainerRepository, times(1)).save(trainer);
  }

  @Test
  void testGetTrainerById() {
    long trainerId = 1L;
    Trainer trainer = new Trainer();
    trainer.setId(trainerId);

    when(trainerRepository.findById(trainerId)).thenReturn(Optional.of(trainer));

    Trainer result = trainerService.getTrainerById(trainerId);
    assertEquals(trainerId, result.getId());
  }

  @Test
  void testGetTrainerByIdNotFound() {
    long trainerId = 1L;

    when(trainerRepository.findById(trainerId)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> {
      trainerService.getTrainerById(trainerId);
    });
  }

  @Test
  void testUpdateTrainerById() {
    long trainerId = 1L;
    Trainer updatedTrainer = new Trainer();

    when(trainerRepository.existsById(trainerId)).thenReturn(true);
    when(trainerRepository.save(updatedTrainer)).thenReturn(updatedTrainer);

    Trainer result = trainerService.updateTrainerById(trainerId, updatedTrainer);
    assertNotNull(result);
    assertEquals(updatedTrainer, result);
  }

  @Test
  void testUpdateTrainerByIdNotFound() {
    long trainerId = 1L;
    Trainer updatedTrainer = new Trainer();

    when(trainerRepository.existsById(trainerId)).thenReturn(false);

    Trainer result = trainerService.updateTrainerById(trainerId, updatedTrainer);
    assertNull(result);
  }
}
