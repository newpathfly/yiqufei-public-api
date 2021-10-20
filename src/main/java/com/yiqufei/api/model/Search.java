package com.yiqufei.api.model;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.yiqufei.api.model.enums.StatusType;
import com.yiqufei.api.model.enums.TripType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

public class Search {

    private Search() {
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        /**
         * Integration ID
         */
        @NotBlank
        String cid;

        /**
         * TripType,1:One Way;2: Return Way
         */
        @NotNull
        TripType tripType;

        /**
         * [Attention: request based on CITY not AIRPORT]
         */
        @NotBlank
        @Size(min = 3, max = 3)
        String fromCity;

        /**
         * [Attention: request based on CITY not AIRPORT]
         */
        @NotBlank
        @Size(min = 3, max = 3)
        String toCity;

        /**
         * Date, in YYYYMMDD format
         */
        @NotBlank
        @Size(min = 8, max = 8)
        String fromDate;

        /**
         * Date, in YYYYMMDD format
         */
        @Size(min = 8, max = 8)
        String retDate;

        /**
         * Adult Number, 1-9, Suppliers please follow the actual request passenger
         * numbers [Attention: request is with the passenger number]
         */
        @NotNull
        @Min(1)
        @Max(9)
        Integer adultNumber;

        /**
         * Child Number, 0-9(New suppliers are only open “adults” by default, not
         * multiple passenger types)
         */
        @NotNull
        @Min(0)
        @Max(9)
        Integer childNumber;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        /**
         * 0=success
         * 
         * 3=other failure reasons
         * 
         * 4=request parameter error
         * 
         * 5=program exception
         * 
         * [Return successful when request result with no actual routes on searching
         * date]
         * 
         */
        @NotNull
        StatusType status;

        /**
         * Prompt information
         */
        String msg;

        @NotNull
        @NotEmpty
        @Singular
        @Valid
        List<Routing> routings;
    }
}
