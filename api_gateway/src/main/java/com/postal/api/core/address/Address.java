package com.postal.api.core.address;

import lombok.Getter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
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

//    public Address(int addressId, String recipient_name, String business_name, String post_office_box, String rural_route_identifier, String station_informaton, String suite_apartment_number, String street_address2, String quarter_area, String neighborhood, String city_provinceCode, String city_town_locality, String locality_name, String municipality_name, String state, String province, String additional_information, int postal_code, String serviceAddress) {
//        this.addressId = addressId;
//        this.recipient_name = recipient_name;
//        this.business_name = business_name;
//        this.post_office_box = post_office_box;
//        this.rural_route_identifier = rural_route_identifier;
//        this.station_informaton = station_informaton;
//        this.suite_apartment_number = suite_apartment_number;
//        this.street_address2 = street_address2;
//        this.quarter_area = quarter_area;
//        this.neighborhood = neighborhood;
//        this.city_provinceCode = city_provinceCode;
//        this.city_town_locality = city_town_locality;
//        this.locality_name = locality_name;
//        this.municipality_name = municipality_name;
//        this.state = state;
//        this.province = province;
//        this.additional_information = additional_information;
//        this.postal_code = postal_code;
//        this.serviceAddress = serviceAddress;
//    }
//
//    public Address() {
//        this.addressId = 0;
//        this.recipient_name = null;
//        this.business_name = null;
//        this.post_office_box = null;
//        this.rural_route_identifier = null;
//        this.station_informaton = null;
//        this.suite_apartment_number = null;
//        this.street_address2 = null;
//        this.quarter_area = null;
//        this.neighborhood = null;
//        this.city_provinceCode = null;
//        this.city_town_locality = null;
//        this.locality_name = null;
//        this.municipality_name = null;
//        this.state = null;
//        this.province = null;
//        this.additional_information = null;
//        this.postal_code = 0;
//        this.serviceAddress = null;
//    }

//    public int getAddressId() {
//        return addressId;
//    }
//
//    public void setAddressId(int addressId) {
//        this.addressId = addressId;
//    }
}
