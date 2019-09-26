package com.thesis.biblegame.ui.login;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.preference.PreferenceManager;

public class BackgroundSoundService extends Service {
    private static final String TAG = null;
    MediaPlayer player;
    SharedPreferences settingPref;

    public IBinder onBind(Intent arg0) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        settingPref = PreferenceManager.getDefaultSharedPreferences(this);
        player = MediaPlayer.create(this, ModGLobal.gameBackgroundMusic);
        player.setLooping(true); // Set looping
        player.setVolume(100, 100);

    }

    @SuppressLint("WrongConstant")
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (settingPref.getBoolean("music", true))
            player.start();
        return 1;
    }

    public void onStart(Intent intent, int startId) {
        // TO DO
    }

    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    public void onStop() {

    }


    public void onPause() {
        player.stop();
        player.release();
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }
}