package com.yiqufei.api.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaxType {

    EXCLUDING_TAX(0),

    INCLUDING_TAX(2);

    private final int _type;

    private TaxType(int type) {
        _type = type;
    }

    @JsonValue
    public int toInt() {
        return _type;
    }
}
