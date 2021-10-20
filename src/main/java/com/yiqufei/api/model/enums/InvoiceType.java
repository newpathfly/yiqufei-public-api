package com.yiqufei.api.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InvoiceType {
    
    @JsonProperty("T")
    INITERARY,

    @JsonProperty("F")
    INVOICE,

    @JsonProperty("E")
    E_INVOICE
}
