package com.example.vnox.coupletunesvx;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Leon on 5/7/2016.
 */
public class DataHolder {
    private static String data;
    static ArrayList<LatLng> myLoc = new ArrayList<>();
    static ArrayList<String> myName = new ArrayList<>();
    static String myPhone = "";

    public static String getData() {return data;}
    public static void setData(String data) {DataHolder.data = data;}
}
