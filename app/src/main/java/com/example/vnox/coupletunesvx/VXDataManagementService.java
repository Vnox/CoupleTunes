package com.example.vnox.coupletunesvx;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
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
            Firebase myFirebaseRef = new Firebase("https://cse110-vxcoupletones.firebaseio.com/user1");

            //VXLocation testFirebaseLoc = new VXLocation(new LatLng(23.0,24.0), "hahaloc");
            //testFirebaseLoc.setTone(2);

            myFirebaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    GenericTypeIndicator<ArrayList<VXLocation>> vxlocindicator = new GenericTypeIndicator<ArrayList<VXLocation>>() {};
                    ArrayList<VXLocation> vxLocData = dataSnapshot.child("testloclist").getValue(vxlocindicator);
                    //Toast.makeText(MainMenuActivity.this,"Data Changed, with first being: " + vxLocData.get(0).getMyName() + ", total length is: " + vxLocData.size(), Toast.LENGTH_SHORT).show();
                    DataHolder.vxLocTemp = vxLocData;
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
    }
}
