package com.postal.model.composite;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@Api(description = "REST API for composite address information.")
public interface AddressCompositeRESTfulService {

    /**
     * Sample usage: curl $HOST:$PORT/address/6
     *
     * @param addressId
     * @return the composite address info mapped by the id, if found, else null
     */
    @ApiOperation(
            value = "${api.address.search.description}",
            notes = "${api.address.search.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request, invalid format of the request. See response message for more information."),
            @ApiResponse(code = 404, message = "Not found, the specified text does not exist."),
            @ApiResponse(code = 422, message = "Unprocessable entity, input parameters caused the processing to fail. See response message for more information.")
    })
    @GetMapping(
            value    = "/addresscomposite/{addressId}",
            produces = "application/json")
    Mono<AddressAggregate> getCompositeAddressById(
            @RequestHeader HttpHeaders headers,
            @PathVariable int addressId,
            @RequestParam(value = "delay", required = false, defaultValue = "0") int delay,
            @RequestParam(value = "faultPercent", required = false, defaultValue = "0") int faultPercent
    );


//    /**
//     * Sample usage: curl $HOST:$PORT/address/search/murphyapartments
//     *
//     * @param text
//     * @return the composite address info for the serach string, if found, else null
//     */
//    @ApiOperation(
//        value = "${api.address.search.description}",
//        notes = "${api.address.search.notes}")
//    @ApiResponses(value = {
//        @ApiResponse(code = 400, message = "Bad Request, invalid format of the request. See response message for more information."),
//        @ApiResponse(code = 404, message = "Not found, the specified text does not exist."),
//        @ApiResponse(code = 422, message = "Unprocessable entity, input parameters caused the processing to fail. See response message for more information.")
//    })
//    @GetMapping(
//        value    = "/address/search/{text}",
//        produces = "application/json")
//    Mono<AddressAggregate> searchCompositeAddress(
//        @RequestHeader HttpHeaders headers,
//        @PathVariable String text,
//        @RequestParam(value = "delay", required = false, defaultValue = "0") int delay,
//        @RequestParam(value = "faultPercent", required = false, defaultValue = "0") int faultPercent
//    );
//
//
//
//    /**
//     * Sample usage:
//     *
//     * curl -X POST $HOST:$PORT/address \
//     *   -H "Content-Type: application/json" --data \
//     *   '{"state":123,"city":"product 123","province":123pp}'
//     *
//     * @param body
//     */
//    @ApiOperation(
//        value = "${api.address.create.description}",
//        notes = "${api.address.create.notes}")
//    @ApiResponses(value = {
//        @ApiResponse(code = 400, message = "Bad Request, invalid format of the request. See response message for more information."),
//        @ApiResponse(code = 422, message = "Unprocessable entity, input parameters caused the processing to fail. See response message for more information.")
//    })
//    @PostMapping(
//        value    = "/address-records",
//        consumes = "application/json")
//    Mono<Void> createCompositeAddress(@RequestBody AddressAggregate body);
//
//    /**
//     * Sample usage:
//     *
//     * curl -X DELETE $HOST:$PORT/address/1
//     *
//     * @param AddressId
//     */
//    @ApiOperation(
//        value = "${api.address.delete.description}",
//        notes = "${api.address.delete.notes}")
//    @ApiResponses(value = {
//        @ApiResponse(code = 400, message = "Bad Request, invalid format of the request. See response message for more information."),
//        @ApiResponse(code = 422, message = "Unprocessable entity, input parameters caused the processing to fail. See response message for more information.")
//    })
//    @DeleteMapping(value = "/address-records/{AddressId}")
//    Mono<Void> deleteCompositeAddress(@PathVariable int AddressId);
}