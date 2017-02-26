package com.pizzashop.annotations;

import com.pizzashop.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by bartek on 2/26/17.
 */
public class LoginValidator implements ConstraintValidator<IsNewLogin,String> {
    IsNewLogin isNewLoginAnnotation;

    @Autowired
    ManagerRepository managerRepository;

    @Override
    public void initialize(IsNewLogin isNewLoginAnnotation) {
        this.isNewLoginAnnotation = isNewLoginAnnotation;
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext context) {
        return managerRepository.findByeMail(login)==null;
    }
}
