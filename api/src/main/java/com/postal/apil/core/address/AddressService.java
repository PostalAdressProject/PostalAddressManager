package com.postal.apil.core.address;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

public interface AddressService {

    @PostMapping(
            value    = "/address/format",
            consumes = "application/json")
    Address createAddress(@RequestBody Address body);

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
     * Sample usage: curl $HOST:$PORT/address/6
     *
     * @param addressId
     * @return the address, if found, else null
     */
    @GetMapping(
            value    = "/address-1/{addressId}",
            produces = "application/json")
    Mono<Address> getAddress(
            @RequestHeader HttpHeaders headers,
            @PathVariable int addressId,
            @RequestParam(value = "delay", required = false, defaultValue = "0") int delay,
            @RequestParam(value = "faultPercent", required = false, defaultValue = "0") int faultPercent
    );

    /**
     * Sample usage: curl $HOST:$PORT/address/murphyapartments,washington
     *
     * @param text
     * @return the address text, if found, else null
     */
    @GetMapping(
            value    = "/address-1/{text}",
            produces = "application/json")
    Mono<Address> findAddress(
            @RequestHeader HttpHeaders headers,
            @PathVariable String text,
            @RequestParam(value = "delay", required = false, defaultValue = "0") int delay,
            @RequestParam(value = "faultPercent", required = false, defaultValue = "0") int faultPercent
    );

    @DeleteMapping(value = "/address/format/{addressId}")
    void deleteAddress(@PathVariable int addressId);
}