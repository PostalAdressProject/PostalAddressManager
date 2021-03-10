package com.postal.formatdao.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.postal.formatdao.repository.FormatRepository;
import com.postal.model.models.PostalFormat;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Component
public class FormatRepositoryImpl implements FormatRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ObjectMapper getObjectMapper;


    private static final String REGEX = "^{value}";
    private static final String REPLACE = "{value}";

    public PostalFormat getFormat(final String countryName) throws IOException {
        /*
        PostalFormat postalFormat = FormatCodeUtils.getFormatByCountryName(countryName);
        if (postalFormat == null) {
            throw new IllegalArgumentException("Country Name is empty or invalid");
        }*/

        if(countryName == null || countryName.chars().allMatch(Character::isWhitespace)){
            throw new IllegalArgumentException("Country Name is empty or invalid");
        }

        Bson filter = eq("country", countryName);
        MongoCollection<Document> collection = mongoTemplate.getCollection("Formats");
        final List<org.bson.Document> results = new ArrayList<>();
        collection.find(filter).into(results);

        if (results.size() <= 0) {
            throw new IllegalArgumentException("Country Name is not supported");
        }

        return getPostalFormatFromDocument(results).get(0);
    }

    @Override
    public List<PostalFormat> getFormats() throws IOException {
        MongoCollection<Document> collection = mongoTemplate.getCollection("Formats");
        final List<org.bson.Document> results = new ArrayList<>();
        collection.find().into(results);
        return getPostalFormatFromDocument(results);
    }

    private List<PostalFormat> getPostalFormatFromDocument(final List<org.bson.Document> documents)
            throws JsonProcessingException {
        final String jsonDocuments = getObjectMapper.writeValueAsString(documents);

        final List<PostalFormat> postalFormatList = getObjectMapper.readValue(jsonDocuments,
                new TypeReference<List<PostalFormat>>() {
                });
        return postalFormatList;
    }
}
