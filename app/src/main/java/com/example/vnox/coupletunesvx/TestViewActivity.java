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
                final Firebase myFirebaseRef = new Firebase("https://cse110-vxcoupletones.firebaseio.com/user1");
                ArrayList<VXLocation> tlist = DataHolder.vxLocList;
                tlist.get(0).setArrival(true);
                myFirebaseRef.child("testloclist").setValue(tlist);
                tlist.get(0).setArrival(false);
                myFirebaseRef.child("testloclist").setValue(tlist);
            }
        });
    }
}
