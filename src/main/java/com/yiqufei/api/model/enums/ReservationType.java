package com.yiqufei.api.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ReservationType {
    
    @JsonProperty("1E")
    TRAVELSKY,

    @JsonProperty("1A")
    AMADEUS,

    @JsonProperty("1B")
    ABACUS,

    @JsonProperty("1S")
    SABRE,

    @JsonProperty("1P")
    WORLDSPAN,

    @JsonProperty("1G")
    GALILEO,

    @JsonProperty("OT")
    UNKNOWN;
}
