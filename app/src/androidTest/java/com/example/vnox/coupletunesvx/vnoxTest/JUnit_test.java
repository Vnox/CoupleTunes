package com.example.vnox.coupletunesvx.vnoxTest;

import android.test.ActivityInstrumentationTestCase2;

import com.example.vnox.coupletunesvx.MainMenuActivity;
import com.example.vnox.coupletunesvx.MapViewActivity;
import com.example.vnox.coupletunesvx.pairActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Leon on 5/8/2016.
 */
public class JUnit_test extends ActivityInstrumentationTestCase2<MapViewActivity> {

    MapViewActivity mapViewActivity;
    pairActivity myPairActivity;

    public JUnit_test(){
        super(MapViewActivity.class);
    }

    public void test_saveLocations(){
        mapViewActivity = getActivity();
        assertTrue(mapViewActivity.saveLocations());
    }

    public void test_retrieveMarkers(){
        mapViewActivity = getActivity();
        //test if it returns true
        assertTrue(mapViewActivity.retrieveMarkers());

    }

    //Scenario Based Tests
    public void test_beforeAdd(){
        mapViewActivity = getActivity();
        //Before adding location, there shouldn't be anything in it.
        assertEquals(0, mapViewActivity.getMyLocationList().size());
    }

    public void test_firstAdd(){
        mapViewActivity = getActivity();
        LatLng testLL = new LatLng(0, 0);
        mapViewActivity.getMyLocationList().add(testLL);
        //Before adding location, there shouldn't be anything in it.
        assertEquals(1, mapViewActivity.getMyLocationList().size());
    }

    // Different Cases
    public void test_compareLocations(){
        mapViewActivity = getActivity();

        LatLng LLA = new LatLng(1, 1);
        LatLng LLB = new LatLng(1.000001, 1.000001);
        LatLng LLC = new LatLng(9, 9);

        assertEquals(true, mapViewActivity.checkLocationNear(LLA, LLB));
        assertEquals(false, mapViewActivity.checkLocationNear(LLB, LLC));
        assertEquals(false, mapViewActivity.checkLocationNear(LLA, LLC));
        assertEquals(true, mapViewActivity.checkLocationNear(LLA, LLA));

    }





}
