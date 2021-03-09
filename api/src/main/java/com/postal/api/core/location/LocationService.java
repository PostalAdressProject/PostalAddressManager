package com.postal.api.core.location;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

public interface LocationService {

    @PostMapping(
            value    = "/location",
            consumes = "application/json")
    Location createLocation(@RequestBody Location body);

    /**
     * Sample usage:
     *
     * curl $HOST:$PORT/location?latitude=534.21&longitude=56.342312
     *
     * @param locationId
     * @return
     */
    @GetMapping(
        value    = "/location",
        produces = "application/json")
    Flux<Location> getLocations(@RequestHeader HttpHeaders headers, @RequestParam(value = "location Id", required = true) int locationId);

    @DeleteMapping(value = "/location/{locationId}")
    void deleteLocations(@RequestParam(value = "location Id", required = true)  int locationId);
}
