package com.yiqufei.api.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Eligibility {
    
    @JsonProperty("NOR")
    NORMAL_ADULT,

    @JsonProperty("LAB")
    LABOR,

    @JsonProperty("SEA")
    SEAMAN,

    @JsonProperty("SNR")
    SENIOR,

    @JsonProperty("STU")
    STUDENT,

    @JsonProperty("YOU")
    YOUTH;
}
