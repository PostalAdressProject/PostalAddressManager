package com.postal.api.core.street;

public class Street {
    private String streetname;
    private int building_number;
    private String building_name;

    public Street(String streetname, int building_number, String building_name) {
        this.streetname = streetname;
        this.building_number = building_number;
        this.building_name = building_name;
    }

    public Street() {
        this.streetname = null;
        this.building_number = 0;
        this.building_name = null;
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

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public void setBuilding_number(int building_number) {
        this.building_number = building_number;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

}
