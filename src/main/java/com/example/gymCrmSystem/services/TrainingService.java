package com.example.gymCrmSystem.services;

import com.example.gymCrmSystem.entity.Training;
import java.util.List;

public interface TrainingService {

  void addTraining(Training training);

  Training getTrainingById(long id);
}
