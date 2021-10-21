package com.yiqufei.api.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * [Return successful when request result with no actual routes on searching
 * date]
 */
public enum Status {

    SUCCESS(0),

    OTHER_FAILURE_REASONS(3),

    REQUEST_PARAMETER_ERROR(4),

    PROGRAM_EXCEPTION(5);

    private final int _status;

    private Status(int status) {
        _status = status;
    }

    @JsonValue
    public int toInt() {
        return _status;
    }
}
