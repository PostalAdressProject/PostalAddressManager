package com.postal.microservices.services;

import com.postal.addressdao.accessor.AddressAccessor;
import com.postal.addressdao.exception.AddressDataAccessException;
import com.postal.apil.composite.AddressAggregate;
import com.postal.apil.composite.AddressCompositeRESTfulService;
import com.postal.apil.core.address.Address;
import com.postal.apil.enums.Field;
import com.postal.util.exceptions.InvalidInputException;
import com.postal.util.exceptions.NotFoundException;
import com.postal.util.http.ServiceUtil;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.util.logging.Level.FINE;

@RestController
public class ApplicationServiceImpl implements AddressCompositeRESTfulService {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationServiceImpl.class);
    private final SecurityContext sc = new SecurityContextImpl();

    private final ServiceUtil serviceUtil;
    private final ApplicationIntegration integration;

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

        return Mono.zip(
                values -> createAddressAggregate((SecurityContext) values[0], (Address) values[1]),
                ReactiveSecurityContextHolder.getContext().defaultIfEmpty(sc),
                integration.getAddress(headers, addressId, delay, faultPercent)
                        .onErrorReturn(CallNotPermittedException.class, getAddressFallbackValue(addressId)))
                .doOnError(ex -> LOG.warn("getCompositeAddress failed: {}", ex.toString()))
                .log(null, FINE);
    }

    /**
     * Sample usage: curl $HOST:$PORT/address/search/Murphyapartments
     *
     * @param requestHeaders
     * @param addresstext
     * @param delay
     * @param faultPercent
     * @return the composite info, if found, else null
     */
    @Override
    public Mono<AddressAggregate> searchCompositeAddress(HttpHeaders requestHeaders, String addresstext, int delay, int faultPercent) {
        LOG.info("Will get composite address info for address with text ={}", addresstext);
        HttpHeaders headers = getHeaders(requestHeaders, "X-group");
        if (addresstext==null) throw new InvalidInputException("Invalid addresstext: ");
        if (delay > 0) simulateDelay(delay);
        if (faultPercent > 0) throwErrorIfBadLuck(faultPercent);
        AddressAccessor addressAccessor = new AddressAccessor();
        final Map<Field, String> fieldStringMap = new HashMap<>();
		fieldStringMap.put(Field.STREET, "3a Street");
        final List<com.postal.apil.models.Address> addressList;
        try {
            addressList = addressAccessor
                .findAddressByCountry("United Arab Emirates", fieldStringMap);
            //		System.out.println(addressList.get(0).getRegion());
            System.out.println(addressList.get(0).toString());
            return (Mono<AddressAggregate>) addressList;
        } catch (AddressDataAccessException e) {
            e.printStackTrace();
        }
        return null;
//        return Mono.zip(
//                values -> createAddressAggregate((SecurityContext) values[0], (Address) values[1]),
//                ReactiveSecurityContextHolder.getContext().defaultIfEmpty(sc),
//                integration.findAddress(headers, addresstext, delay, faultPercent))
//                .doOnError(ex -> LOG.warn("getCompositeAddress failed: {}", ex.toString()))
//                .log(null, FINE);
    }

    @Override
    public Mono<Void> createCompositeAddress(AddressAggregate body) {
        try {
            Address addobj = new Address(body.getAddressId(), body.getState(), body.getPostal_code(), body.getNeighborhood());
            integration.createAddress(addobj);

            // TBD make entry on l and s

        } catch (RuntimeException re) {
            LOG.warn("createCompositeProduct failed: {}", re.toString());
            throw re;
        }

        return null;
    }

    @Override
    public Mono<Void> deleteCompositeAddress(int AddressId) {
        LOG.debug("deleteCompositeProduct: Deletes a product aggregate for Id: {}", AddressId);
        // TBD : find the Lid and sid from aid
        integration.deleteAddress(AddressId);
        integration.deleteLocations(AddressId);
        integration.deleteStreets(AddressId);
        return null;
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

    /**
     * Note that this method is called by Mono.onErrorReturn() in getCompositeProduct().
     * Mono.onErrorReturn() will call this method once per execution to prepare a static response if the execution fails.
     * Do not execute any lengthy or CPU intensive operation in this method.
     *
     * @param addressId
     * @return
     */
    private Address getAddressFallbackValue(int addressId) {

        if (addressId < 1) throw new InvalidInputException("Invalid Id: " + addressId);

        if (addressId == 13) {
            String errMsg = " Id: " + addressId + " not found in fallback cache!";
            LOG.warn(errMsg);
            throw new NotFoundException(errMsg);
        }

        return new Address(addressId, "Fallback product" + addressId, addressId, serviceUtil.getServiceAddress());
    }

    private AddressAggregate createAddressAggregate(SecurityContext sc, Address address) {
//        logAuthorizationInfo(sc);
        String country = "uk";
//        return new AddressAggregate(); // trmp
//        return new AddressAggregate(country);
        return null;
    }


    private void simulateDelay(int delay) {
        LOG.debug("Sleeping for {} seconds...", delay);
        try {
            Thread.sleep(delay * 1000);
        } catch (InterruptedException e) {
        }
        LOG.debug("Moving on...");
    }

    private void throwErrorIfBadLuck(int faultPercent) {
        int randomThreshold = getRandomNumber(1, 100);
        if (faultPercent < randomThreshold) {
            LOG.debug("We got lucky, no error occurred, {} < {}", faultPercent, randomThreshold);
        } else {
            LOG.warn("Bad luck, an error occurred, {} >= {}", faultPercent, randomThreshold);
            throw new RuntimeException("Something went wrong...");
        }
    }

    private final Random randomNumberGenerator = new Random();

    private int getRandomNumber(int min, int max) {

        if (max < min) {
            throw new RuntimeException("Max must be greater than min");
        }

        return randomNumberGenerator.nextInt((max - min) + 1) + min;
    }
//    private void logAuthorizationInfo(Jwt jwt) {
//        if (jwt == null) {
//            LOG.warn("No JWT supplied, running tests are we?");
//        } else {
//            if (LOG.isDebugEnabled()) {
//                URL issuer = jwt.getIssuer();
//                List<String> audience = jwt.getAudience();
//                Object subject = jwt.getClaims().get("sub");
//                Object scopes = jwt.getClaims().get("scope");
//                Object expires = jwt.getClaims().get("exp");
//
//                LOG.debug("Authorization info: Subject: {}, scopes: {}, expires {}: issuer: {}, audience: {}", subject, scopes, expires, issuer, audience);
//            }
//        }
//    }
}
