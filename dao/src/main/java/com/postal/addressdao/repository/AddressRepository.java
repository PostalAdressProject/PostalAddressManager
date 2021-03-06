package com.postal.addressdao.repository;

import com.postal.model.enums.Field;
import com.postal.model.models.Address;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Repository
public interface AddressRepository {

    List<Address> findAddressByCountry(final String countryName, final Map<Field, String> fieldsMap)
            throws IOException;

    List<Address> findAddressAcrossAllCountries(final Map<Field, String> fieldsMap)
            throws IOException;
}