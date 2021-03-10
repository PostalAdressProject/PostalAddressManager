package com.postal.api.composite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddressAggregate {

    private final int addressId;

    @NotNull
    @Size(min=2, max=30)
    private final String recipient_name;
    private final String business_name;
    private final String post_office_box;
    private final String rural_route_identifier;
    private final String station_informaton;
    private final String suite_apartment_number;
    private final String street_address2;
    private final String quarter_area;
    private final String neighborhood;
    private final String city_provinceCode;
    private final String city_town_locality;
    private final String locality_name;
    private final String municipality_name;
    private final String state;
    private final String province;
    private final String additional_information;

    @NotNull
    @Min(100)
    private final int postal_code;
    @NotNull
    @Size(min=2, max=30)
    private final String country;

    private final List<com.postal.api.core.street.Street> streetList;
    private final List<com.postal.api.core.location.Location> locationList;

}
