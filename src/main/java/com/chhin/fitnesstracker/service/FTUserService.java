package com.chhin.fitnesstracker.service;

import com.chhin.fitnesstracker.entities.FTUser;
import com.chhin.fitnesstracker.repository.FTUserRepository;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FTUserService {

  private final FTUserRepository userRepository;

  public FTUserService(FTUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public FTUser findByUsername(OAuth2User principal) {
    if (principal != null) {
      String username = principal.getAttribute("prefered_username");
      if (username != null) {
        return findByUsername(username);
      }
    }
    return null;
  }

  public FTUser findByUsername(String username) {
    return userRepository.findByUsernameIgnoreCase(username).orElse(null);
  }

  public Optional<FTUser> findByUsernameOptional(String username) {
    return userRepository.findByUsernameIgnoreCase(username);
  }

  public void processOAuthPostLogin(DefaultOidcUser oidcUser) {

  }

  public Optional<FTUser> findById(Long id) {
    return userRepository.findById(id);
  }
}
