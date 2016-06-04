package com.example.vnox.coupletunesvx;

import android.test.ActivityInstrumentationTestCase2;
import com.example.vnox.coupletunesvx.*;

/**
 * Created by Leon on 6/3/2016.
 */
public class JUnitTest_VXClasses extends ActivityInstrumentationTestCase2<MainMenuActivity> {

    MainMenuActivity mainMenuActivity;

    public JUnitTest_VXClasses(){
        super(MainMenuActivity.class);
    }

    // Testing construction method
    public void test_VXLocation_Constructor(){
        //Testing VXLocation class
        mainMenuActivity = getActivity();
        VXLocation testVXLoc = new VXLocation();
        assertEquals(testVXLoc.getToneControl(), 1);
        assertEquals(testVXLoc.getVibeControl(), 1);
        assertEquals(testVXLoc.getMyName(), "");
        //assertEquals(testVXLoc.getToneControl(), 1);

        assertFalse(testVXLoc.isArrival());
        assertFalse(testVXLoc.isDeparture());

    }

    // Testing setter getters
    public void test_VXLocation_setget(){
        VXLocation testLoc = new VXLocation();
        testLoc.setToneControl(2);
        testLoc.setVibeControl(3);
        assertEquals(testLoc.getToneControl(), 2);
        assertEquals(testLoc.getVibeControl(), 3);
        assertFalse(testLoc.isArrival());
        assertFalse(testLoc.isDeparture());
    }

    // Testing other methods
    public void test_VXLocation_others(){
        VXLocation testLoc = new VXLocation();
        testLoc.setToneControl(2);
        testLoc.setVibeControl(3);
        assertEquals(testLoc.getToneControl(), 2);
        assertEquals(testLoc.getVibeControl(), 3);
    }
}
