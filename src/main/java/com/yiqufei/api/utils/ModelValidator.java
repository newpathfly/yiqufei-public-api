package com.yiqufei.api.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Set;

import com.yiqufei.api.model.Routing;
import com.yiqufei.api.model.Search;
import com.yiqufei.api.model.Segment;
import com.yiqufei.api.model.enums.TripType;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public class ModelValidator {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter //
            .ofPattern("uuuuMMdd") //
            .withResolverStyle(ResolverStyle.STRICT);

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter //
            .ofPattern("uuuuMMddHHmm") //
            .withResolverStyle(ResolverStyle.STRICT);

    private final Validator _validator;

    public ModelValidator() {
        _validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public void validate(Search.Request request) {
        validateInternal(request);

        validateDate(request.getFromDate());

        if (TripType.RT.equals(request.getTripType())) {
            validateDate(request.getRetDate());
        }
    }

    public void validate(Search.Response response) {
        validateInternal(response);

        response.getRoutings().forEach(this::validateInternal);
    }

    public void validate(Routing routing) {
        validateInternal(routing);

        routing.getFromSegments().forEach(this::validate);
        routing.getRetSegments().forEach(this::validate);
    }

    public void validate(Segment segment) {
        validateInternal(segment);

        validateDateTime(segment.getDepTime());
        validateDateTime(segment.getArrTime());
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
        if (null == date) {
            throw new IllegalArgumentException("date should not be null.");
        }

        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(String.format("date string is not in YYYYMMDD format: `%s`", date), e);
        }

        if (localDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(String.format("date `%s` should not be earlier than today `%s`", date,
                    LocalDate.now().format(DATE_FORMATTER)));
        }
    }

    private static void validateDateTime(String dateTime) {
        if (null == dateTime) {
            throw new IllegalArgumentException("dateTime should not be null.");
        }

        try {
            LocalDateTime.parse(dateTime, DATETIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    String.format("dateTime string is not in YYYYMMDDHHmm format: `%s`", dateTime), e);
        }
    }
}
