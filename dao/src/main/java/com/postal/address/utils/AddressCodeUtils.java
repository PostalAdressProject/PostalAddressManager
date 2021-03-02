package com.postal.address.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class AddressCodeUtils {

    public static String getCodeByCountry(final String country) throws IOException {
        final String jsonFilePath = "dao/src/main/resources/countries.json";
        final ObjectMapper objectMapper = new ObjectMapper();
        final Map<String, String> map = objectMapper.readValue(
                Paths.get(jsonFilePath).toFile(), Map.class);
        return map.getOrDefault(country, StringUtils.EMPTY);
    }
}