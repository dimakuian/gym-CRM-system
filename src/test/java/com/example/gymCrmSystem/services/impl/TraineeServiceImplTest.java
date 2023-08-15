package com.example.gymCrmSystem.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.gymCrmSystem.entity.Trainee;
import com.example.gymCrmSystem.entity.User;
import com.example.gymCrmSystem.repository.TraineeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TraineeServiceImplTest {

  @Mock
  private TraineeRepository traineeRepository;

  private TraineeServiceImpl traineeService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    traineeService = new TraineeServiceImpl(traineeRepository);
  }

  @Test
  void testAddTrainee() {
    Trainee trainee = new Trainee();
    traineeService.addTrainee(trainee);
    verify(traineeRepository, times(1)).save(trainee);
  }

  @Test
  void testGetTraineeById() {
    long traineeId = 1L;
    Trainee trainee = new Trainee();
    trainee.setId(traineeId);

    when(traineeRepository.findById(traineeId)).thenReturn(Optional.of(trainee));

    Trainee result = traineeService.getTraineeById(traineeId);
    assertEquals(traineeId, result.getId());
  }

  @Test
  void testGetTraineeByIdNotFound() {
    long traineeId = 1L;

    when(traineeRepository.findById(traineeId)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> traineeService.getTraineeById(traineeId));
  }

  @Test
  void testGetAllTrainees() {
    List<Trainee> trainees = new ArrayList<>();
    Trainee trainee1 = Trainee.builder()
        .id(1L)
        .user(new User())
        .build();
    Trainee trainee2 = Trainee.builder()
        .id(2L)
        .user(new User())
        .build();
    trainees.add(trainee1);
    trainees.add(trainee2);

    when(traineeRepository.findAll()).thenReturn(trainees);

    List<Trainee> result = traineeService.getAllTrainees();
    assertEquals(trainees.size(), result.size());
  }

  @Test
  void testRemoveTraineeById() {
    long traineeId = 1L;
    Trainee trainee = new Trainee();
    trainee.setId(traineeId);

    when(traineeRepository.existsById(traineeId)).thenReturn(true);
    when(traineeRepository.findById(traineeId)).thenReturn(Optional.of(trainee));

    traineeService.removeTraineeById(traineeId);

    verify(traineeRepository, times(1)).delete(trainee);
  }

  @Test
  void testUpdateTraineeById() {
    long traineeId = 1L;
    Trainee updatedTrainee = new Trainee();

    when(traineeRepository.existsById(traineeId)).thenReturn(true);
    when(traineeRepository.save(updatedTrainee)).thenReturn(updatedTrainee);

    Trainee result = traineeService.updateTraineeById(traineeId, updatedTrainee);
    assertNotNull(result);
    assertEquals(updatedTrainee, result);
  }

  @Test
  void testUpdateTraineeByIdNotFound() {
    long traineeId = 1L;
    Trainee updatedTrainee = new Trainee();

    when(traineeRepository.existsById(traineeId)).thenReturn(false);

    Trainee result = traineeService.updateTraineeById(traineeId, updatedTrainee);
    assertNull(result);
  }
}
