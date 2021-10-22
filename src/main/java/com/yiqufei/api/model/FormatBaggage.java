package com.yiqufei.api.model;

import com.yiqufei.api.model.enums.PassengerType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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

    /**
     * Passenger type, 1=adult/1=child/2=infant
     * 
     * 1）For multi-passenger type request and verify, the price must be returned by
     * the type of all passengers; e.g. adult+child's request, must return both of
     * the allowance of baggage.
     */
    @NotNull
    PassengerType passengerType;

    /**
     * Baggage pieces allowance (unit in PC)
     * 
     * 1）do not allow in blank, if blank, will be filtering
     * 
     * 2）The enumeration values are as follows:
     * 
     * 0=No free checked baggage (at this time baggageWeight do not assign, even if
     * the assignment is meaningless);
     * 
     * -1=means ‘WEIGHT CONCEPT’instead of piece concept, the baggageWeight means
     * the total weight allowrence per passenger(at this time the baggageWeight must
     * be assigned, or will be blocked)
     * 
     * ＞0=means ‘PIECE CONCEPT’, the baggageWeight means weight of each piece of
     * baggage(at this time the baggageWeight could be blank, but not recommended)
     */
    @Min(-1)
    Integer baggagePiece;

    /**
     * BaggageWeight unit in KG, combined with baggagePiece
     */
    @PositiveOrZero
    Integer baggageWeight;

    /**
     * Chinese baggage allowance
     */
    String cnBaggage;

    /**
     * English baggage allowance
     */
    String enBaggage;
}
