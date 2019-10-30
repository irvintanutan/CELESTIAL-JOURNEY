package com.thesis.biblegame.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thesis.biblegame.R;

import java.util.List;

public class MapActivity extends AppCompatActivity {


    ImageView episode1, episode2, episode3, episode4, episode5;
    ImageView fab;
    boolean indicator = false;


    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        switch (ModGLobal.worldPref) {

            case "world1":
                setContentView(R.layout.activity_map);
                break;
            case "world2":
                setContentView(R.layout.activity_map2);
                pref = getApplicationContext().getSharedPreferences("world1", 0);
                if (pref.getInt("score", 1) < 5)
                    showAlert();
                break;
            case "world3":
                setContentView(R.layout.activity_map3);
                pref = getApplicationContext().getSharedPreferences("world2", 0);
                if (pref.getInt("score", 1) < 5)
                    showAlert();
                break;
            case "world4":
                setContentView(R.layout.activity_map4);
                pref = getApplicationContext().getSharedPreferences("world3", 0);
                if (pref.getInt("score", 1) < 5)
                    showAlert();
                break;
            case "world5":
                setContentView(R.layout.activity_map5);
                pref = getApplicationContext().getSharedPreferences("world4", 0);
                if (pref.getInt("score", 1) < 5)
                    showAlert();
                break;
            case "world6":
                setContentView(R.layout.activity_map6);
                pref = getApplicationContext().getSharedPreferences("world5", 0);
                if (pref.getInt("score", 1) < 5)
                    showAlert();
                break;
            case "world7":
                setContentView(R.layout.activity_map7);
                pref = getApplicationContext().getSharedPreferences("world6", 0);
                if (pref.getInt("score", 1) < 5)
                    showAlert();
                break;
            case "world8":
                setContentView(R.layout.activity_map8);
                pref = getApplicationContext().getSharedPreferences("world7", 0);
                if (pref.getInt("score", 1) < 5)
                    showAlert();
                break;
            case "world9":
                setContentView(R.layout.activity_map9);
                pref = getApplicationContext().getSharedPreferences("world8", 0);
                if (pref.getInt("score", 1) < 5)
                    showAlert();
                break;
            case "world10":
                setContentView(R.layout.activity_map10);
                pref = getApplicationContext().getSharedPreferences("world9", 0);
                if (pref.getInt("score", 1) < 5)
                    showAlert();
                break;
            case "world11":
                setContentView(R.layout.activity_map11);
                pref = getApplicationContext().getSharedPreferences("world10", 0);
                if (pref.getInt("score", 1) < 5)
                    showAlert();
                break;
            case "world12":
                setContentView(R.layout.activity_map12);
                pref = getApplicationContext().getSharedPreferences("world11", 0);
                if (pref.getInt("score", 1) < 5)
                    showAlert();
                else
                    indicator = true;
                break;

        }

        ModGLobal.gameBackgroundMusic = R.raw.game_background_music;
        Intent svc = new Intent(this, BackgroundSoundService.class);
        stopService(svc);
        startService(svc);
        pref = getApplicationContext().getSharedPreferences(ModGLobal.worldPref, 0);

        Log.e("SHARED PREF", Integer.toString(pref.getInt("score", 1)));

        episode1 = findViewById(R.id.button);
        episode2 = findViewById(R.id.button2);
        episode3 = findViewById(R.id.button3);
        episode4 = findViewById(R.id.button4);
        episode5 = findViewById(R.id.button5);
        fab = findViewById(R.id.floating_action_button);
        Glide.with(getApplicationContext()).asBitmap().load(ModGLobal.buttonSprites.get(5)).into(fab);


