package com.yiqufei.api.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PriceType {

    NORMAL_PRICE(0),

    STUDENTS_PRICE(1);

    private final int _type;

    private PriceType(int type) {
        _type = type;
    }

    @JsonValue
    public int toInt() {
        return _type;
    }
}
