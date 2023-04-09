package com.chhin.fitnesstracker.config.security.provider;

import com.chhin.fitnesstracker.service.FTUserDetailsService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JdbcProvider extends DaoAuthenticationProvider {

  private final FTUserDetailsService ftUserDetailsService;

  public JdbcProvider(FTUserDetailsService ftUserDetailsService, PasswordEncoder passwordEncoder) {
    this.setUserDetailsService(ftUserDetailsService);
    this.setPasswordEncoder(passwordEncoder);
    this.ftUserDetailsService = ftUserDetailsService;
  }
}
