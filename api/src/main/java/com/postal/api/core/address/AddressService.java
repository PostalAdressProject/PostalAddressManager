package com.postal.api.core.address;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
//import reactor.core.publisher.Mono;

public interface AddressService {

    Address createAddress(@RequestBody Address body);

    /**
     * Sample usage: curl $HOST:$PORT/address/usa
     *
     * @param country
     * @return the country format, if found, else null
     */
    @GetMapping(
        value    = "/address/{country}",
        produces = "application/json")
    Mono<Address> getAddress(
         @RequestHeader HttpHeaders headers,
         @PathVariable int country,
         @RequestParam(value = "delay", required = false, defaultValue = "0") int delay,
         @RequestParam(value = "faultPercent", required = false, defaultValue = "0") int faultPercent
    );


//    void deleteProduct(@PathVariable int productId);
}