package com.yiqufei.api.model;

import com.yiqufei.api.model.enums.RefundType;

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
public class Refund {

    /**
     * RefundTpye
     * 
     * 0=ticket all unuse
     *
     * 1=ticket partial used
     * 
     * [even the inbound way is used, in the return way of routes, this means the
     * outbound way of refund information]
     * 
     * [one way=0, return way=0,1]
     */
    @NotNull
    RefundType refundType;

    // @todo - continue
}
