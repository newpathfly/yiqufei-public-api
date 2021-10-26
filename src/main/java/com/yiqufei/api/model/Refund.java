package com.yiqufei.api.model;

import java.math.BigDecimal;

import com.yiqufei.api.model.enums.PassengerType;
import com.yiqufei.api.model.enums.RefundStatus;
import com.yiqufei.api.model.enums.RefundType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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

    /**
     * RefundStatus
     * 
     * T=unrefundable
     * 
     * H=conditional refund
     * 
     * F=free refund
     * 
     * E=by the rules of airlines[public fares only]
     */
    @NotNull
    RefundStatus refundStatus;

    /**
     * RefundFee
     * 
     * 1）when refundStatus=H, it must be assigned
     * 
     * 2）when refundStatus=T/F, it does not request to be asigned
     */
    @PositiveOrZero
    BigDecimal refundFee;

    /**
     * when refundStatus=H, it must be assigned
     */
    @Size(min = 3, max = 3)
    String currency;

    /**
     * PassengerType, 0=adult/1=child/2=infant
     */
    @NotNull
    PassengerType passengerType;

    /**
     * 1） For multi-passenger type request and verify, must be returned by the type
     * of all passengers; e.g. adult+child's request, must return both of the
     * allowance of exchange/refund.
     * 
     * 2）T=unrefundable; H= conditional refund; F=free refund; E=by the rules of
     * airlines[public fares only]
     */
    @NotNull
    RefundStatus refNoshow;

    /**
     * When apply for refund, how long before departure will be seen as NoShow?
     * Unit: hours
     * 
     * 1）If could not confirm for it, pls assigned as 0.
     */
    @NotNull
    @PositiveOrZero
    Integer refNoShowCondition;

    /**
     * NoShow refund fee
     * 
     * 1）if IsRefNoshow =H，
     * 
     * 2）Show for user Noshow= refundFee+ refNoshowFee。
     */
    @PositiveOrZero
    BigDecimal refNoshowFee;

    /**
     * Chinese refund remark
     */
    String cnRefRemark;

    /**
     * English refund remark
     */
    String enRefRemark;
}
