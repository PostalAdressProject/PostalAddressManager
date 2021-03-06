package com.postal.api.core.address;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface AddressService {

    /**
     * Sample usage: curl $HOST:$PORT/address/format/usa
     *
     * @param country
     * @return the country format, if found, else null
     */
    @GetMapping(
        value    = "/address/format/{country}",
        produces = "application/json")
    Mono<Address> getAddressFormat(
         @RequestHeader HttpHeaders headers,
         @PathVariable String country,
         @RequestParam(value = "delay", required = false, defaultValue = "0") int delay,
         @RequestParam(value = "faultPercent", required = false, defaultValue = "0") int faultPercent
    );

    /**
     * Sample usage: curl $HOST:$PORT/address?street=123&city=xyz
     *
     * @param allParams
     * @return the address text, if found, else null
     */
    @ApiOperation(
            value = "${api.address.search.description}",
            notes = "${api.address.search.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request, invalid format of the request. See response message for more information."),
            @ApiResponse(code = 404, message = "Not found, the specified text does not exist."),
            @ApiResponse(code = 422, message = "Unprocessable entity, input parameters caused the processing to fail. See response message for more information.")
    })
    @GetMapping(
            value    = "/address2",
            produces = "application/json")
    List<Address> findAddress(
            @RequestHeader HttpHeaders headers,
            @RequestParam Map<String, String> allParams,
            @RequestParam(value = "delay", required = false, defaultValue = "0") int delay,
            @RequestParam(value = "faultPercent", required = false, defaultValue = "0") int faultPercent
    );

    /**
     * Sample usage: curl $HOST:$PORT/address/street/murphyapartments?country=us
     *
     * @param text
     * @return the address text, if found, else null
     */
    @ApiOperation(
            value = "${api.address.search.description}",
            notes = "${api.address.search.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request, invalid format of the request. See response message for more information."),
            @ApiResponse(code = 404, message = "Not found, the specified text does not exist."),
            @ApiResponse(code = 422, message = "Unprocessable entity, input parameters caused the processing to fail. See response message for more information.")
    })
    @GetMapping(
            value    = "/address/street/{text}",
            produces = "application/json")
    List<Address> findStreetAddress(
            @RequestHeader HttpHeaders headers,
            @PathVariable String text,
            @RequestParam(value = "country", required = false, defaultValue = "United States") String country,
            @RequestParam(value = "delay", required = false, defaultValue = "0") int delay,
            @RequestParam(value = "faultPercent", required = false, defaultValue = "0") int faultPercent
    );

}