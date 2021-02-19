package com.postal.api.core.street;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

public interface SteetService {

    Review createStreet(@RequestBody Review body);

    /**
     * Sample usage: curl $HOST:$PORT/review?productId=1
     *
     * @param streetId
     * @return
     */
    @GetMapping(
            value = "/street",
            produces = "application/json")
    Flux<Review> getStreets(@RequestHeader HttpHeaders headers, @RequestParam(value = "street_name", required = true) int streetId);

//    void deleteStreets(@RequestParam(value = "streetId", required = true)  int streetId);
}