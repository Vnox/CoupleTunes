package com.example.vnox.coupletunesvx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);




        final Button mapButton = (Button) findViewById(R.id.mapViewButton);
        final Button pairButton = (Button) findViewById(R.id.pairButton);
        final Button locationButton = (Button) findViewById(R.id.locationButton);
        final Button testButton = (Button) findViewById(R.id.testButton);
        final Button partnerLocButton = (Button) findViewById(R.id.partnerLocListButton);

        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                startActivity(new Intent(MainMenuActivity.this, MapViewActivity.class));
            }
        });

        pairButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                startActivity(new Intent(MainMenuActivity.this, pairActivity.class));
            }
        });

        locationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                startActivity(new Intent(MainMenuActivity.this, LocationListActivity.class));
            }
        });

        testButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                startActivity(new Intent(MainMenuActivity.this, TestViewActivity.class));
            }
        });

        partnerLocButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                startActivity(new Intent(MainMenuActivity.this, PartnerListView.class));
            }
        });



    }
}
