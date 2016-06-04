package com.example.vnox.coupletunesvx;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Leon on 6/3/2016.
 */
public class JUnitTest_VXLatLng extends ActivityInstrumentationTestCase2 {

    MainMenuActivity mainMenuActivity;

    public JUnitTest_VXLatLng(){
        super(MainMenuActivity.class);
    }

    //Test constructors
    public void test_Constructors(){
        VXLatLng testLL = new VXLatLng(2.3, 2.3);
        assertEquals(testLL.getLatitude(), 2.3);
        assertEquals(testLL.getLongitude(), 2.3);
        VXLatLng newLL = new VXLatLng(0.0, 0.0);
        assertEquals(newLL.getLatitude(), 0.0);
        assertEquals(newLL.getLongitude(), 0.0);

    }

    // test setters and getters
    public void test_setget(){
        VXLatLng testLL = new VXLatLng(2.2, 2.2);
        testLL.setLatitude(0.1);
        testLL.setLongitude(0.2);
        assertEquals(testLL.getLatitude(), 0.1);
        assertEquals(testLL.getLongitude(), 0.2);
    }

}
