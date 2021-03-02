package com.postal.model.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

	String ID;
	String longitude;
	String latitude;
	String number;
	String street;
	String unit;
	String city;
	String postCode;
	String country;
	String hash;
}
