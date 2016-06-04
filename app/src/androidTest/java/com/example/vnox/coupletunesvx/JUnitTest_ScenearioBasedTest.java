package com.example.vnox.coupletunesvx;

import android.provider.ContactsContract;
import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

/**
 * Created by Leon on 6/3/2016.
 */
public class JUnitTest_ScenearioBasedTest extends ActivityInstrumentationTestCase2 {

    MainMenuActivity mainMenuActivity;
    //LocationListActivity locationListActivity;

    public JUnitTest_ScenearioBasedTest() {
        super(MainMenuActivity.class);
    }

    // Test 1, scenario one.
    // Scenario 1: Have partner, non-empty list, tone/vibration set, with internet
    // Given that I have a partner, a non-empty location list
    // And both my device is connected with the Internet
    // And a location in the list is set to a tone/vibration
    //When my partner visit the location, the tone rings/device vibrates

    public void test_sc1() {

        // check partner status
        DataHolder.partnerName = "test name";
        DataHolder.theSoundSetting = 3;

        //test for settings
        assertEquals(DataHolder.theSoundSetting, 3);

        //if list is non empty as described
        DataHolder.vxLocList = new ArrayList<>();
        VXLatLng testLL = new VXLatLng(2.0, 2.0);
        VXLocation testLoc = new VXLocation(testLL, "testlocation");

        //ste tone and vibration
        testLoc.setVibeControl(2);
        testLoc.setToneControl(3);
        DataHolder.vxLocList.add(testLoc);

        Boolean testVisited = true;
        if (testVisited) {
            testLoc.vibrateAndRingAri(getActivity());
            // hear the sound in backgroud ! yayyyy !
        }


    }

    // Test 2: Have partner, non-empty list, tone/vibration set, no internet
    // Given that I have a partner, a non-empty location list
    // And my device is not connected with the Internet
    // And a location in the list is set to a tone/vibration
    // When my partner visit the location
    // No tones, no vibration

    public void test_sc2() {

        // check partner status
        DataHolder.partnerName = "test name";
        DataHolder.theSoundSetting = 3;

        //test for settings
        assertEquals(DataHolder.theSoundSetting, 3);

        //if list is non empty as described
        DataHolder.vxLocList = new ArrayList<>();
        VXLatLng testLL = new VXLatLng(2.0, 2.0);
        VXLocation testLoc = new VXLocation(testLL, "testlocation");

        //set NO tone and NO vibration

        DataHolder.vxLocList.add(testLoc);

        Boolean testVisited = true;

        // set silent mode atually
        DataHolder.theSoundSetting = 4;

        if (testVisited) {
            testLoc.vibrateAndRingAri(getActivity());
            // hear the sound in backgroud ! yayyyy !
        }


    }

    //Test 3: Empty list, tone setting
   // Given I have a partner, an empty location list
    //When I go to the tone setting
    //There will be no location for me to change

    public void test_sc3(){
        // the empty list test
        DataHolder.vxLocList = new ArrayList<>();

        //add partner
        // check partner status
        DataHolder.partnerName = "test name";
        DataHolder.theSoundSetting = 3;

        // testing the length of the list !
        assertEquals(DataHolder.vxLocList.size(), 0);
    }

    // Test 4 : Given that I set a location with just vibration in my location list
    // And my device is connected to the Internet
    // when my partner visite/depart the location
    // There will be just vibration
    public void test_sc4(){
        // check partner status
        DataHolder.partnerName = "test name";
        DataHolder.theSoundSetting = 3;

        //test for settings
        assertEquals(DataHolder.theSoundSetting, 3);

        //if list is non empty as described
        DataHolder.vxLocList = new ArrayList<>();
        VXLatLng testLL = new VXLatLng(2.0, 2.0);
        VXLocation testLoc = new VXLocation(testLL, "testlocation");

        //set NO tone and NO vibration

        DataHolder.vxLocList.add(testLoc);

        Boolean testVisited = true;

        // set vibration only !!
        DataHolder.theSoundSetting = 1;

        if (testVisited) {
            testLoc.vibrateAndRingAri(getActivity());
            // hear the sound in backgroud ! yayyyy !
        }
    }

    // Scenario 1: Have partner, non-empty location list
    // Given that I have a partner
    // And I have added some favorite locations in the list
    // When I tab on my partner’s favorite locations
    // Then it will show the contents(name, location and tone info) of the list which allow me to edit.

    public void test_sc5(){
        // check partner status
        DataHolder.partnerName = "test name";
        DataHolder.theSoundSetting = 3;

        //test for settings
        assertEquals(DataHolder.theSoundSetting, 3);

        //if list is non empty as described
        DataHolder.vxLocList = new ArrayList<>();
        VXLatLng testLL = new VXLatLng(2.0, 2.0);
        VXLocation testLoc = new VXLocation(testLL, "testlocation");

        //ste tone and vibration
        testLoc.setVibeControl(2);
        testLoc.setToneControl(3);
        DataHolder.vxLocList.add(testLoc);

        Boolean testVisited = true;
        if (testVisited) {
            testLoc.vibrateAndRingAri(getActivity());
            // hear the sound in backgroud ! yayyyy !
        }

        // check list length
        assertEquals(DataHolder.vxLocList.size(), 1);
    }

}
