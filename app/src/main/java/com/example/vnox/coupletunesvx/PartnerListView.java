package com.example.vnox.coupletunesvx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class PartnerListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_list_view);

        //final TextView locList = (TextView) findViewById(R.id.partnerloclist);
        String retrievedList = "";

        ArrayList<VXLocation> vxLocList = new ArrayList<>();
        //vxLocList = DataHolder.vxLocList;
        //actually retrieve data here
        vxLocList = DataHolder.vxLocTemp;
        //Toast.makeText(PartnerListView.this,"Added, total length is: " + vxLocList.size(), Toast.LENGTH_SHORT).show();
        if(vxLocList == null){return;}
        for(int i = 0; i< vxLocList.size(); i++){
            retrievedList += vxLocList.get(i).getMyName();
            retrievedList += " : ";
            retrievedList += vxLocList.get(i).getMyLatLng().latitude;
            retrievedList += " , ";
            retrievedList += vxLocList.get(i).getMyLatLng().longitude;
            retrievedList += "\n";
            //the layout on which you are working
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.partnerllid);

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
                    startActivity(new Intent(PartnerListView.this, locSettingActivity.class));
                }
            });

            //add button to the layout
            layout.addView(btnTag);


        }
        //locList.setText("");
    }
}
