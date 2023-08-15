package com.example.gymCrmSystem.services;

import com.example.gymCrmSystem.entity.Trainee;
import java.util.List;

public interface TraineeService {

  void addTrainee(Trainee trainee);

  Trainee getTraineeById(long id);

  List<Trainee> getAllTrainees();

  void removeTraineeById(long id);

  Trainee updateTraineeById(long id, Trainee trainee);
}
