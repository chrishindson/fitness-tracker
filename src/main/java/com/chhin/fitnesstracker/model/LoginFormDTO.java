package com.chhin.fitnesstracker.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginFormDTO {

  private String username;
  private String password;
}
