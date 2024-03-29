package com.example.vnox.coupletunesvx;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.location.Address;
import android.location.LocationListener;
import android.location.LocationManager;

import com.firebase.client.Firebase;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.data.*;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.example.vnox.coupletunesvx.VXLatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapViewActivity extends FragmentActivity implements OnConnectionFailedListener,
        OnMapReadyCallback,
        OnMyLocationButtonClickListener,
        GoogleMap.OnInfoWindowClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback {

    // Other fields
    private GoogleMap myMap;
    private UiSettings mUiSettings;
    private GoogleApiClient mGoogleApiClient;

    private String TAG = "placeActivity";
    private MapViewActivity thisActivity = this;
    private AlertDialog.Builder dialogBuilder;
    //private AlertDialog.Builder dialogBuilder2;

    private static LatLng latLng;
    private static String locationName;

    private static final int MY_PERMISSIONS_REUQEST_CURRENT_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .addApi(AppIndex.API).build();

        mGoogleApiClient.connect();
    }




    @Override
    public void onMapReady (GoogleMap googleMap){
        myMap = googleMap;
        mUiSettings = myMap.getUiSettings();

        // Keep the UI Settings state in sync with the checkboxes.
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);
        mUiSettings.setScrollGesturesEnabled(true);
        mUiSettings.setZoomGesturesEnabled(true);
        mUiSettings.setTiltGesturesEnabled(true);
        mUiSettings.setRotateGesturesEnabled(true);

        myMap.setOnMyLocationButtonClickListener(this);
        myMap.setOnInfoWindowClickListener(this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    100);
            Log.d("test1", "ins");
            return;
        }else if(myMap != null) {
            Log.d("test2", "outs");
            // Enable my location as shown in lab 4
            myMap.setMyLocationEnabled(true);

        }

        retrieveMarkers();

        //Track location
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // Changed location --> calculate distance
                //myMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("update path"));
                myMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
                myMap.clear();
                retrieveMarkers();

                // Add a new circle to show the range
                LatLng myLocationNow = new LatLng(location.getLatitude(), location.getLongitude());
                Circle circle = myMap.addCircle(new CircleOptions()
                        .center(myLocationNow)
                        .radius(1000)
                        .strokeColor(Color.RED)
                        .fillColor(Color.TRANSPARENT));

                //arriveFavoriteLocationDialog();

              // Check near by markers here
                for(int i = 0; i < DataHolder.vxLocList.size(); i++){
                    VXLatLng toCheckLocation = DataHolder.vxLocList.get(i).getMyLatLng();
                    //String theLocName = nameList.get(i);
                    Log.v("TAG2", "HERE !");

                    LatLng toCheckLocationConverted = new LatLng(toCheckLocation.getLatitude(), toCheckLocation.getLongitude());

                    if(checkLocationNear(myLocationNow, toCheckLocationConverted) &&  DataHolder.vxLocList.get(i).inRange == false){
                        DataHolder.vxLocList.get(i).inRange = true;
                        Log.v("LocCheck", "Arrived");
                        // Arrival Start
                        // Trigger THINGS HERE

                        Firebase myFirebaseRef = new Firebase("https://cse110-vxcoupletones.firebaseio.com/"+DataHolder.partnerName);
                        ArrayList<VXLocation> tlist = DataHolder.vxLocList;
                        tlist.get(i).setArrival(true);
                        myFirebaseRef.child("testloclist").setValue(tlist);
                        tlist.get(i).setArrival(false);
                        myFirebaseRef.child("testloclist").setValue(tlist);


                        return;
                    }else if(!checkLocationNear(myLocationNow, toCheckLocationConverted) &&  DataHolder.vxLocList.get(i).inRange == true){
                        DataHolder.vxLocList.get(i).inRange = false;
                        Log.v("LocCheck", "Departed");
                        // Departure found
                        //Trigger THINGS HERE

                        Firebase myFirebaseRef = new Firebase("https://cse110-vxcoupletones.firebaseio.com/"+DataHolder.partnerName);
                        ArrayList<VXLocation> tlist = DataHolder.vxLocList;
                        tlist.get(i).setDeparture(true);
                        myFirebaseRef.child("testloclist").setValue(tlist);
                        tlist.get(i).setDeparture(false);
                        myFirebaseRef.child("testloclist").setValue(tlist);

                    }
                }
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        LocationManager locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.GPS_PROVIDER;
        locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);



        myMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                // Creating a marker on the click location
                MarkerOptions markerOptions = new MarkerOptions();
                //Set customized marker image
                markerOptions.icon(BitmapDescriptorFactory.fromAsset("map-marker-icon.png"));
                myMap.clear();
                retrieveMarkers();

                // Go to the marker location
                myMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                String placeName;
                Geocoder location = new Geocoder(thisActivity, Locale.getDefault());
                String addressStr = "Tap to add to My Favorite Locations";

                markerOptions.title(addressStr);
                markerOptions.position(latLng);
                thisActivity.latLng = latLng;
                Marker thisPlace = myMap.addMarker(markerOptions);
                thisPlace.showInfoWindow();
            }

        });
    }

    public boolean checkLocationNear(LatLng myLocationNow, LatLng toCheckLocation){
        double checkLat = toCheckLocation.latitude;
        double checkLng = toCheckLocation.longitude;
        double myLat = myLocationNow.latitude;
        double myLng = myLocationNow.longitude;
        // Actually check if near
        double latDelta = myLat - checkLat;
        double lngDelta = myLng - checkLng;
        if(latDelta*latDelta + lngDelta*lngDelta <= 0.001){
            return true;
        }else{
            return false;
        }

    }






    private void addFavoriteLocationDialog(final LatLng latLng) {
        dialogBuilder = new AlertDialog.Builder(this);
        final EditText nameInput = new EditText(this);
        nameInput.setBackgroundColor(Color.parseColor("#FFFFFF"));

        dialogBuilder.setTitle("New Favorite Location");
        dialogBuilder.setView(nameInput);

        dialogBuilder.setPositiveButton("" +
                "Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                locationName = nameInput.getText().toString();
                VXLatLng vxLatLng = new VXLatLng(latLng.latitude, latLng.longitude);
                // Add locatiuon to local storage
                DataHolder.myLoc.add(vxLatLng);
                DataHolder.myName.add(locationName);
                saveLocations();
                Toast.makeText(getApplicationContext(), "Favorite Location Added", Toast.LENGTH_SHORT).show();
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Cancelled.", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialogLocationName = dialogBuilder.create();
        dialogLocationName.show();
    }



    public boolean retrieveMarkers(){

        if(DataHolder.vxLocList != null){
            DataHolder.myLoc = new ArrayList<>();
            DataHolder.myName = new ArrayList<>();
            for(int i = 0; i < DataHolder.vxLocList.size(); i++){
                VXLatLng tLL = DataHolder.vxLocList.get(i).getMyLatLng();
                String tName = DataHolder.vxLocList.get(i).getMyName();
                DataHolder.myLoc.add(tLL);
                DataHolder.myName.add(tName);
            }
        }else{
           DataHolder.myLoc = new ArrayList<>();
        }

        for(int i = 0; i < DataHolder.myLoc.size(); i++){
            VXLatLng newLL = DataHolder.myLoc.get(i);
            LatLng newLLConverted = new LatLng(newLL.getLatitude(), newLL.getLongitude());
            Marker melbourne = myMap.addMarker(new MarkerOptions()
                    .position(newLLConverted)
                    .title("Favorite Location")
                    .snippet(DataHolder.myName.get(i))
                    .icon(BitmapDescriptorFactory.fromAsset("map-marker-icon.png")));
        }
        return true;

    }

    public boolean saveLocations(){

//        DataHolder.myLoc = myLocationList;
//        DataHolder.myName = nameList;

        ArrayList<VXLocation> vxLocList = new ArrayList<VXLocation>();

        // create VXLocation Objects using data
        for(int i = 0; i <  DataHolder.myLoc.size(); i++){
            VXLocation vxLoc = new VXLocation( DataHolder.myLoc.get(i),  DataHolder.myName.get(i));
            vxLocList.add(vxLoc);
        }

        // save data back as xvLocList
        DataHolder.vxLocList = vxLocList;
        Firebase myFirebaseRef = new Firebase("https://cse110-vxcoupletones.firebaseio.com/" + DataHolder.partnerName );
        //VXLocation testFirebaseLoc = new VXLocation(new LatLng(23.0,24.0), "hahaloc");
        //testFirebaseLoc.setTone(2);
        myFirebaseRef.child("testloclist").setValue(vxLocList);

        if(DataHolder.myName != null && DataHolder.myLoc != null) {
            return true;
        }else{
            return false;
        }
    }




    @Override
    public boolean onMyLocationButtonClick() {
        //perform default behavior
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        addFavoriteLocationDialog(latLng);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MapView Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.vnox.coupletunesvx/http/host/path")
        );
        AppIndex.AppIndexApi.start(mGoogleApiClient, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MapView Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.vnox.coupletunesvx/http/host/path")
        );
        AppIndex.AppIndexApi.end(mGoogleApiClient, viewAction);
        mGoogleApiClient.disconnect();
    }
}
