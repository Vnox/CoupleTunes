package com.example.vnox.coupletunesvx;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class pairActivity extends AppCompatActivity {

    private pairActivity thisActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair);

      // final Button testButton = (Button) findViewById(R.id.testSMS);
        final Button confirmButton = (Button) findViewById(R.id.confirmButton);
        final EditText myInput = (EditText) findViewById(R.id.myInput);
        final EditText partnerInput = (EditText) findViewById(R.id.partnerInput);
        final TextView partnerStatus = (TextView) findViewById(R.id.partnerStatus);
        final Button logInButton = (Button) findViewById(R.id.logInButton);

        logInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Login already
                DataHolder.myUserName = myInput.getText().toString();
                partnerStatus.setText("Welcome, " + DataHolder.myUserName );
                // Start Service with the name
                Intent intent = new Intent(pairActivity.this, VXDataManagementService.class);
                startService(intent);
            }
        });


        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                String partnerName = partnerInput.getText().toString();
                //DataHolder.partnerName = partnerName;
                partnerStatus.setText("Partner Added" );
                confirmButton.setText("Finished");
                String myFirebaseUrl = "https://cse110-vxcoupletones.firebaseio.com/";
                myFirebaseUrl += partnerName;
                final Firebase myFirebaseRef = new Firebase(myFirebaseUrl);
                myFirebaseRef.child("partner").setValue(DataHolder.myUserName);
            }
        });




    }
}
