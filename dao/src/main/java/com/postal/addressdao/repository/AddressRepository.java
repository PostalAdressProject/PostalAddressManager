package com.postal.addressdao.repository;

import com.postal.addressdao.model.enums.Field;
import com.postal.addressdao.model.models.Address;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Repository
public interface AddressRepository {

    List<Address> findAddressByCountry(final String countryName, final Map<Field, String> fieldsMap)
            throws IOException;
}