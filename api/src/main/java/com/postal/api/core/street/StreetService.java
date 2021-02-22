package com.postal.api.core.street;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
//import reactor.core.publisher.Flux;

public interface StreetService {

    Street createStreet(@RequestBody Street body);

    /**
     * Sample usage: curl $HOST:$PORT/review?productId=1
     *
     * @param streetName
     * @return
     */
    @GetMapping(
            value = "/address/street",
            produces = "application/json")
    Flux<Street> getStreets(@RequestHeader HttpHeaders headers, @RequestParam(value = "street_name", required = true) String streetName);

//    void deleteStreets(@RequestParam(value = "streetId", required = true)  int streetId);
}