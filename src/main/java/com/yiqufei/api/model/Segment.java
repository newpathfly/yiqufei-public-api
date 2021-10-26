package com.yiqufei.api.model;

import com.yiqufei.api.model.enums.CabinGrade;

import jakarta.validation.constraints.NotBlank;
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
public class Segment {

    /**
     * Airlines IATA code, must be the same with the flightNumber
     */
    @NotBlank
    @Size(min = 2, max = 2)
    String carrier;

    /**
     * FlightNumber, e.g.: CA123
     * 
     * CZ6 flight number with extra zero, must be removed, e.g.CZ006, need to be
     * return as CZ6
     */
    @NotBlank
    @Size(min = 3)
    String flightNumber;

    /**
     * Departure airport’s IATA code
     */
    @NotBlank
    @Size(min = 3, max = 3)
    String depAirport;

    /**
     * Departure terminal, in abbreviations, e.g. T1
     */
    String depTerminal;

    /**
     * Departure date and time, format: YYYYMMDDHHMM,
     * 
     * e.g. 201203100300 means 3 o’clock on March 10th, 2012
     */
    @NotBlank
    @Size(min = 12, max = 12)
    String depTime;

    /**
     * Arrival airport’s IATA code
     */
    @NotBlank
    @Size(min = 3, max = 3)
    String arrAirport;

    /**
     * Arrival terminal, in abbreviations, e.g. T1
     */
    String arrTerminal;

    /**
     * Arrival date and time, format: YYYYMMDDHHMM,
     * 
     * e.g. 201203101305 means 13:05 on March 10th, 2012 [only support Beijing time]
     */
    @NotBlank
    @Size(min = 12, max = 12)
    String arrTime;

    /**
     * Stop cities’s IATA code
     */
    String stopCities;

    /**
     * Stop airports’IATA code
     */
    String stopAirports;

    /**
     * Code share (true for code share/ false for none code share)
     */
    @NotNull
    Boolean codeShare;

    /**
     * Operating carrier
     * 
     * If codeshare=true, the operatingCarrie must not be blank
     */
    @Size(min = 2, max = 2)
    String operatingCarrier;

    /**
     * OperatingFlightNo
     */
    @Size(min = 3)
    String operatingFlightNo;

    /**
     * Cabin
     */
    @NotBlank
    @Size(min = 1, max = 1)
    String cabin;

    /**
     * Cabin grade
     * 
     * F=first class;
     * 
     * C= commercial class;
     * 
     * S= super economy class;
     * 
     * Y=economy [Currently only supported to return Y]
     */
    CabinGrade cabinGrade;

    /**
     * AircraftCode’s IATA code，and filter the following aircraftCode information
     * 
     * BUS|ICE|LCH|LMO|MTL|RFS|TGV|THS|THT|TRN|TSL|
     */
    @Size(min = 3, max = 3)
    String aircraftCode;

    /**
     * Duration; unit: minutes, result of after the time difference conversion.
     * 
     * Subject to actual duration time
     */
    String duration;

    /**
     * seat count of the associated cabin
     */
    @PositiveOrZero
    Integer cabinCount;
}
