package com.example.vnox.coupletunesvx;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
//import com.google.android.gms.maps.model.LatLng;
import com.example.vnox.coupletunesvx.VXLatLng;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class LocationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        DataHolder.isMyList = true;

        final TextView locList = (TextView) findViewById(R.id.locList);
        Button clearButton = (Button) findViewById(R.id.clearButton);
        String retrievedList = "";

        ArrayList<VXLocation> vxLocList = new ArrayList<VXLocation>();


        // Actually contacting firebase here
        Firebase myFirebaseRef = new Firebase("https://cse110-vxcoupletones.firebaseio.com/" + DataHolder.partnerName );
        //VXLocation testFirebaseLoc = new VXLocation(new LatLng(23.0,24.0), "hahaloc");
        //testFirebaseLoc.setTone(2);
        //myFirebaseRef.child("testloclist").setValue(vxLocList);

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<VXLocation>> vxlocindicator = new GenericTypeIndicator<ArrayList<VXLocation>>() {};
                ArrayList<VXLocation> vxLocData = dataSnapshot.child("testloclist").getValue(vxlocindicator);
                DataHolder.vxLocList = vxLocData;
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }


        });

        vxLocList = DataHolder.vxLocList;
        if(vxLocList != null){
            for(int i = 0; i< vxLocList.size(); i++){
                retrievedList += vxLocList.get(i).getMyName();
                retrievedList += " : ";
                retrievedList += vxLocList.get(i).getMyLatLng().latitude;
                retrievedList += " , ";
                retrievedList += vxLocList.get(i).getMyLatLng().longitude;
                retrievedList += "\n";
                //the layout on which you are working
                RelativeLayout layout = (RelativeLayout) findViewById(R.id.llid);

                //set the properties for button
                Button btnTag = new Button(this);
                btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                btnTag.setText("Location " + (i + 1) + " : " + vxLocList.get(i).getMyName());
                btnTag.setY(btnTag.getY() + i * 140);
                btnTag.setY(btnTag.getY() + 240);
                btnTag.setX(180);
                //btnTag.setTag("button" + i);
                final int theTag = i;

                btnTag.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Perform action on click
                        DataHolder.currentChosen = theTag;
                        startActivity(new Intent(LocationListActivity.this, locSettingActivity.class));
                    }
                });

                //add button to the layout
                layout.addView(btnTag);


            }


        }

        locList.setText("");

        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                DataHolder.myLoc = new ArrayList<VXLatLng>();
                DataHolder.myName = new ArrayList<String>();
                DataHolder.vxLocList = new ArrayList<VXLocation>();
                //locList.setText("All cleared ! Please add new location in mapView.");
                startActivity(new Intent(LocationListActivity.this, MainMenuActivity.class));

            }
        });

        clearButton.setEnabled(false);

    }
}
