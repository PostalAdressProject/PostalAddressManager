package com.postal.model.core.street;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
//import reactor.core.publisher.Flux;

public interface StreetService {

    @PostMapping(
            value    = "/street",
            consumes = "application/json")
    Street createStreet(@RequestBody Street body);

    /**
     * Sample usage: curl $HOST:$PORT/review?productId=1
     *
     * @param streetName
     * @return
     */
    @GetMapping(
            value = "/street",
            produces = "application/json")
    Flux<Street> getStreets(@RequestHeader HttpHeaders headers, @RequestParam(value = "street_name", required = true) String streetName);

    @DeleteMapping(value = "/street/{streetId}")
    void deleteStreets(@RequestParam(value = "streetId", required = true)  int streetId);
}