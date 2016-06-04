package com.example.vnox.coupletunesvx;

import com.example.vnox.coupletunesvx.VXLatLng;

import java.util.ArrayList;

/**
 * The class that actually holds location data
 * @author      Shuangli Zhou, Zuqi Chen
 */
public class DataHolder {
    private static String data;
    static ArrayList<VXLatLng> myLoc = new ArrayList<>();
    static ArrayList<String> myName = new ArrayList<>();
    static String myUserName = "";
    static String partnerName = "";
    static int currentChosen;
    static ArrayList<VXLocation> vxLocList = new ArrayList<>();
    static ArrayList<VXLocation> vxLocTemp = new ArrayList<>();
    static boolean isMyList;
    static ArrayList<String> historyList = new ArrayList<>();

    // sound setting
    // 1 --> only sound
    // 2 -> only vibe
    // 3 -> both vibe and sound
    // 0 -> nothing
    static int theSoundSetting = 3;

    /**
     * Getter method to get the data object
     * @return the string representation of the data object
     */
    public static String getData() {return data;}

    /**
     * Setter to set the data field of the object
     * @param  String data to set
     */
    public static void setData(String data) {DataHolder.data = data;}
}
