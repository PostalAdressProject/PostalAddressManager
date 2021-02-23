package com.postal.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//import reactor.core.publisher.Mono;

@Api(description = "REST API for composite product information.")
@RestController
public class TestBoot {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "Greetings from Spring Boot!";
    }

    /**
     * Sample usage: curl $HOST:$PORT//address/search/bothell
     *
     * @param text
     * @return the composite product info, if found, else null
     */
    @ApiOperation(
            value = "${api.product-composite.get-composite-product.description}",
            notes = "${api.product-composite.get-composite-product.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request, invalid format of the request. See response message for more information."),
            @ApiResponse(code = 404, message = "Not found, the specified id does not exist."),
            @ApiResponse(code = 422, message = "Unprocessable entity, input parameters caused the processing to fail. See response message for more information.")
    })
    @GetMapping(
            value = "/address/search/{text}",
            produces = "application/json")
    @ResponseBody
    public String getAddress(@PathVariable String text) {
//        Address a = Address.builder().recipient_name(text).build();
//        return "I'm lost but I'm going to look for " + a.getRecipient_name();
        return "I'm lost ";
    }

    @GetMapping(
            value = "/divide/{divisor}",
            produces = "application/json")
    @ResponseBody
    public int calculateDivision(@PathVariable int divisor) {
        try {
            return 1 / divisor;
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Damn. It's undefined.");
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String myCustomException(IllegalArgumentException e) {
        return "you lost dumbo." + e.getMessage();
    }
//    Mono<AddressAggregate> getCompositeProduct(
//            @RequestHeader HttpHeaders headers,
//            @PathVariable int productId,
//            @RequestParam(value = "delay", required = false, defaultValue = "0") int delay,
//            @RequestParam(value = "faultPercent", required = false, defaultValue = "0") int faultPercent
//    );

}