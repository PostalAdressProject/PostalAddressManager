package com.postal.address.repository;

import com.postal.api.enums.Field;
import com.postal.api.models.Address;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository {

	List<Address> findAddressByCountry(final String countryName, final Map<Field, String> fieldsMap)
		throws IOException;
}
