package com.example.gymCrmSystem;

import com.example.gymCrmSystem.config.AppConfig;
import com.example.gymCrmSystem.repository.TrainingTypeRepository;
import com.example.gymCrmSystem.repository.UserRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GymCrmSystemApplication {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        AppConfig.class);

    TrainingTypeRepository bean = context.getBean(TrainingTypeRepository.class);
    System.out.println(bean.findAll());
  }
}
