package com.postal.model.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */
public class PostalFormat {
    public Map<String, String> _id;
    public String country;
    @JsonProperty("Format")
    public Format Format;
}


