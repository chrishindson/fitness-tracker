package com.chhin.fitnesstracker.service;

import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.repository.FTUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FTUserDetailsService implements UserDetailsService {

  private final FTUserRepository userRepository;

  @Autowired
  public FTUserDetailsService(FTUserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    FTUser user = userRepository.findByUsernameIgnoreCase(username).orElse(null);
    if (user != null) {
      List<GrantedAuthority> authorityList = buildUserAuthority(user);

      return buildUserForAuthentication(user, authorityList);
    }
    throw new UsernameNotFoundException(username);
  }

  private List<GrantedAuthority> buildUserAuthority(FTUser user) {
    GrantedAuthority authority = new SimpleGrantedAuthority(
        user.getUserGroup().getUserGroupDescription());
    return Collections.singletonList(authority);
  }

  private User buildUserForAuthentication(FTUser user, List<GrantedAuthority> authorityList) {
    return new User(user.getUsername(), user.getPassword(), true, true, true, true, authorityList);
  }
}
