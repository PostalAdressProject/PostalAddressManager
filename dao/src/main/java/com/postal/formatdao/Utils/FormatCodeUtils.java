package com.postal.formatdao.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postal.model.models.PostalFormat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class FormatCodeUtils {

    public static PostalFormat getFormatByCountryName(final String countryName) throws IOException {
        final String jsonFilePath = "dao/src/main/resources/PostalFormat.json";
        final ObjectMapper objectMapper = new ObjectMapper();
        final Set<PostalFormat> postalFormatSet = fromJSON(new TypeReference<Set<PostalFormat>>() {}, Paths.get(jsonFilePath).toFile());
        // objectMapper.readValue(Paths.get(jsonFilePath).toFile(), PostalFormat[].class);
        //List<PostalFormat> lst = Arrays.asList(postalFormatSet);
        PostalFormat postalFormat = postalFormatSet.stream()
                .filter(s -> s.country.equals(countryName))
                .findFirst()
                .get() ;

        return postalFormat;
    }

    private static <T> T fromJSON(final TypeReference<T> type,
                                  final File jsonPacket) {
        T data = null;

        try {
            data = new ObjectMapper().readValue(jsonPacket, type);
        } catch (Exception e) {
            // Handle the problem
        }
        return data;
    }
}
