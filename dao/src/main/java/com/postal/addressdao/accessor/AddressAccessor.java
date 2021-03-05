package com.postal.addressdao.accessor;

import com.postal.addressdao.exception.AddressDataAccessException;
import com.postal.addressdao.repository.AddressRepository;
import com.postal.model.enums.Field;
import com.postal.model.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AddressAccessor {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAddressByCountry(final String countryName,
                                              final Map<Field, String> fieldMap) throws AddressDataAccessException {
        try {
            return addressRepository.findAddressByCountry(countryName, fieldMap);
        } catch (final IllegalArgumentException e) {
            System.out.println("Illegal argument exception occurred:" + e.getMessage());
            throw new IllegalArgumentException(e);
        } catch (final Exception e) {
            System.out.println("Exception occurred:" + e.getMessage());
            throw new AddressDataAccessException(e.getMessage(), e);
        }
    }
}
