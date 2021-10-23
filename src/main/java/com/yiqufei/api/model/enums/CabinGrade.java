package com.yiqufei.api.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CabinGrade {

    @JsonProperty("F")
    FIRST,

    @JsonProperty("C")
    COMMERCIAL,

    @JsonProperty("S")
    SUPER_ECONOMY,

    @JsonProperty("Y")
    ECONOMY;
}
