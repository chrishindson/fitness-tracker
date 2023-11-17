package com.chhin.fitnesstracker.model;

import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginFormDTO {

  private String username;
  private String password;
}
