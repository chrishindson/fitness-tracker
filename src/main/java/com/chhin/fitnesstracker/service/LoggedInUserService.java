package com.chhin.fitnesstracker.service;

import com.chhin.fitnesstracker.entities.FTUser;

import java.util.Optional;

public class LoggedInUserService {
  private final int userId;
  private final FTUserService ftUserService;

  public LoggedInUserService(int userId, FTUserService ftUserService) {
    this.userId = userId;
    this.ftUserService = ftUserService;
  }

  public Optional<FTUser> getLoggedInUser() {
    return ftUserService.findById(userId);
  }
}
