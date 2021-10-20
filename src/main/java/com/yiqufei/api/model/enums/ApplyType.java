package com.yiqufei.api.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplyType {
    
    PRESET_PRICE(0),

    APPLICATION_PRICE(1);

    private final int _type;

    private ApplyType(int type) {
        _type = type;
    }

    @JsonValue
    public int toInt() {
        return _type;
    }
}
