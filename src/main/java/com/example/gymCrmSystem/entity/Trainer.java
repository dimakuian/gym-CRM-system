package com.example.gymCrmSystem.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Trainer {

  private Long id;
  private User userId;
  private List<TrainingType> specialization;
}
