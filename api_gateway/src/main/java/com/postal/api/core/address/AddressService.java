package com.postal.api.core.address;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

public interface AddressService {

    Product createAddress(@RequestBody Address body);

    /**
     * Sample usage: curl $HOST:$PORT/address/1
     *
     * @param productId
     * @return the product, if found, else null
     */
    @GetMapping(
        value    = "/address/{addressId}",
        produces = "application/json")
    Mono<Product> getAddress(
         @RequestHeader HttpHeaders headers,
         @PathVariable int addressId,
         @RequestParam(value = "delay", required = false, defaultValue = "0") int delay,
         @RequestParam(value = "faultPercent", required = false, defaultValue = "0") int faultPercent
    );

//    void deleteProduct(@PathVariable int productId);
}