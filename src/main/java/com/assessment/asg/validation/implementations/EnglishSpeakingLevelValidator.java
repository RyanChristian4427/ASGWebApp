package com.assessment.asg.validation.implementations;

import com.assessment.asg.validation.annotations.ValidEnglishSpeakingLevel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnglishSpeakingLevelValidator implements ConstraintValidator<ValidEnglishSpeakingLevel, Integer> {

    @Override
    public void initialize(final ValidEnglishSpeakingLevel constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Integer speakingLevel, final ConstraintValidatorContext context) {
        return (0 < speakingLevel && speakingLevel < 7);
    }
}
