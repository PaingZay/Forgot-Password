package com.example.WebAppClient.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.WebAppClient.Model.Business;


@Component
public class BusinessValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return Business.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    System.out.println(target);
    
  }
}
