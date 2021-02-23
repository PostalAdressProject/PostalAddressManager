package com.postal.microservice.composite.services;

import com.postal.api.composite.address.AddressAggregate;
import com.postal.api.composite.address.AddressCompositeRESTfulService;
import com.postal.util.http.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

public class ApplicationServiceImpl  implements AddressCompositeRESTfulService {

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
}
