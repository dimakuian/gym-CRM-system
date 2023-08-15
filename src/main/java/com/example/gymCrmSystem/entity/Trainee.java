package com.example.gymCrmSystem.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trainee {
  private Long id;

  private Date dateOfBirth;

  private String address;

  private User user;
}
