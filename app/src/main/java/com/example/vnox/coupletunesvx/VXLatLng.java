package com.example.vnox.coupletunesvx;


/**
 * Created by Leon on 5/31/2016.
 */

public class VXLatLng {
    public double latitude;
    public double longitude;
    public VXLatLng(){}     //default constructor

    public VXLatLng(Double inLat, Double inLng){
        this.latitude = inLat;
        this.longitude = inLng;
    }

    public double getLatitude() {       //get latitude
        return latitude;
    }

    public double getLongitude() {      //get longitude
        return longitude;
    }

    public void setLatitude(double latitude) {      //set latitude
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {       //set longitude
        this.longitude = longitude;
    }
}
