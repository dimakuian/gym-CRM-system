package com.example.gymCrmSystem.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Training {
  private Long id;

  private Trainee trainee;

  private Trainer trainer;

  private String trainingName;

  private TrainingType trainingType;

  private Date trainingDate;

  private int trainingDuration;
}
