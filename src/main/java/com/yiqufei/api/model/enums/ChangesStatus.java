package com.yiqufei.api.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ChangesStatus {
    
    @JsonProperty("T")
    UNCHANGEABLE,

    @JsonProperty("H")
    CONDITIONAL_CHANGE,

    @JsonProperty("F")
    FREE_CHANGE,

    @JsonProperty("E")
    BY_THE_RULES_OF_AIRLINES;
}
