package com.yiqufei.api.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Set;

import com.yiqufei.api.model.Search;
import com.yiqufei.api.model.enums.TripType;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ModelValidator {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter //
            .ofPattern("uuuuMMdd") //
            .withResolverStyle(ResolverStyle.STRICT);

    private final Validator _validator;

    public ModelValidator() {
        _validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public void validate(Search.Request request) {

        validateDate(request.getFromDate());

        if (TripType.RT.equals(request.getTripType())) {
            validateDate(request.getRetDate());
        }

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

    private static void validateDate(String date) {
        LocalDate fromDate;
        try {
            fromDate = LocalDate.parse(date, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(String.format("date string is not in YYYYMMDD format: `%s`", date), e);
        }

        if (fromDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(String.format("date `%s` should not be earlier than today `%s`", date,
                    LocalDate.now().format(DATE_FORMATTER)));
        }
    }
}
