package com.postal.addressdao.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.model.Filters;
import com.postal.addressdao.repository.AddressRepository;
import com.postal.model.enums.Field;
import com.postal.model.models.Address;
import org.apache.commons.lang3.StringUtils;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class AddressRepositoryImpl implements AddressRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Address> findAddressByCountry(final String countryName,
                                              final Map<Field, String> fieldsMap)
            throws IOException {
        String collectionName = com.postal.address.utils.AddressCodeUtils.getCodeByCountry(countryName);
        collectionName =
                StringUtils.isBlank(collectionName) ? collectionName : collectionName.toLowerCase();
        System.out.println("CountryCode:" + collectionName);
        if (StringUtils.isBlank(collectionName)) {
            throw new IllegalArgumentException("Country Name is empty or invalid");
        }
        final boolean isPresent = mongoTemplate.collectionExists(collectionName);
        if (!isPresent) {
            throw new IllegalArgumentException("Country Name is not supported");
        }
        final List<org.bson.Document> results = new ArrayList<>();
        mongoTemplate.getCollection(collectionName)
                .find(Filters.and(getFilters(fieldsMap))).into(results);
        return getAddressFromDocument(results);
    }

    private Iterable<Bson> getFilters(final Map<Field, String> fieldsMap) {
        final List<Bson> filters = new ArrayList<>();
        final Iterator<Field> keyItr = fieldsMap.keySet().iterator();
        while (keyItr.hasNext()) {
            final Field field = keyItr.next();
            filters.add(Filters.eq(field.toString(), fieldsMap.get(field)));
        }
        return filters;
    }

    private List<Address> getAddressFromDocument(final List<org.bson.Document> documents)
            throws JsonProcessingException {
        final String jsonDocuments = objectMapper.writeValueAsString(documents);
        final TypeReference<List<Address>> typeRef
                = new TypeReference<List<Address>>() {
        };

        final List<Address> results = objectMapper.readValue(jsonDocuments, typeRef);
        return results;
    }
}
