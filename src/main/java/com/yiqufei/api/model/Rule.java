package com.yiqufei.api.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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
public class Rule {

    /**
     * FormatBaggage Element
     * 
     * format of baggage information list, use the following Format Baggage Element
     * 
     * 1）when verify, baggage and format baggage needs to return in the same time,
     * could not miss any of it
     */
    @NotEmpty
    @Valid
    @Singular("formatBaggageInfo")
    List<FormatBaggage> formatBaggageInfoList;

    /**
     * Refund rules information list, use the following Refund Element
     * 
     * 1）the format of OW/RT are different
     * 
     * 2）need to be return by passenger type
     */
    @NotEmpty
    @Valid
    @Singular("refundInfo")
    List<Refund> refundInfoList;

    /**
     * Change rules Changes Element
     */
    @NotEmpty
    @Valid
    @Singular("changesInfo")
    List<Changes> changesInfoList;

    /**
     * Note information
     */
    String note;

    /**
     * Public fare related parameters, geographic interval see the freight cluster
     * code
     */
    String tariffNo;

    /**
     * Public fare related parameters
     */
    String ruleNo;

    /**
     * internal use
     */
    Integer fareRuleMatchMode;

    /**
     * internal use
     */
    Boolean isUseCtripRule;
}
