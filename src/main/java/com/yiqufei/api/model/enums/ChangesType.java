package com.yiqufei.api.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ChangesType {

    TICKET_ALL_UNUSE(0),

    TICKET_PARTIAL_USED(1);

    private final int _type;

    private ChangesType(int type) {
        _type = type;
    }

    @JsonValue
    public int toInt() {
        return _type;
    }
}
