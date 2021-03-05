package com.postal.microservices.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

//@EnableBinding(ApplicationIntegration.MessageSources.class)
@Component
public class ApplicationIntegration  {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationIntegration.class);
    private final ObjectMapper mapper;
    private final WebClient.Builder webClientBuilder;
    private WebClient webClient;
    private final int serviceTimeoutSec;
    //        private final RestTemplate restTemplate;

    @Autowired
    public ApplicationIntegration(WebClient.Builder webClientBuilder, ObjectMapper mapper, @Value("${app.address-service.timeoutSec}") int serviceTimeoutSec) {

        this.webClientBuilder = webClientBuilder;
        this.mapper = mapper;
        this.serviceTimeoutSec = serviceTimeoutSec;
    }


}
