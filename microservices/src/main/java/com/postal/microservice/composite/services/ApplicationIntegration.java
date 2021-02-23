package com.postal.microservice.composite.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postal.api.core.address.Address;
import com.postal.api.core.address.AddressService;
import com.postal.api.core.location.Location;
import com.postal.api.core.location.LocationService;
import com.postal.api.core.street.Street;
import com.postal.api.core.street.StreetService;
import com.postal.util.exceptions.InvalidInputException;
import com.postal.util.exceptions.NotFoundException;
import com.postal.util.http.HttpErrorInfo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.Output;
//import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;

import static java.util.logging.Level.FINE;

//@EnableBinding(ApplicationIntegration.MessageSources.class)
@Component
public class ApplicationIntegration implements AddressService , StreetService , LocationService {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationIntegration.class);


    private final ObjectMapper mapper;
    private final WebClient.Builder webClientBuilder;

    private WebClient webClient;

    private final MessageSources messageSources;

    private final int serviceTimeoutSec;
//
//    public interface MessageSources {
//
//        String OUTPUT_PRODUCTS = "output-products";
//
//        @Output(OUTPUT_PRODUCTS)
//        MessageChannel outputProducts();
//    }


    public interface MessageSources {

        String OUTPUT_PRODUCTS = "output-products";
    }

//    private final RestTemplate restTemplate;
//    private final ObjectMapper mapper;

    private final String addressServiceUrl;
//    private final String locationServiceUrl;
//    private final String streetServiceUrl;

    @Autowired
    public ApplicationIntegration(WebClient.Builder webClientBuilder, ObjectMapper mapper, MessageSources messageSources, @Value("${app.product-service.timeoutSec}")  int serviceTimeoutSec) {

//            @Value("${app.address-service.host}") String addressServiceHost,
//            @Value("${app.address-service.port}") int addressServicePort

//            @Value("${app.recommendation-service.host}") String locationServiceHost,
//            @Value("${app.recommendation-service.port}") int locationServicePort,
//
//            @Value("${app.review-service.host}") String streetServiceHost,
//            @Value("${app.review-service.port}") int streetServicePort

        this.webClientBuilder = webClientBuilder;
        this.mapper = mapper;
        this.messageSources = messageSources;
        this.serviceTimeoutSec = serviceTimeoutSec;

        String addressServiceHost;
        addressServiceUrl = "http://localhost:8080/address/";
//        addressServiceUrl = "http://" + addressServiceHost + ":" + addressServicePort + "/address/";
//        locationServiceUrl = "http://" + locationServiceHost + ":" + locationServicePort + "/recommendation?productId=";
//        streetServiceUrl = "http://" + streetServiceHost + ":" + streetServicePort + "/review?productId=";
    }

    private WebClient getWebClient() {
        if (webClient == null) {
            webClient = webClientBuilder.build();
        }
        return webClient;
    }

    private Throwable handleException(Throwable ex) {

        if (!(ex instanceof WebClientResponseException)) {
            LOG.warn("Got a unexpected error: {}, will rethrow it", ex.toString());
            return ex;
        }

        WebClientResponseException wcre = (WebClientResponseException)ex;

        switch (wcre.getStatusCode()) {

            case NOT_FOUND:
                return new NotFoundException(getErrorMessage(wcre));

            case UNPROCESSABLE_ENTITY :
                return new InvalidInputException(getErrorMessage(wcre));

            default:
                LOG.warn("Got a unexpected HTTP error: {}, will rethrow it", wcre.getStatusCode());
                LOG.warn("Error body: {}", wcre.getResponseBodyAsString());
                return ex;
        }
    }

    private String getErrorMessage(WebClientResponseException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        } catch (IOException ioex) {
            return ex.getMessage();
        }
    }

//    public Address findaddress(int addressId) {
//
//        try {
//            String url = addressServiceUrl + addressId;
//            LOG.debug("Will call getProduct API on URL: {}", url);
//
//            Address address = restTemplate.getForObject(url, Address.class);
////            LOG.debug("Found a product with id: {}", address.getAddressId()); //lombal not returning getaddressId as getter
//            LOG.debug("Found a product with id: {}",address.toString());
//            return address;
//
//        } catch (HttpClientErrorException ex) {
//
//            LOG.warn("Got a unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
//            LOG.warn("Error body: {}", ex.getResponseBodyAsString());
//            throw ex;
//
//        }
//    }



    @Override
    public Address createAddress(Address body){
//        messageSources.outputProducts().send(MessageBuilder.withPayload(new Event(CREATE, body.getProductId(), body)).build());
        return body;
    }



    /**
     * Sample usage: curl $HOST:$PORT/address/usa
     *
     * @param headers
     * @param addressId
     * @param delay
     * @param faultPercent
     * @return the country format, if found, else null
     */
    @Retry(name = "address")
    @CircuitBreaker(name = "address")
    @Override
    public Mono<Address> getAddress(HttpHeaders headers, int addressId, int delay, int faultPercent) {

        URI url = UriComponentsBuilder.fromUriString(addressServiceUrl + "/address/{addressId}?delay={delay}&faultPercent={faultPercent}").build(addressId, delay, faultPercent);
        LOG.debug("Will call the API on URL: {}", url);

        return getWebClient().get().uri(url)
                .headers(h -> h.addAll(headers))
                .retrieve().bodyToMono(Address.class).log(null, FINE)
                .onErrorMap(WebClientResponseException.class, ex -> handleException(ex));
//                .timeout(Duration.ofSeconds(productServiceTimeoutSec));
    }

    @Override
    public Location createLocation(Location body) {
        return null;
    }

    @Override
    public Street createStreet(Street body) {
        return null;
    }

    @Override
    public Flux<Street> getStreets(HttpHeaders headers, String streetName) {
        return null;
    }
}