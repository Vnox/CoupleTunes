package com.example.vnox.coupletunesvx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

        ArrayList<VXLocation> vxLocList = new ArrayList<VXLocation>();
        vxLocList = DataHolder.vxLocList;




        for(int i = 0; i< vxLocList.size(); i++){
            retrievedList += vxLocList.get(i).getMyName();
            retrievedList += " : ";
            retrievedList += vxLocList.get(i).getLL().latitude;
            retrievedList += " , ";
            retrievedList += vxLocList.get(i).getLL().longitude;
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
            btnTag.setTag("button" + i);
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
        locList.setText("");

        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                DataHolder.myLoc = new ArrayList<LatLng>();
                DataHolder.myName = new ArrayList<String>();
                //locList.setText("All cleared ! Please add new location in mapView.");
                startActivity(new Intent(LocationListActivity.this, MainMenuActivity.class));

            }
        });

    }
}
