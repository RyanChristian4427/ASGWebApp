package com.assessment.drones.validation.implementations;

import com.assessment.drones.validation.annotations.ValidEnglishSpeakingLevel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnglishSpeakingLevelValidator implements ConstraintValidator<ValidEnglishSpeakingLevel, Integer> {

    @Override
    public void initialize(ValidEnglishSpeakingLevel constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer speakingLevel, ConstraintValidatorContext context){
        return (0<speakingLevel && speakingLevel<7);
    }
}