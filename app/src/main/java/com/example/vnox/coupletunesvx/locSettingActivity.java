package com.example.vnox.coupletunesvx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class locSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc_setting);

        final String currName = DataHolder.vxLocList.get(DataHolder.currentChosen).getMyName();
        final int currRing = DataHolder.vxLocList.get(DataHolder.currentChosen).getToneControl();
        final int currVibe = DataHolder.vxLocList.get(DataHolder.currentChosen).getVibeControl();
        final VXLocation currVXLoc = DataHolder.vxLocList.get(DataHolder.currentChosen);

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
                if (DataHolder.vxLocList.get(DataHolder.currentChosen).getToneControl() < 3){
                    DataHolder.vxLocList.get(DataHolder.currentChosen).setToneControl(DataHolder.vxLocList.get(DataHolder.currentChosen).getToneControl() + 1);
                }else{
                    DataHolder.vxLocList.get(DataHolder.currentChosen).setToneControl(1);
                }

                ringTag.setText("Ring Tone : " + DataHolder.vxLocList.get(DataHolder.currentChosen).getToneControl());
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
    }
}
