package com.postal.api.composite.address;

import java.util.List;

public class AddressAggregate {

    private final int addressId;
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
    private final int postal_code;
    private final List<Street> streetList;
    private final List<Location> locationList;

    public AddressAggregate(int addressId, String recipient_name, String business_name, String post_office_box, String rural_route_identifier, String station_informaton, String suite_apartment_number, String street_address2, String quarter_area, String neighborhood, String city_provinceCode, String city_town_locality, String locality_name, String municipality_name, String state, String province, String additional_information, int postal_code, List<Street> streetList, List<Location> locationList) {
        this.addressId = addressId;
        this.recipient_name = recipient_name;
        this.business_name = business_name;
        this.post_office_box = post_office_box;
        this.rural_route_identifier = rural_route_identifier;
        this.station_informaton = station_informaton;
        this.suite_apartment_number = suite_apartment_number;
        this.street_address2 = street_address2;
        this.quarter_area = quarter_area;
        this.neighborhood = neighborhood;
        this.city_provinceCode = city_provinceCode;
        this.city_town_locality = city_town_locality;
        this.locality_name = locality_name;
        this.municipality_name = municipality_name;
        this.state = state;
        this.province = province;
        this.additional_information = additional_information;
        this.postal_code = postal_code;
        this.streetList = streetList;
        this.locationList = locationList;

    }
    public AddressAggregate() {
        this.addressId = 0;
        this.recipient_name = null;
        this.business_name = null;
        this.post_office_box = null;
        this.rural_route_identifier = null;
        this.station_informaton = null;
        this.suite_apartment_number = null;
        this.street_address2 = null;
        this.quarter_area = null;
        this.neighborhood = null;
        this.city_provinceCode = null;
        this.city_town_locality = null;
        this.locality_name = null;
        this.municipality_name = null;
        this.state = null;
        this.province = null;
        this.additional_information = null;
        this.postal_code = 0;
        this.streetList = null;
        this.locationList = null;
    }
    public int getAddressId() {
        return addressId;
    }

    public String getRecipient_name() {
        return recipient_name;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public String getPost_office_box() {
        return post_office_box;
    }

    public String getRural_route_identifier() {
        return rural_route_identifier;
    }

    public String getStation_informaton() {
        return station_informaton;
    }

    public String getSuite_apartment_number() {
        return suite_apartment_number;
    }

    public String getStreet_address2() {
        return street_address2;
    }

    public String getQuarter_area() {
        return quarter_area;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity_provinceCode() {
        return city_provinceCode;
    }

    public String getCity_town_locality() {
        return city_town_locality;
    }

    public String getLocality_name() {
        return locality_name;
    }

    public String getMunicipality_name() {
        return municipality_name;
    }

    public String getState() {
        return state;
    }

    public String getProvince() {
        return province;
    }

    public String getAdditional_information() {
        return additional_information;
    }

    public int getPostal_code() {
        return postal_code;
    }

    public List<Street> getStreetList() {
        return streetList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

}
