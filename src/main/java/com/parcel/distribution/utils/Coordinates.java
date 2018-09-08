package com.parcel.distribution.utils;

import lombok.Data;

@Data
public class Coordinates {

    private Double lat;
    private Double lng;

    public Coordinates(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
