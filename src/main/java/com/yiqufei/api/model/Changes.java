package com.yiqufei.api.model;

import java.math.BigDecimal;

import com.yiqufei.api.model.enums.ChangesStatus;
import com.yiqufei.api.model.enums.ChangesType;
import com.yiqufei.api.model.enums.PassengerType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
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
    ChangesStatus changesStatus;

    /**
     * ChangesFee
     * 
     * 1）when changesStatus=H, it must be assigned
     * 
     * 2）If changesStatus=T/F, this could not be assigned
     */
    @PositiveOrZero
    BigDecimal changesFee;

    /**
     * When refundStatus=H, it must be assigned
     */
    @Size(min = 3, max = 3)
    String currency;

    /**
     * PassengerType, 0=adult/1=child/2=infant
     * 
     * 1）For multi-passenger type request and verify, must be returned by the type
     * of all passengers; e.g. adult+child's request, must return both of the
     * allowance of exchange/refund.
     */
    @NotNull
    PassengerType passengerType;

    /**
     * Wether or not allow Noshow to revert
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
    ChangesStatus revNoshow;

    /**
     * When apply for change, how long before departure will be seen as NoShow?
     * Unit: hours
     * 
     * If could not confirm for it, pls assigned as 0.
     */
    @NotNull
    @PositiveOrZero
    Integer revNoShowCondition;

    /**
     * Noshow change fee
     * 
     * 1)When revNoshow=H, it must be assigned
     * 
     * 2) Shows for users Noshow change
     * 
     * fee=changesFee+ revNoshowFee
     */
    @PositiveOrZero
    BigDecimal revNoshowFee;

    /**
     * Chinese revert remark
     */
    String cnRevRemark;

    /**
     * English revert remark
     */
    String enRevRemark;
}
