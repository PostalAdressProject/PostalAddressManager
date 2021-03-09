package com.postal.addressdao.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public class AddressCodeUtils {

	public static String getCodeByCountry(final String country) throws IOException {
		final String jsonFilePath = "/home/altanai/Documents/architecture/PostalAddressManager/dao/src/main/resources/countries.json" ;//"resources/countries.json";
		final ObjectMapper objectMapper = new ObjectMapper();
		final Map<String, String> map = objectMapper.readValue(
				Paths.get(jsonFilePath).toFile(), Map.class);
		return map.getOrDefault(country, StringUtils.EMPTY);
	}
}