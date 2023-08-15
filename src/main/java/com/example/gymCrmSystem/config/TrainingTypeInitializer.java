package com.example.gymCrmSystem.config;

import com.example.gymCrmSystem.entity.TrainingType;
import com.example.gymCrmSystem.repository.TrainingTypeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

@Component
public class TrainingTypeInitializer implements BeanPostProcessor, ApplicationContextAware {

  private ApplicationContext applicationContext;

  @Autowired
  private ConfigurableListableBeanFactory beanFactory;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    if (bean instanceof TrainingTypeRepository && applicationContext != null) {
      ObjectMapper objectMapper = new ObjectMapper();
      try {
        PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        Resource resource = resourceResolver.getResource("trainingTypes.json");
        TrainingTypeRepository trainingTypeRepository = (TrainingTypeRepository) bean;
        List<TrainingType> trainingTypes = objectMapper.readValue(resource.getFile(),
            new TypeReference<>() {
            });
        for (TrainingType trainingType : trainingTypes) {
          trainingTypeRepository.save(trainingType);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }
}