package com.postal.addressdao.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature; // test
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.postal.model.models.Country;
import org.apache.commons.lang3.StringUtils;

public class AddressCodeUtils {

	public static String getCodeByCountry(final String country) throws IOException {
		final String jsonFilePath = "dao/src/main/resources/countries.json";
		final ObjectMapper objectMapper = new ObjectMapper();
		final Map<String, String> map = objectMapper.readValue(
			Paths.get(jsonFilePath).toFile(), Map.class);
		return map.getOrDefault(country, StringUtils.EMPTY);
	}
	public static Map<String, String> getAllCountryCodes() throws IOException {
		final String jsonFilePath = "dao/src/main/resources/countries.json";
		final ObjectMapper objectMapper = new ObjectMapper();
		final Map<String, String> map = objectMapper.readValue(
				Paths.get(jsonFilePath).toFile(), Map.class);
		return map;
	}
}