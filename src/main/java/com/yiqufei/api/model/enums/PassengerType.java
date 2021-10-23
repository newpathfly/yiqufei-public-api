package com.yiqufei.api.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PassengerType {

    ADULT(0),

    CHILD(1),

    INFANT(2);

    private final int _type;

    private PassengerType(int type) {
        _type = type;
    }

    @JsonValue
    public int toInt() {
        return _type;
    }
}
