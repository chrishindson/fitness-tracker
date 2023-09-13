package com.chhin.fitnesstracker.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/** DTO for {@link com.chhin.fitnesstracker.entity.FTUser} */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FTUserDTO implements Serializable {
  private Long userId;
  private String username;
  private String forename;
  private String surname;
  private String password;
  private String emailAddress;
  private Integer height;
  private LocalDateTime lastUpdated;
  private LocalDateTime createdDate;
  private LocalDateTime lastAccessedDate;
  private LocalDate dateOfBirth;
}
