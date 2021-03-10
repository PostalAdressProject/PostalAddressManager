package com.postal.formatdao.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class ApplicationConfig {

    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017/");
    }

    @Bean(name = "formatMongoTemplate")
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "PostalFormat");
    }

    @Bean(name = "formatgetObjectMapper")
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
