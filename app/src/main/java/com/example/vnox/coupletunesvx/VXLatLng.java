package com.example.vnox.coupletunesvx;


/**
 * Created by Leon on 5/31/2016.
 */
public class VXLatLng {
    public double latitude;
    public double longitude;
    public VXLatLng(){}

    public VXLatLng(Double inLat, Double inLng){
        this.latitude = inLat;
        this.longitude = inLng;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
