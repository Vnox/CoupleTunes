package com.example.vnox.coupletunesvx;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class VXDataManagementService extends Service {
    public VXDataManagementService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Toast.makeText(VXDataManagementService.this, "VXService started.", Toast.LENGTH_SHORT).show();
        Thread thread = new Thread(new ListenerThread(startId));
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy(){
        Toast.makeText(VXDataManagementService.this, "VXService stopped.", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    final class ListenerThread implements Runnable{

        int startId;
        public ListenerThread(int startId){
            this.startId = startId;
        }

        @Override
        public void run(){
            // doing listener things
            // Actually contacting firebase here
            String myFirebaseUrl = "https://cse110-vxcoupletones.firebaseio.com/";
            myFirebaseUrl += DataHolder.myUserName;
            final Firebase myFirebaseRef = new Firebase(myFirebaseUrl);

            //VXLocation testFirebaseLoc = new VXLocation(new LatLng(23.0,24.0), "hahaloc");
            //testFirebaseLoc.setTone(2);

            myFirebaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //Check partner first
                    String newPartner = dataSnapshot.child("partner").getValue(String.class);
                    if(DataHolder.partnerName == "" && newPartner != null){
                        Toast.makeText(VXDataManagementService.this,"New partner added " + newPartner, Toast.LENGTH_SHORT).show();
                        DataHolder.partnerName = newPartner;
                    }


                    GenericTypeIndicator<ArrayList<VXLocation>> vxlocindicator = new GenericTypeIndicator<ArrayList<VXLocation>>() {};
                    ArrayList<VXLocation> vxLocData = dataSnapshot.child("testloclist").getValue(vxlocindicator);
                    //Toast.makeText(MainMenuActivity.this,"Data Changed, with first being: " + vxLocData.get(0).getMyName() + ", total length is: " + vxLocData.size(), Toast.LENGTH_SHORT).show();
                    DataHolder.vxLocTemp = vxLocData;

                    //check if entering or exiting
                    if(vxLocData != null){
                        for(int i = 0; i < vxLocData.size(); i++){
                            if(vxLocData.get(i).isArrival() == true){
                                vxLocData.get(i).vibrateAndRingAri(getApplicationContext());
                                Log.v("ServiceLog:", "Arrived");
                                NotificationCompat.Builder builder =
                                        new NotificationCompat.Builder(VXDataManagementService.this)
                                                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                                                .setContentTitle("Partner Arrived At somePlace!")
                                                .setContentText("Your partner arrived at : " + vxLocData.get(i).getMyName() );
                                int NOTIFICATION_ID = 12345;

                                Intent targetIntent = new Intent(VXDataManagementService.this, VXDataManagementService.class);
                                PendingIntent contentIntent = PendingIntent.getActivity(VXDataManagementService.this, 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                builder.setContentIntent(contentIntent);
                                NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                nManager.notify(NOTIFICATION_ID, builder.build());

                            }
                        }
                        for(int i = 0; i < vxLocData.size(); i++){
                            if(vxLocData.get(i).isDeparture() == true){
                                vxLocData.get(i).vibrateAndRingDep(getApplicationContext());
                                Log.v("ServiceLog:", "Departed");

                                NotificationCompat.Builder builder =
                                        new NotificationCompat.Builder(VXDataManagementService.this)
                                                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                                                .setContentTitle("Partner Departed somePlace!")
                                                .setContentText("Your partner departed : " + vxLocData.get(i).getMyName() );
                                int NOTIFICATION_ID = 12345;

                                Intent targetIntent = new Intent(VXDataManagementService.this, VXDataManagementService.class);
                                PendingIntent contentIntent = PendingIntent.getActivity(VXDataManagementService.this, 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                builder.setContentIntent(contentIntent);
                                NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                nManager.notify(NOTIFICATION_ID, builder.build());
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }


            });
        }
    }
}
