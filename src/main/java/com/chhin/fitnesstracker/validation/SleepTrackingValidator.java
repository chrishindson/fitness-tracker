package com.chhin.fitnesstracker.validation;

import com.chhin.fitnesstracker.model.SleepTrackingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SleepTrackingValidator implements Validator {

  private static final Logger log = LoggerFactory.getLogger(SleepTrackingValidator.class);

  @Override
  public boolean supports(Class<?> clazz) {
    return false;
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (!(target instanceof SleepTrackingDTO dto)) {
    }
  }
}
