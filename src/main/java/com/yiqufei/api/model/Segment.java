package com.yiqufei.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class Segment {

    /**
     * Airlines IATA code, must be the same with the flightNumber
     */
    @NotBlank
    @Size(min = 3, max = 3)
    String carrier;

    // @todo - continue
}
