package com.chhin.fitnesstracker.validation;

import com.chhin.fitnesstracker.model.FoodTypeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FoodTypeValidator implements Validator {

  private static final Logger log = LoggerFactory.getLogger(FoodTypeValidator.class);

  @Override
  public boolean supports(Class<?> clazz) {
    return false;
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (!(target instanceof FoodTypeDTO dto)) {
    }
  }
}
