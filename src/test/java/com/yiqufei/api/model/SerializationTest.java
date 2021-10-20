package com.yiqufei.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yiqufei.api.model.enums.ApplyType;
import com.yiqufei.api.model.enums.InvoiceType;
import com.yiqufei.api.model.enums.PriceType;
import com.yiqufei.api.model.enums.StatusType;
import com.yiqufei.api.model.enums.TaxType;
import com.yiqufei.api.model.enums.TripType;
import com.yiqufei.api.utils.ModelValidator;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;

class SerializationTest {

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

    private static Search.Request buildSearchRequest() {
        return Search.Request.builder() //
                .cid("wtf") //
                .tripType(TripType.RT) //
                .fromCity("PEK") //
                .toCity("YYZ") //
                .adultNumber(2) //
                .childNumber(2) //
                .fromDate("20220202") //
                .retDate("20220228") //
                .build();
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

    private static Search.Response buildSearchResponse() {

        Routing routing = buildRouting();

        return Search.Response.builder() //
                .routing(routing) //
                .status(StatusType.SUCCESS) //
                .build();
    }

    private static Routing buildRouting() {
        return Routing.builder() //
                .applyType(ApplyType.PRESET_PRICE) //
                .adultPrice(BigDecimal.valueOf(123.45)) //
                .adultTax(BigDecimal.valueOf(12.34)) //
                .adultTaxType(TaxType.INCLUDING_TAX) //
                .childPrice(BigDecimal.valueOf(123.45)) //
                .childTax(BigDecimal.valueOf(12.34)) //
                .childTaxType(TaxType.INCLUDING_TAX) //
                .data("wtf") //
                .invoiceType(InvoiceType.E_INVOICE) //
                .priceType(PriceType.NORMAL_PRICE) //
                .build();
    }
}
