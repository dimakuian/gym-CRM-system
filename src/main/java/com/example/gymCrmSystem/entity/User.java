package com.example.gymCrmSystem.entity;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {

  private Long id;

  private String firstName;

  private String lastName;

  private String username;

  private String password;

  private Boolean isActive;

  public static class PasswordGenerator {

    private static final String CHARACTERS =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomPassword() {
      SecureRandom random = new SecureRandom();

      return IntStream.range(0, 10)
          .mapToObj(i -> CHARACTERS.charAt(random.nextInt(CHARACTERS.length())))
          .map(Object::toString)
          .collect(Collectors.joining());
    }
  }
}
