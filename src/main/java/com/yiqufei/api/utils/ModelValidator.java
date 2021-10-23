package com.yiqufei.api.utils;

import java.util.Set;

import com.yiqufei.api.model.Search;
import com.yiqufei.api.model.enums.TripType;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ModelValidator {

    private final Validator _validator;

    public ModelValidator() {
        _validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public void validate(Search.Request request) {
        if (TripType.RT.equals(request.getTripType())
                && (null == request.getRetDate() || request.getRetDate().isEmpty()))
            throw new IllegalArgumentException("retDate should not be null or empty since tripType is RT");

        // @todo add validation for date format `uuuuMMdd`.
 
        validateInternal(request);
    }

    public void validate(Search.Response response) {
        validateInternal(response);
    }

    private <T> void validateInternal(T request) {
        Set<ConstraintViolation<T>> violations = _validator.validate(request);
        for (ConstraintViolation<T> violation : violations) {
            if (null != violation)
                throw new IllegalArgumentException(String.format("%s: %s::%s", violation.getMessage(),
                        violation.getLeafBean().getClass().getSimpleName(), violation.getPropertyPath().toString()));
        }
    }
}
