package com.example.vnox.coupletunesvx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class LocationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        final TextView locList = (TextView) findViewById(R.id.locList);
        Button clearButton = (Button) findViewById(R.id.clearButton);
        String retrievedList = "";
        ArrayList<String> nameList = DataHolder.myName;
        ArrayList<LatLng> locationList = DataHolder.myLoc;
        for(int i = 0; i< nameList.size(); i++){
            retrievedList += nameList.get(i);
            retrievedList += " : ";
            retrievedList += locationList.get(i).latitude;
            retrievedList += " , ";
            retrievedList += locationList.get(i).longitude;
            retrievedList += "\n";
        }
        locList.setText(retrievedList);

        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                DataHolder.myLoc = new ArrayList<LatLng>();
                DataHolder.myName = new ArrayList<String>();
                locList.setText("All cleared ! Please add new location in mapView.");
            }
        });

    }
}
