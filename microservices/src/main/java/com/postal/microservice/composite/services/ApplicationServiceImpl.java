package com.postal.microservice.composite.services;

import com.postal.api.composite.address.AddressAggregate;
import com.postal.api.composite.address.AddressCompositeRESTfulService;
import com.postal.util.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ApplicationServiceImpl  implements AddressCompositeRESTfulService {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    private final SecurityContext nullSC = new SecurityContextImpl();

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
     * @param headers
     * @param productId
     * @param delay
     * @param faultPercent
     * @return the composite product info, if found, else null
     */
    @Override
    public Mono<AddressAggregate> getCompositeAddress(HttpHeaders headers, int productId, int delay, int faultPercent) {
        return null;
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
