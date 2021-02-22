package com.postal.api.core.location;

import org.springframework.web.bind.annotation.RequestBody;
//import reactor.core.publisher.Flux;

public interface LocationService {

    Location createLocation(@RequestBody Location body);

    /**
     * Sample usage:
     *
     * curl $HOST:$PORT/location?latitude=534.21&longitude=56.342312
     *
     * @param productId
     * @return
     */
//    @GetMapping(
//        value    = "/location",
//        produces = "application/json")
//    Flux<Location> getLocations(@RequestHeader HttpHeaders headers, @RequestParam(value = "addressId", required = true) int addressId);

//    void deleteRecommendations(@RequestParam(value = "productId", required = true)  int productId);
}
