package com.example.vnox.coupletunesvx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class TestViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);

        final Button playButton = (Button) findViewById(R.id.playButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                VXLocation testLocation = new VXLocation();
                testLocation.setToneControl(2);
                testLocation.setVibeControl(3);
                testLocation.vibrateAndRingAri(TestViewActivity.this);
            }
        });

        final Button modeButton = (Button) findViewById(R.id.modeButton);
        if(DataHolder.theSoundSetting == 3){
            modeButton.setText("Normal Mode");
        }else if(DataHolder.theSoundSetting == 4){
            modeButton.setText("Silent Mode");
        }else if(DataHolder.theSoundSetting == 2){
            modeButton.setText("Vibrate only Mode");
        }else if(DataHolder.theSoundSetting == 1){
            modeButton.setText("Sound only Mode");
        }

        modeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //change differen modes ...
                if(DataHolder.theSoundSetting == 1){
                    DataHolder.theSoundSetting = 2;
                    modeButton.setText("Vibrate Only Mode");
                }
                else if(DataHolder.theSoundSetting == 2){
                    DataHolder.theSoundSetting = 3;
                    modeButton.setText("Normal Mode");
                }
                else if(DataHolder.theSoundSetting == 3){
                    DataHolder.theSoundSetting = 4;
                    modeButton.setText("Silent Mode");
                }
                else if(DataHolder.theSoundSetting == 4){
                    DataHolder.theSoundSetting = 1;
                    modeButton.setText("Sound Only Mode");
                }

            }
        });
    }
}
