package com.example.vnox.coupletunesvx;

import android.content.Context;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.provider.MediaStore;

import com.google.android.gms.common.data.*;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Leon on 5/30/2016.
 */

/*Class VXLocation
* The location class specifically
* designed for CSE110 Milestone 2
* created by Vnox - Shuangli Zhou*/
public class VXLocation{

    // some fields
    private int vibeControl;
    private int toneControl;
    private LatLng myLatLng;
    private int soundSetting = 3;
    private String myName;
    // Context myContext;

    public VXLocation() {
        this.toneControl = 1;
        this.vibeControl = 1;
    }

    public VXLocation(LatLng inLL, String inName) {
        this.toneControl = 1;
        this.vibeControl = 1;
        this.myName = inName;
        this.myLatLng = inLL;
    }

    public LatLng getLL(){
        return this.myLatLng;
    }

    public String getMyName(){
        return this.myName;
    }

    public int getRingTone(){
        return this.toneControl;
    }

    public int getVibePattern(){
        return this.vibeControl;
    }

    public void setVibe(int setVal) {
        this.vibeControl = setVal;
    }

    public void setTone(int setVal) {
        this.toneControl = setVal;
    }

    public void setName(String setVal){
        this.myName = setVal;
    }

    public void syncSoundSettings() {
        this.soundSetting = com.example.vnox.coupletunesvx.DataHolder.theSoundSetting;
    }

    // Do the thing
    public void vibrateAndRingDep(Context theCt) {
        // Give context at run time
        if (this.soundSetting == 1 || this.soundSetting == 3) {
            MediaPlayer vnoxPlayer = new MediaPlayer();
            MediaPlayer departurePlayer = new MediaPlayer();
            departurePlayer = MediaPlayer.create(theCt, R.raw.departure);
            if (this.toneControl == 1) {
                vnoxPlayer = MediaPlayer.create(theCt, R.raw.ding);
            } else if (this.toneControl == 2) {
                vnoxPlayer = MediaPlayer.create(theCt, R.raw.dong);
            } else if (this.toneControl == 3) {
                vnoxPlayer = MediaPlayer.create(theCt, R.raw.biubiu);
            }
            //   long deltaDuration = departurePlayer.getDuration();
            // play two tones
            departurePlayer.start();
            departurePlayer.setNextMediaPlayer(vnoxPlayer);
        }
        if (this.soundSetting == 2 || this.soundSetting == 3) {
            Vibrator v = (Vibrator) theCt.getSystemService(Context.VIBRATOR_SERVICE);
            // vibrate different patterns
            long[] pattern1 = new long[]{30, 30, 30, 30};
            long[] pattern2 = new long[]{60, 60};
            long[] pattern3 = new long[]{40, 40, 40};

            if (this.vibeControl == 1) {
                v.vibrate(pattern1, 0);
            } else if (this.vibeControl == 2) {
                v.vibrate(pattern2, 0);
            } else if (this.vibeControl == 3) {
                v.vibrate(pattern3, 0);
            }


        }


    }

    public void vibrateAndRingAri(Context theCt) {
        // Give context at run time

        if (this.soundSetting == 1 || this.soundSetting == 3) {
            MediaPlayer vnoxPlayer = new MediaPlayer();
            MediaPlayer arrivalPlayer = new MediaPlayer();
            arrivalPlayer = MediaPlayer.create(theCt, R.raw.arrival);
            if (this.toneControl == 1) {
                vnoxPlayer = MediaPlayer.create(theCt, R.raw.ding);
            } else if (this.toneControl == 2) {
                vnoxPlayer = MediaPlayer.create(theCt, R.raw.dong);
            } else if (this.toneControl == 3) {
                vnoxPlayer = MediaPlayer.create(theCt, R.raw.biubiu);
            }
            //   long deltaDuration = departurePlayer.getDuration();
            // play two tones
            arrivalPlayer.start();
            arrivalPlayer.setNextMediaPlayer(vnoxPlayer);
        }
        if (this.soundSetting == 2 || this.soundSetting == 3) {
            Vibrator v = (Vibrator) theCt.getSystemService(Context.VIBRATOR_SERVICE);
            // vibrate different patterns
            long[] pattern1 = new long[]{30, 30, 30, 30};
            long[] pattern2 = new long[]{60, 60};
            long[] pattern3 = new long[]{40, 40, 40};

            if (this.vibeControl == 1) {
                v.vibrate(pattern1, 0);
            } else if (this.vibeControl == 2) {
                v.vibrate(pattern2, 0);
            } else if (this.vibeControl == 3) {
                v.vibrate(pattern3, 0);
            }


        }

    }

    public void vibrateAndRingTest(Context theCt){

        MediaPlayer vnoxPlayer = new MediaPlayer();

        if (this.toneControl == 1) {
            vnoxPlayer = MediaPlayer.create(theCt, R.raw.ding);
        } else if (this.toneControl == 2) {
            vnoxPlayer = MediaPlayer.create(theCt, R.raw.dong);
        } else if (this.toneControl == 3) {
            vnoxPlayer = MediaPlayer.create(theCt, R.raw.biubiu);
        }
        //   long deltaDuration = departurePlayer.getDuration();
        // play two tones
        vnoxPlayer.start();


        Vibrator v = (Vibrator) theCt.getSystemService(Context.VIBRATOR_SERVICE);
        // vibrate different patterns
        long[] pattern1 = new long[]{30, 30, 30, 30};
        long[] pattern2 = new long[]{60, 60};
        long[] pattern3 = new long[]{40, 40, 40};

        if (this.vibeControl == 1) {
            v.vibrate(pattern1, 0);
        } else if (this.vibeControl == 2) {
            v.vibrate(pattern2, 0);
        } else if (this.vibeControl == 3) {
            v.vibrate(pattern3, 0);
        }

    }
}
