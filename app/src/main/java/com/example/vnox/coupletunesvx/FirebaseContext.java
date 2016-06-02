package com.example.vnox.coupletunesvx;

import com.firebase.client.Firebase;

/**
 * Created by Leon on 5/31/2016.
 */
public class FirebaseContext extends android.app.Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
