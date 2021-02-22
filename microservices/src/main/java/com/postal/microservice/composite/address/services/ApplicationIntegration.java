package com.postal.microservice.composite.address.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postal.api.core.address.Address;
import com.postal.api.core.address.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

//import com.postal.api.core.location.Location;
//import com.postal.api.core.location.LocationService;
//import com.postal.api.core.street.Street;
//import com.postal.api.core.street.StreetService;

@SpringBootApplication(scanBasePackages = "com.postal.api")

public class ApplicationIntegration implements AddressService{

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationIntegration.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private final String addressServiceUrl;
//    private final String locationServiceUrl;
//    private final String streetServiceUrl;

    @Autowired
    public ApplicationIntegration(
            RestTemplate restTemplate,
            ObjectMapper mapper,

            @Value("${app.address-service.host}") String addressServiceHost,
            @Value("${app.address-service.port}") int addressServicePort

//            @Value("${app.recommendation-service.host}") String locationServiceHost,
//            @Value("${app.recommendation-service.port}") int locationServicePort,
//
//            @Value("${app.review-service.host}") String streetServiceHost,
//            @Value("${app.review-service.port}") int streetServicePort
    ) {

        this.restTemplate = restTemplate;
        this.mapper = mapper;

        addressServiceUrl = "http://" + addressServiceHost + ":" + addressServicePort + "/product/";
//        locationServiceUrl = "http://" + locationServiceHost + ":" + locationServicePort + "/recommendation?productId=";
//        streetServiceUrl = "http://" + streetServiceHost + ":" + streetServicePort + "/review?productId=";
    }

    public Address findaddress(int addressId) {

        try {
            String url = addressServiceUrl + addressId;
            LOG.debug("Will call getProduct API on URL: {}", url);

            Address address = restTemplate.getForObject(url, Address.class);
//            LOG.debug("Found a product with id: {}", address.getAddressId()); //lombal not returning getaddressId as getter
            LOG.debug("Found a product with id: {}",address.toString());
            return address;

        } catch (HttpClientErrorException ex) {

            LOG.warn("Got a unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
            LOG.warn("Error body: {}", ex.getResponseBodyAsString());
            throw ex;

        }
    }

//    private String getErrorMessage(HttpClientErrorException ex) {
//        try {
//            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
//        } catch (IOException ioex) {
//            return ex.getMessage();
//        }
//    }

    @Override
    public Address createAddress(Address body) {
        return null;
    }

    /**
     * Sample usage: curl $HOST:$PORT/address/usa
     *
     * @param headers
     * @param country
     * @param delay
     * @param faultPercent
     * @return the country format, if found, else null
     */
    @Override
    public Mono<Address> getAddress(HttpHeaders headers, int country, int delay, int faultPercent) {
        return null;
    }

}
