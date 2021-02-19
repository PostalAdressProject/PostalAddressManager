package com.postal.api.composite.product;

public class Location {

    private final double latitude;
    private final double longitude;

    public Location() {
        this.latitude = 0;
        this.longitude = 0;
    }

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
