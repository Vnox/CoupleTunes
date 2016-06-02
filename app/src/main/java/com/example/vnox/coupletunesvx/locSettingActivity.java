package com.example.vnox.coupletunesvx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class locSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc_setting);


        String currName = DataHolder.vxLocList.get(DataHolder.currentChosen).getMyName();
        int currRing = 0;
        int currVibe = 0;
        final VXLocation currVXLoc;

        if(DataHolder.isMyList == true){
            // Editing my List
            currName = DataHolder.vxLocList.get(DataHolder.currentChosen).getMyName();
            currRing = DataHolder.vxLocList.get(DataHolder.currentChosen).getToneControl();
            currVibe = DataHolder.vxLocList.get(DataHolder.currentChosen).getVibeControl();
            currVXLoc = DataHolder.vxLocList.get(DataHolder.currentChosen);
        }else{
            currName = DataHolder.vxLocTemp.get(DataHolder.currentChosen).getMyName();
            currRing = DataHolder.vxLocTemp.get(DataHolder.currentChosen).getToneControl();
            currVibe = DataHolder.vxLocTemp.get(DataHolder.currentChosen).getVibeControl();
            currVXLoc = DataHolder.vxLocTemp.get(DataHolder.currentChosen);
        }
        //currVXLoc = DataHolder.vxLocTemp.get(DataHolder.currentChosen);

        final TextView infoTag = (TextView)findViewById(R.id.infoTag);
        infoTag.setText("You are now configuring location : " + currName);

        final TextView ringTag = (TextView)findViewById(R.id.ringtoneId);
        ringTag.setText("Ring Tone : " + currRing);

        final TextView vibeTag = (TextView)findViewById(R.id.vibeId);
        vibeTag.setText("Vibration Pattern : " + currVibe);

        final Button ringButton = (Button) findViewById(R.id.ringButton);



        ringButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                if (DataHolder.vxLocTemp.get(DataHolder.currentChosen).getToneControl() < 3){
                    DataHolder.vxLocTemp.get(DataHolder.currentChosen).setToneControl(DataHolder.vxLocTemp.get(DataHolder.currentChosen).getToneControl() + 1);
                }else{
                    DataHolder.vxLocTemp.get(DataHolder.currentChosen).setToneControl(1);
                }

                ringTag.setText("Ring Tone : " + DataHolder.vxLocTemp.get(DataHolder.currentChosen).getToneControl());
                Firebase myFirebaseRef = new Firebase("https://cse110-vxcoupletones.firebaseio.com/" + DataHolder.myUserName );
                //VXLocation testFirebaseLoc = new VXLocation(new LatLng(23.0,24.0), "hahaloc");
                //testFirebaseLoc.setTone(2);
                myFirebaseRef.child("testloclist").setValue(DataHolder.vxLocTemp);
            }


        });

        final Button vibeButton = (Button) findViewById(R.id.vibeButton);

        vibeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

            }
        });



        final Button testButton = (Button) findViewById(R.id.testPlayButton);

        testButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                currVXLoc.vibrateAndRingTest(locSettingActivity.this);

            }
        });

        final Button deleteButton = (Button) findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                DataHolder.vxLocList.remove(DataHolder.currentChosen);
                ArrayList tempList = DataHolder.vxLocList;
                Firebase myFirebaseRef = new Firebase("https://cse110-vxcoupletones.firebaseio.com/" + DataHolder.partnerName );
                //VXLocation testFirebaseLoc = new VXLocation(new LatLng(23.0,24.0), "hahaloc");
                //testFirebaseLoc.setTone(2);
                myFirebaseRef.child("testloclist").setValue(tempList);
                startActivity(new Intent(locSettingActivity.this, LocationListActivity.class));

            }
        });

        if(DataHolder.isMyList == true){
            ringButton.setEnabled(false);
            vibeButton.setEnabled(false);
        }else{
            deleteButton.setEnabled(false);
        }
    }
}
