package com.postal.addressdao.repository.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.model.Filters;
import com.postal.addressdao.repository.AddressRepository;
import com.postal.addressdao.utils.AddressCodeUtils;
import com.postal.model.enums.Field;
import com.postal.model.models.Address;
import org.apache.commons.lang3.StringUtils;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import java.util.Map.Entry;
import java.util.Iterator;

import com.postal.model.models.Country;
import org.apache.commons.lang3.StringUtils;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;


@Component
public class AddressRepositoryImpl implements AddressRepository {

	private static final String REGEX = "^{value}";
	private static final String REPLACE = "{value}";

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public List<Address> findAddressByCountry(final String countryName,
		final Map<Field, String> fieldsMap)
		throws IOException {
		String collectionName = AddressCodeUtils.getCodeByCountry(countryName);
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
		final Bson filter = Filters.and(getFiltersForPrefix(fieldsMap));
		mongoTemplate.getCollection(collectionName)
			.find(filter).into(results);
		return getAddressFromDocument(results);
	}

	public List<Address> findAddressAcrossAllCountries(final Map<Field, String> fieldsMap) throws IOException{
		Map<String, String> allCountryNames =AddressCodeUtils.getAllCountryCodes();
        Iterator<Entry<String, String>> iterator = allCountryNames.entrySet().iterator();
		List<Address> result = new ArrayList<>();
		int matchCount = 0;
        while(iterator.hasNext()) {
			Map.Entry<String, String> set = iterator.next();
			String countryName = set.getKey();
			List<Address> matches = findAddressByCountry(countryName, fieldsMap);
			for(Address address : matches) {
				result.add(address);
				matchCount++;
				if(matchCount == 5) return result;
			}
		}
		return result;
	}

	private Iterable<Bson> getFiltersForPrefix(final Map<Field, String> fieldsMap) {
		final List<Bson> filters = new ArrayList<>();
		final Iterator<Field> keyItr = fieldsMap.keySet().iterator();
		while (keyItr.hasNext()) {
			final Field field = keyItr.next();
			final String formattedRegex = StringUtils
				.replace(REGEX, REPLACE, fieldsMap.get(field));
			final Pattern regex = Pattern.compile(formattedRegex, Pattern.CASE_INSENSITIVE);
			filters.add(Filters.eq(field.toString(), regex));
		}
		return filters;
	}

	private List<Address> getAddressFromDocument(final List<org.bson.Document> documents)
		throws JsonProcessingException {
		final String jsonDocuments = objectMapper.writeValueAsString(documents);

		final List<Address> addressList = objectMapper
			.readValue(jsonDocuments, new TypeReference<List<Address>>() {
			});
		return addressList;
	}
}

