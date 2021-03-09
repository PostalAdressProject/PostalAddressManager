package com.postal.addressdao.model.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Address {

	@JsonProperty
	Map<String, String> _id;
	@JsonProperty("ID")
	String id;
	@JsonProperty("LON")
	String longitude;
	@JsonProperty("LAT")
	String latitude;
	@JsonProperty("NUMBER")
	String number;
	@JsonProperty("STREET")
	String street;
	@JsonProperty("UNIT")
	String unit;
	@JsonProperty("CITY")
	String city;
	@JsonProperty("POSTCODE")
	String postcode;
	@JsonProperty("REGION")
	String region;
	@JsonProperty("DISTRICT")
	String district;
	@JsonProperty("HASH")
	String hash;
}
