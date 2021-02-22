package com.postal.api.core.address;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private int addressId;
    private String recipient_name;
    private String business_name;
    private String post_office_box;
    private String rural_route_identifier;
    private String station_informaton;
    private String suite_apartment_number;
    private String street_address2;
    private String quarter_area;
    private String neighborhood;
    private String city_provinceCode;
    private String city_town_locality;
    private String locality_name;
    private String municipality_name;
    private String state;
    private String province;
    private String additional_information;
    private int postal_code;
    private String serviceAddress;
}
