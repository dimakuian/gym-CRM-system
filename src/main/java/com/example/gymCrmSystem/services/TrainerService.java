package com.example.gymCrmSystem.services;

import com.example.gymCrmSystem.entity.Trainer;

public interface TrainerService {

  void addTrainer(Trainer trainer);

  Trainer getTrainerById(long id);

  Trainer updateTrainerById(long id, Trainer trainer);
}
