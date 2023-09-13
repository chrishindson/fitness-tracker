package com.chhin.fitnesstracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FitnessTrackerApplication {

  public static void main(String[] args) {
    SpringApplication.run(FitnessTrackerApplication.class, args);
  }
}
