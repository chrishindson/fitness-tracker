package com.chhin.fitnesstracker.service;

import com.chhin.fitnesstracker.entity.FTUser;

import java.util.Optional;

public class LoggedInUserService {

  private final Long userId;
  private final FTUserService ftUserService;

  public LoggedInUserService(Long userId, FTUserService ftUserService) {
    this.userId = userId;
    this.ftUserService = ftUserService;
  }

  public Optional<FTUser> getLoggedInUser() {
    return ftUserService.findById(userId);
  }
}
