package com.postal.microservices.services;

import com.postal.addressdao.accessor.AddressAccessor;
import com.postal.api.composite.AddressAggregate;
import com.postal.api.composite.AddressCompositeRESTfulService;
import com.postal.api.core.address.Address;
import com.postal.api.core.address.AddressService;
import com.postal.addressdao.model.enums.Field;
import com.postal.util.http.ServiceUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApplicationServiceImpl implements AddressCompositeRESTfulService, AddressService {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationServiceImpl.class);
    private final SecurityContext sc = new SecurityContextImpl();

    private final ServiceUtil serviceUtil;
    private final ApplicationIntegration integration;

    private final String addressServiceUrl= "http://localhost:8080/address/";

    @Autowired
   AddressAccessor addressAccessor;

    @Autowired
    public ApplicationServiceImpl(ServiceUtil serviceUtil, ApplicationIntegration integration) {
        this.serviceUtil = serviceUtil;
        this.integration = integration;
    }


    /**
     * Sample usage: curl $HOST:$PORT/address/1
     *
     * @param requestHeaders
     * @param addressId
     * @param delay
     * @param faultPercent
     * @return the composite product info, if found, else null
     */
    @Override
    public Mono<AddressAggregate> getCompositeAddressById(HttpHeaders requestHeaders, int addressId, int delay, int faultPercent) {
        LOG.info("Will get composite-address info for address.id={}", addressId);

        HttpHeaders headers = getHeaders(requestHeaders, "X-group");
        return null;
//        return Mono.zip(
//                values -> createAddressAggregate((SecurityContext) values[0], (Address) values[1]),
//                ReactiveSecurityContextHolder.getContext().defaultIfEmpty(sc),
//                integration.getAddress(headers, addressId, delay, faultPercent)
//                        .onErrorReturn(CallNotPermittedException.class, getAddressFallbackValue(addressId)))
//                .doOnError(ex -> LOG.warn("getCompositeAddress failed: {}", ex.toString()))
//                .log(null, FINE);
    }


    private HttpHeaders getHeaders(HttpHeaders requesthHeaders, String... headers) {
        LOG.trace("Will look for {} headers: {}", headers.length, headers);
        HttpHeaders h = new HttpHeaders();
        for (String header : headers) {
            List<String> value = requesthHeaders.get(header);
            if (value != null) {
                h.addAll(header, value);
            }
        }
        LOG.trace("Will transfer {}, headers: {}", h.size(), h);
        return h;
    }

    @Override
    public Mono<Address> getAddressFormat(HttpHeaders headers, String country, int delay, int faultPercent) {
        return null;
    }

    /**
     * Sample usage: curl $HOST:$PORT/address/usa
     *
     * @param headers
     * @param text
     * @param country
     * @param delay
     * @param faultPercent
     * @return the country format, if found, else null
     */
    @Retry(name = "address")
    @CircuitBreaker(name = "address")
    @Override
    public List<Address> findStreetAddress(HttpHeaders headers, String text, String country, int delay, int faultPercent) {
        try {
            final Map<Field, String> fieldStringMap = new HashMap<>();
            fieldStringMap.put(Field.STREET, text);
            final List<com.postal.addressdao.model.models.Address> addressList = addressAccessor
                    .findAddressByCountry(country, fieldStringMap);
            List<Address> addressArrayList = new ArrayList<>();
            for (com.postal.addressdao.model.models.Address object: addressList) {
                addressArrayList.add(Address.builder()
                        .suite_apartment_number(object.getNumber())
                        .city_town_locality(object.getDistrict())
                        .build());
            }
            return addressArrayList;
        } catch (HttpClientErrorException ex) {
            LOG.warn("Got a unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
            LOG.warn("Error body: {}", ex.getResponseBodyAsString());
            throw ex;
        } catch (Exception ex) {
            LOG.warn("Got a unexpected HTTP error: {}, will rethrow it", ex.toString());
            return null;
        }
    }

    /**
     * Sample usage: curl $HOST:$PORT/address/usa
     *
     * @param headers
     * @param allParams
     * @param delay
     * @param faultPercent
     * @return the country format, if found, else null
     */
    @Retry(name = "address")
    @CircuitBreaker(name = "address")
    @Override
    public List<Address> findAddress(HttpHeaders headers, Map<String, String> allParams, int delay, int faultPercent) {

        try {
            String country = "United States of America";
            final Map<Field, String> fieldStringMap = new HashMap<>();
            // using for-each loop for iteration over Map.entrySet()
            //                fieldStringMap.put(Field.STREET, text);
            for (Map.Entry<String,String> entry : allParams.entrySet()) {
//                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                if(entry.getKey().equalsIgnoreCase("number"))
                    fieldStringMap.put(Field.NUMBER, entry.getValue());
                if(entry.getKey().equalsIgnoreCase("street"))
                    fieldStringMap.put(Field.STREET, entry.getValue());
                if(entry.getKey().equalsIgnoreCase("city"))
                    fieldStringMap.put(Field.CITY, entry.getValue());
                if(entry.getKey().equalsIgnoreCase("country"))
                    country=entry.getValue();
            }
            final List<com.postal.addressdao.model.models.Address> addressList = addressAccessor
                    .findAddressByCountry(country, fieldStringMap);
            List<Address> addressArrayList = new ArrayList<>();
            for (com.postal.addressdao.model.models.Address object: addressList) {
                addressArrayList.add(Address.builder()
                        .suite_apartment_number(object.getNumber())
                        .city_town_locality(object.getDistrict())
                        .build());
            }
            return addressArrayList;
        } catch (HttpClientErrorException ex) {
            LOG.warn("Got a unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
            LOG.warn("Error body: {}", ex.getResponseBodyAsString());
            throw ex;
        } catch (Exception ex) {
            LOG.warn("Got a unexpected HTTP error: {}, will rethrow it", ex.toString());
            return null;
        }

    }

}
