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

    /**
     * Test 1: A List of Partner's Favorite Location
     * Given that I have a partner
     * And I haven’t added any favorite locations in the list
     * When I tab on my partner’s favorite locations
     * Then it will show the contents(name, location and tone info) of the list which allow me to edit. 
     * /
    
    
    /**
     * Test 2: An Ordered List of Partner’s Visiting History of a Day
     * Given that I have a partner and a non-empty list of my partner’s favorite location
     * And my partner visited some locations on the list today
     * When I click on ‘History of Today’
     * Then it will show the location(s) and time he/she visited today.
     * /
    
    /**
     * Test 3: Tones/Vibrations for My Partner’s Arrival and Departure of a Favorite Location
     * Given that I have a partner, a non-empty location list
     * And both my device is connected with the Internet 
     * And a location in the list is set to a tone/vibration
     * When my partner visit the location, the tone rings/device vibrates
     * /
    
    /**
     * Test 4: Tone Setting
     * Given I have a partner, a non-empty location list
     * When I go to the tone setting and click on one of the location in the list 
     * I can change the tone of that location(arrival and departure)
     * /
    
    /**
     * Test 5: Vibration Setting
     * Given I have a partner, a non-empty location list
     * When I go to the vibration setting and click on one of the location in the list 
     * I can change the vibration of that location(arrival and departure)
     * /
    
    /**
     * Test 6: Notification Options of Tones and Vibrations
     * Given that I set a location with both tones and vibrations in my location list
     * And my device is connected to the Internet
     * When my partner visite/depart the location
     * There will be both tones and vibration
     * /
    

    }





}