        mapStarInit();
        if (indicator) {
            episode1.setOnClickListener(view -> {
                Intent intent = new Intent(MapActivity.this, GameActivity.class);
                ModGLobal.episode = ModGLobal.worldPref + "/episode1.json";
                ModGLobal.episodePref = "epi1";
                ModGLobal.level = 2;
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            });

            episode2.setOnClickListener(view -> {
                if (pref.getInt("score", 0) > 1) {
                    Intent intent = new Intent(MapActivity.this, GameActivity.class);
                    ModGLobal.episode = ModGLobal.worldPref + "/episode2.json";
                    ModGLobal.episodePref = "epi2";
                    ModGLobal.level = 3;
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else {
                    showAlert("1");
                }
            });

            episode3.setOnClickListener(view -> {
                if (pref.getInt("score", 0) > 2) {
                    Intent intent = new Intent(MapActivity.this, GameActivity.class);
                    ModGLobal.episode = ModGLobal.worldPref + "/episode3.json";
                    ModGLobal.episodePref = "epi3";
                    ModGLobal.level = 4;
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else {
                    showAlert("2");
                }
            });

            episode4.setOnClickListener(view -> {
                if (pref.getInt("score", 0) > 3) {
                    Intent intent = new Intent(MapActivity.this, GameActivity.class);
                    ModGLobal.episode = ModGLobal.worldPref + "/episode4.json";
                    ModGLobal.episodePref = "epi4";
                    ModGLobal.level = 5;
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else {
                    showAlert("3");
                }
            });

            episode5.setOnClickListener(view -> {
                if (pref.getInt("score", 0) > 4) {
                    Intent intent = new Intent(MapActivity.this, GameActivity.class);
                    ModGLobal.episode = ModGLobal.worldPref + "/episode5.json";
                    ModGLobal.episodePref = "epi5";
                    ModGLobal.level = 6;
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else {
                    showAlert("4");
                }
            });
        } else {

            Intent intent = new Intent(MapActivity.this, GameActivity.class);
            ModGLobal.episode = ModGLobal.worldPref + "/episode1.json";
            ModGLobal.episodePref = "epi1";
            ModGLobal.level = 2;
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        fab.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure you want to go to main World Map ?");

            builder.setPositiveButton("YES", (dialog, which) -> {
                // Do nothing but close the dialog
                // Do nothing
                startActivity(new Intent(MapActivity.this, WorldMapActivity.class));
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            });
            builder.setNegativeButton("NO", (dialog, which) -> dialog.dismiss());

            AlertDialog alert = builder.create();
            alert.show();
        });

    }


    private void mapStarInit() {


        List<Bitmap> chunkedImages = ModGLobal.episodeSprites;

        episode1.setEnabled(false);
        episode2.setEnabled(false);
        episode3.setEnabled(false);
        episode4.setEnabled(false);
        episode5.setEnabled(false);

        int score = pref.getInt("score", 1);
        int epiScore = pref.getInt("epi1", 0);


        if (score > 0) {
            episode1.setEnabled(true);
            if (epiScore == 1)
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(2)).into(episode1);
            else if (epiScore == 2)
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(3)).into(episode1);
            else if (epiScore == 3)
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(4)).into(episode1);
            else {
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(1)).into(episode1);
            }
        }

        epiScore = pref.getInt("epi2", 0);
        Log.e("episcore", Integer.toString(epiScore));

        if (score > 1) {
            episode2.setEnabled(true);
            if (epiScore == 1)
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(7)).into(episode2);
            else if (epiScore == 2)
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(8)).into(episode2);
            else if (epiScore == 3)
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(9)).into(episode2);
            else
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(6)).into(episode2);
        }

        epiScore = pref.getInt("epi3", 0);
        if (score > 2) {
            episode3.setEnabled(true);
            if (epiScore == 1)
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(12)).into(episode3);
            else if (epiScore == 2)
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(13)).into(episode3);
            else if (epiScore == 3)
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(14)).into(episode3);
            else
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(11)).into(episode3);
        }

        epiScore = pref.getInt("epi4", 0);
        if (score > 3) {
            episode4.setEnabled(true);
            if (epiScore == 1)
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(17)).into(episode4);
            else if (epiScore == 2)
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(18)).into(episode4);
            else if (epiScore == 3)
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(19)).into(episode4);
            else
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(16)).into(episode4);
        }

        epiScore = pref.getInt("epi5", 0);
        if (score > 4) {
            episode5.setEnabled(true);
            if (epiScore == 1)
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(22)).into(episode5);
            else if (epiScore == 2)
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(23)).into(episode5);
            else if (epiScore == 3)
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(24)).into(episode5);
            else
                Glide.with(getApplicationContext()).asBitmap().load(chunkedImages.get(21)).into(episode5);
        }

    }


    void showAlert(String episode) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);

        builder.setTitle("Warning");
        builder.setMessage("You need to finish Episode " + episode + " before you can play this level");

        builder.setPositiveButton("OK", (dialog, which) -> {
            // Do nothing but close the dialog
            // Do nothing
            dialog.dismiss();

        });
        AlertDialog alert = builder.create();
        alert.show();

    }


    void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);

        builder.setTitle("WARNING");
        builder.setMessage("You are not allowed to play this level yet");

        builder.setPositiveButton("Ok", (dialog, which) -> {
            // Do nothing but close the dialog
            // Do nothing
            startActivity(new Intent(MapActivity.this, WorldMapActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        });

        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        alert.show();
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you want to go to main World Map ?");

        builder.setPositiveButton("YES", (dialog, which) -> {
            // Do nothing but close the dialog
            // Do nothing
            startActivity(new Intent(MapActivity.this, WorldMapActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        });
        builder.setNegativeButton("NO", (dialog, which) -> dialog.dismiss());

        AlertDialog alert = builder.create();
        alert.show();

    }


}
