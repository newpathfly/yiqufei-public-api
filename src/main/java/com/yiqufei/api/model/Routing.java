package com.yiqufei.api.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.List;

import com.yiqufei.api.model.enums.ApplyType;
import com.yiqufei.api.model.enums.Eligibility;
import com.yiqufei.api.model.enums.InvoiceType;
import com.yiqufei.api.model.enums.PriceType;
import com.yiqufei.api.model.enums.ProductType;
import com.yiqufei.api.model.enums.ReservationType;
import com.yiqufei.api.model.enums.TaxType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Routing {

    /**
     * Save necessary information, the pricing will be sent with request message to
     * the supplier; Up to 1000 characters.
     */
    @NotBlank
    String data;

    /**
     * [Public fares must be assigned] Public fare for Adult, excluding tax
     */
    @Positive
    BigDecimal publishPrice;

    /**
     * Single adult’s price, excluding tax
     */
    @NotNull
    @Positive
    BigDecimal adultPrice;

    /**
     * Adult taxes[Attention: taxes could not be 0, if saved as 0, pls contact us]
     */
    @NotNull
    @Positive
    BigDecimal adultTax;

    // @todo known field
    @Positive
    BigDecimal adultTotal;

    /**
     * Public fare for child, excluding tax
     */
    @Positive
    BigDecimal childPublishPrice;

    /**
     * Single child’s price, excluding tax
     */
    @NotNull
    @Positive
    BigDecimal childPrice;

    /**
     * Child’s tax
     * 
     * 1）request include child must be responsed
     * 
     * 2）could not be 0, if saved as 0, pls contact us
     */
    @NotNull
    @Positive
    BigDecimal childTax;

    // @todo known field
    @Positive
    BigDecimal childTotal;

    /**
     * Public fare for infant[ not available]
     */
    @PositiveOrZero
    BigDecimal infantPublishPrice;

    /**
     * Single infant’s price, excluding tax [not available]
     */
    @PositiveOrZero
    BigDecimal infantPrice;

    /**
     * Infant’s tax[not available]
     */
    @PositiveOrZero
    BigDecimal infantTax;

    // @todo known field
    @Positive
    BigDecimal infantTotal;

    /**
     * Adult’s tax type:
     * 
     * 0=excluding tax / 1=including taxes
     * 
     * [0 is normal, if shows 1 pleast notify us]
     */
    @NotNull
    TaxType adultTaxType;

    /**
     * Child’s tax type:
     * 
     * 0=excluding tax / 1=including taxes
     * 
     * [0 is normal, if shows 1 pleast notify us]
     */
    @NotNull
    TaxType childTaxType;

    /**
     * Price type: 0=normal price / 1=students price
     * 
     * [please use 0]
     */
    @NotNull
    PriceType priceType;

    /**
     * Apply type: 0=Pre-set price / 1=application price
     * 
     * [please use 0]
     */
    @NotNull
    ApplyType applyType;

    /**
     * [Public fare must be assigned] exchange rate
     */
    @Positive
    BigDecimal exchange;

    /**
     * Age restriction
     * 
     * 1）use “-”means to, e.g.*-12 means under 12 years old
     * 
     * 2）blank means no restriction
     */
    String adultAgeRestriction;

    /**
     * [Public fares must be assigned]
     * 
     * passenger eligibility, use type code:
     * 
     *  NOR： normal adult
     * 
     *  LAB： labor
     * 
     *  SEA：seaman
     * 
     *  SNR：senior
     * 
     *  STU：student
     * 
     *  YOU： youth
     */
    Eligibility eligibility;

    /**
     * Nationality, use coungry code
     * 
     * 1) blank means no restriction (usually it is blank)
     * 
     * 2) use “/”to devide
     * 
     * 3)choose one from nationality and forbidden nationality
     */
    String nationality;

    /**
     * Forbidden nationality, use country code
     * 
     * 1）blank means no restriction
     * 
     * 2）use “/” to devide
     * 
     * 3）choose one from nationality and forbidden nationality
     */
    String forbiddenNationality;

    /**
     * Plan category default 0
     */
    Integer planCategory;

    /**
     * Invoice type: T=initerary / F=invoice / E=e-invoice
     */
    @NotNull
    InvoiceType invoiceType;

    /**
     * Minimum stay[daily]
     * 
     * [no response, shows in 0, refer to flight change],
     */
    String minStay;

    /**
     * Maximum stay[daily]
     * 
     * [no response, shows in 0, refer to flight change]
     */
    String maxStay;

    /**
     * Minimum passenger
     * 
     * [no response, shows in 1]
     */
    Integer minPassengerCount;

    /**
     * Maximum passenger
     * 
     * [no response, shows in 9]
     */
    Integer maxPassengerCount;

    /**
     * Booking office number
     * 
     * [could be blank]
     */
    String bookingOfficeNo;

    /**
     * Ticketing office number
     * 
     * [could be blank]
     */
    String ticketingOfficeNo;

    /**
     * Valideating carrier
     * 
     * issuing airline
     * 
     * entire routes shows only one airline
     */
    @NotBlank
    String validatingCarrier;

    /**
     * [Public fares must be assigned] reservation type
     * 
     * use IATA code
     * 
     *  1E：TravelSky
     * 
     *  1A：Amadeus
     * 
     *  1B：Abacus
     * 
     *  1S：Sabre
     * 
     *  1P：WorldSpan
     * 
     *  1G：Galileo
     * 
     *  OT：from unknow GDS
     */
    ReservationType reservationType;

    /**
     * 1）PUB public fare: PUB
     * 
     * 2）PRV private fare: PRV
     */
    ProductType productType;

    /**
     * Fare basis devide in “;”each segment
     */
    String fareBasis;

    /**
     * From flight use the following Segment Element
     * 
     * Multi routes all output to this node
     */
    @NotEmpty
    @Valid
    @Singular
    List<Segment> fromSegments;

    /**
     * Segment Element return flight use the following Segment Element, one way
     * search is blank
     */
    @Valid
    @Singular
    List<Segment> retSegments;

    /**
     * Free baggage allowrance, exchange and return information use the following
     * Rule Element
     */
    @Valid
    Rule rule;

    // @todo known field
    String fromConnectionTime;

    // @todo known field
    String returnConnectionTime;
}
