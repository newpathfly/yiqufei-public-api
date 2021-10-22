package com.yiqufei.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.InputStream;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yiqufei.api.model.enums.ApplyType;
import com.yiqufei.api.model.enums.InvoiceType;
import com.yiqufei.api.model.enums.PriceType;
import com.yiqufei.api.model.enums.Status;
import com.yiqufei.api.model.enums.TaxType;
import com.yiqufei.api.model.enums.TripType;
import com.yiqufei.api.utils.ModelValidator;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;

class SearchTest {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final static ModelValidator BASIC_REQUEST_VALIDATOR = new ModelValidator();

    @Test
    @SneakyThrows
    void negativeTest_EmptySearchRequest() {

        Search.Request request = Search.Request.builder().build();

        assertThrows(IllegalArgumentException.class, () -> {
            BASIC_REQUEST_VALIDATOR.validate(request);
        });

        String json = OBJECT_MAPPER.writeValueAsString(request);

        assertNotNull(json);
    }

    @Test
    @SneakyThrows
    void positiveTest_SearchRequest() {

        Search.Request expectedRequest = buildSearchRequest();

        BASIC_REQUEST_VALIDATOR.validate(expectedRequest);

        String json = OBJECT_MAPPER.writeValueAsString(expectedRequest);

        Search.Request actualRequest = OBJECT_MAPPER.readValue(json, Search.Request.class);

        assertEquals(expectedRequest.cid, actualRequest.cid);
        assertEquals(expectedRequest.tripType, actualRequest.tripType);
        assertEquals(expectedRequest.fromCity, actualRequest.fromCity);
        assertEquals(expectedRequest.toCity, actualRequest.toCity);
        assertEquals(expectedRequest.adultNumber, actualRequest.adultNumber);
        assertEquals(expectedRequest.childNumber, actualRequest.childNumber);
        assertEquals(expectedRequest.fromDate, actualRequest.fromDate);
        assertEquals(expectedRequest.retDate, actualRequest.retDate);
    }

    @Test
    @SneakyThrows
    void positiveTest_SearchResponse() {

        Search.Response expectedResponse = buildSearchResponse();

        BASIC_REQUEST_VALIDATOR.validate(expectedResponse);

        String json = OBJECT_MAPPER.writeValueAsString(expectedResponse);

        Search.Response actualResponse = OBJECT_MAPPER.readValue(json, Search.Response.class);

        assertNotNull(actualResponse);
    }

    @Test
    @SneakyThrows
    void positiveTest_SearchResponse_SampleJson() {

        for (String samplePath : new String[] { //
                "/samples/SearchResponse_OW1.json", //
                "/samples/SearchResponse_OW2.json", //
                "/samples/SearchResponse_RT.json" //
        }) {
            Search.Response response = null;

            try (InputStream stream = getClass().getResourceAsStream(samplePath)) {
                response = OBJECT_MAPPER.readValue(stream, Search.Response.class);
            }

            BASIC_REQUEST_VALIDATOR.validate(response);
        }
    }

    private static Search.Request buildSearchRequest() {
        return Search.Request.builder() //
                .cid("???") //
                .tripType(TripType.OW) //
                .fromCity("PEK") //
                .toCity("YYZ") //
                .adultNumber(2) //
                .childNumber(2) //
                .fromDate("20220202") //
                .build();
    }

    private static Search.Response buildSearchResponse() {

        Routing routing = buildRouting();

        return Search.Response.builder() //
                .routing(routing) //
                .status(Status.SUCCESS) //
                .build();
    }

    private static Routing buildRouting() {

        Segment fromSegment = buildSegment();

        return Routing.builder() //
                .adultPrice(BigDecimal.valueOf(123.45)) //
                .adultTax(BigDecimal.valueOf(12.34)) //
                .adultTaxType(TaxType.INCLUDING_TAX) //
                .applyType(ApplyType.PRESET_PRICE) //
                .childPrice(BigDecimal.valueOf(123.45)) //
                .childTax(BigDecimal.valueOf(12.34)) //
                .childTaxType(TaxType.INCLUDING_TAX) //
                .data("???") //
                .invoiceType(InvoiceType.E_INVOICE) //
                .priceType(PriceType.NORMAL_PRICE) //
                .validatingCarrier("ABC") //
                .fromSegment(fromSegment) //
                .build();
    }

    private static Segment buildSegment() {

        return Segment.builder() //
                .arrAirport("YYZ") //
                .cabin("H") //
                .carrier("AC") //
                .codeShare(false) //
                .depAirport("PEK") //
                .depTime("202203100300") //
                .arrTime("202303100300") //
                .flightNumber("AC123") //
                .build();
    }
}
