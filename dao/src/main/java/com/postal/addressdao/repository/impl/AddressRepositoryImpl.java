package com.postal.addressdao.repository.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.model.Filters;
import com.postal.addressdao.repository.AddressRepository;
import com.postal.addressdao.utils.AddressCodeUtils;
import com.postal.model.enums.Field;
import com.postal.model.models.Address;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
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

