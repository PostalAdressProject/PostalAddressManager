package com.postal.apil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.postal.apil.core.address.Address;
//import reactor.core.publisher.Mono;

@Api(description = "REST API for composite product information.")
@RestController
public class TestBoot {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "Greetings from Spring Boot!";
    }

    /**
     * Sample usage: curl $HOST:$PORT//address/test/search/bothell
     *
     * @param text
     * @return the composite product info, if found, else null
     */
    @ApiOperation(
            value = "${api.address.search.description}",
            notes = "${api.address.search.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request, invalid format of the request. See response message for more information."),
            @ApiResponse(code = 404, message = "Not found, the specified id does not exist."),
            @ApiResponse(code = 422, message = "Unprocessable entity, input parameters caused the processing to fail. See response message for more information.")
    })
    @GetMapping(
            value = "/address/test/search/{text}",
            produces = "application/json")
    @ResponseBody
    public String getAddress(@PathVariable String text) {
        Address a = Address.builder().recipient_name(text).build();
        return "I'm lost but I'm going to look for " + a.getRecipient_name();
//        return "I'm lost ";
    }

    @GetMapping(
            value = "/divide/test/{divisor}",
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
        return "Error there" + e.getMessage();
    }
}