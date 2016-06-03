package com.example.vnox.coupletunesvx;

import com.firebase.client.Firebase;

/**
 * The class that links the project with Firebase server
 * @author      Shuangli Zhou, Zuqi Chen
 */
public class FirebaseContext extends android.app.Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
