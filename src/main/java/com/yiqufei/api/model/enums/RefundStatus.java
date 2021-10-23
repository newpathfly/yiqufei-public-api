package com.yiqufei.api.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RefundStatus {

    @JsonProperty("T")
    UNREFUNDABLE,

    @JsonProperty("H")
    CONDITIONAL_REFUND,

    @JsonProperty("F")
    FREE_REFUND,

    @JsonProperty("E")
    BY_THE_RULES_OF_AIRLINES;
}
