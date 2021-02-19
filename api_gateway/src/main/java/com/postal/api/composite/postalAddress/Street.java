package com.postal.api.composite.product;

public class Street {

    private final String streetname;
    private final int building_number;
    private final String building_name;

    public Street() {
        this.streetname = null;
        this.building_number = 0;
        this.building_name = null;
    }

    public Street(String streetname, int building_number, String building_name) {
        this.streetname = streetname;
        this.building_number = building_number;
        this.building_name = building_name;
    }

    public String getStreetname() {
        return streetname;
    }

    public int getBuilding_number() {
        return building_number;
    }

    public String getBuilding_name() {
        return building_name;
    }

}
