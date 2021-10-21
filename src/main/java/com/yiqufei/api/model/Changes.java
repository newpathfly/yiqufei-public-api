package com.yiqufei.api.model;

import com.yiqufei.api.model.enums.ChangesStatus;
import com.yiqufei.api.model.enums.ChangesType;

import jakarta.validation.constraints.NotNull;
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

public class Changes {

    /**
     * ChangesType
     * 
     * 0=ticket all unuse
     * 
     * 1=ticket partial used
     * 
     * [even the inbound way is used, in the return way of routes, this means the
     * outbound way of change information]
     * 
     * [one way=0, return way=0,1]
     */
    @NotNull
    ChangesType changesType;

    /**
     * ChangesStatus
     * 
     * T=unchangeable
     * 
     * H=conditional change
     * 
     * F=free change
     * 
     * E=by the rules of airlines[public fares only]
     */
    @NotNull
    ChangesStatus changeStatus;

    // @todo - continue
}
