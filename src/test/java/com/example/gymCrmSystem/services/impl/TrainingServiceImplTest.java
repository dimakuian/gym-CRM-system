package com.example.gymCrmSystem.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.gymCrmSystem.entity.Training;
import com.example.gymCrmSystem.repository.TrainingRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TrainingServiceImplTest {

  @Mock
  private TrainingRepository trainingRepository;

  private TrainingServiceImpl trainingService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    trainingService = new TrainingServiceImpl(trainingRepository);
  }

  @Test
  void testAddTraining() {
    Training training = new Training();
    trainingService.addTraining(training);
    verify(trainingRepository, times(1)).save(training);
  }

  @Test
  void testGetTrainingById() {
    long trainingId = 1L;
    Training training = new Training();
    training.setId(trainingId);

    when(trainingRepository.findById(trainingId)).thenReturn(Optional.of(training));

    Training result = trainingService.getTrainingById(trainingId);
    assertEquals(trainingId, result.getId());
  }

  @Test
  void testGetTrainingByIdNotFound() {
    long trainingId = 1L;

    when(trainingRepository.findById(trainingId)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> trainingService.getTrainingById(trainingId));
  }
}
