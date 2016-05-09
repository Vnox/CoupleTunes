package com.example.vnox.coupletunesvx;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class pairActivity extends AppCompatActivity {

    private pairActivity thisActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair);

      // final Button testButton = (Button) findViewById(R.id.testSMS);
        final Button confirmButton = (Button) findViewById(R.id.confirmButton);
        final EditText phoneInput = (EditText) findViewById(R.id.phoneInput);
        final TextView partnerStatus = (TextView) findViewById(R.id.partnerStatus);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                String thePhoneNumber = phoneInput.getText().toString();
                DataHolder.myPhone = thePhoneNumber;
                partnerStatus.setText("New Partner Added \n Phone Number : " + DataHolder.myPhone );
                confirmButton.setText("Edit Phone");
            }
        });



//        testButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//                if (ActivityCompat.checkSelfPermission(thisActivity, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED ) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    ActivityCompat.requestPermissions(thisActivity,
//                            new String[]{Manifest.permission.SEND_SMS},
//                            100);
//                    Log.d("test1", "ins");
//                    return;
//                }else {
//                    Log.d("test2", "outs");
//                    // Enable my location as shown in lab 4
//                    SmsManager sms = SmsManager.getDefault();
//                    sms.sendTextMessage(DataHolder.myPhone, null, "Hey partner. I'm at XXX now", null, null);
//
//                }
//
//            }
//        });
    }
}
