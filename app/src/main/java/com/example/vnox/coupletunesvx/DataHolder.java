package com.example.vnox.coupletunesvx;

import com.example.vnox.coupletunesvx.VXLatLng;

import java.util.ArrayList;

/**
 * Created by Leon on 5/7/2016.
 */
public class DataHolder {
    private static String data;
    static ArrayList<VXLatLng> myLoc = new ArrayList<>();
    static ArrayList<String> myName = new ArrayList<>();
    static String myPhone = "";
    static int currentChosen;
    static ArrayList<VXLocation> vxLocList = new ArrayList<>();
    static ArrayList<VXLocation> vxLocTemp = new ArrayList<>();

    // sound setting
    // 1 --> only sound
    // 2 -> only vibe
    // 3 -> both vibe and sound
    // 0 -> nothing
    static int theSoundSetting;

    public static String getData() {return data;}
    public static void setData(String data) {DataHolder.data = data;}
}
