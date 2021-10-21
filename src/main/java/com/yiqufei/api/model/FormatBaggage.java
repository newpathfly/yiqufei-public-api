package com.yiqufei.api.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormatBaggage {

    /**
     * Segments number, start from 1
     * 
     * 1）attention: should be assign by segment, not assign by inbound/outbound way
     */
    @NotNull
    @Positive
    Integer segmentNo;

    // @todo - continue
}
