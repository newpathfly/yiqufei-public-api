package com.yiqufei.api.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ProductType {

    @JsonProperty("PUB")
    PUBLIC_FARE,

    @JsonProperty("PRV")
    PRIVATE_FARE
}
