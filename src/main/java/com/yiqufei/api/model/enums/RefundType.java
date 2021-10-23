package com.yiqufei.api.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RefundType {

    TICKET_ALL_UNUSE(0),

    TICKET_PARTIAL_USED(1);

    private final int _type;

    private RefundType(int type) {
        _type = type;
    }

    @JsonValue
    public int toInt() {
        return _type;
    }
}
