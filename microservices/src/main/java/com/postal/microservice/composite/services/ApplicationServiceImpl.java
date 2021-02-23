package com.postal.microservice.composite.services;

import com.postal.api.composite.address.AddressAggregate;
import com.postal.api.composite.address.AddressCompositeRESTfulService;
import com.postal.api.core.address.Address;
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

import java.util.List;

import static java.util.logging.Level.FINE;

@RestController
public class ApplicationServiceImpl  implements AddressCompositeRESTfulService {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationServiceImpl.class);
    private final SecurityContext sc = new SecurityContextImpl();

    private final ServiceUtil serviceUtil;
    private  ApplicationIntegration integration;

    @Autowired
    public ApplicationServiceImpl(ServiceUtil serviceUtil, ApplicationIntegration integration) {
        this.serviceUtil = serviceUtil;
        this.integration = integration;
    }


    /**
     * Sample usage: curl $HOST:$PORT/product-composite/1
     *
     * @param requestHeaders
     * @param addressId
     * @param delay
     * @param faultPercent
     * @return the composite product info, if found, else null
     */
    @Override
    public Mono<AddressAggregate> getCompositeAddress(HttpHeaders requestHeaders, int addressId, int delay, int faultPercent) {
        LOG.info("Will get composite product info for product.id={}", addressId);

        HttpHeaders headers = getHeaders(requestHeaders, "X-group");

        return Mono.zip(
                values-> ccreateAddressAggregate((SecurityContext) values[0], (Address) values[1],  serviceUtil.getServiceAddress()),
                ReactiveSecurityContextHolder.getContext().defaultIfEmpty(sc),
                integration.getAddress(headers, addressId, delay, faultPercent)
                        .onErrorReturn(CallNotPermittedException.class, getAddressFallbackValue(addressId)))
                .doOnError(ex -> LOG.warn("getCompositeAddress failed: {}", ex.toString()))
                .log(null, FINE);
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

        if (addressId < 1) throw new InvalidInputException("Invalid productId: " + addressId);

        if (addressId == 13) {
            String errMsg = "Product Id: " + addressId + " not found in fallback cache!";
            LOG.warn(errMsg);
            throw new NotFoundException(errMsg);
        }

        return new Address(addressId, "Fallback product" + addressId, addressId, serviceUtil.getServiceAddress());
    }

    private AddressAggregate ccreateAddressAggregate(SecurityContext sc, Address address, String serviceAddress) {

//        logAuthorizationInfo(sc);
        int addressId = 2324;//address.getAddressId();

        return new AddressAggregate();
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
