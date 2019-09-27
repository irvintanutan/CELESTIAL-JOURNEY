package com.thesis.biblegame.ui.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thesis.biblegame.R;

import java.util.List;

public class MapActivity extends AppCompatActivity {


    ImageView episode1, episode2, episode3, episode4, episode5, episode6, episode7, episode8, episode9, episode10;
    FloatingActionButton fab;


    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ModGLobal.gameBackgroundMusic = R.raw.game_background_music;
        Intent svc = new Intent(this, BackgroundSoundService.class);
        stopService(svc);
        startService(svc);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);

        Log.e("SHARED PREF", Integer.toString(pref.getInt("score", 1)));

        episode1 = findViewById(R.id.button);
        episode2 = findViewById(R.id.button2);
        episode3 = findViewById(R.id.button3);
        episode4 = findViewById(R.id.button4);
        episode5 = findViewById(R.id.button5);
        episode6 = findViewById(R.id.button6);
        episode7 = findViewById(R.id.button7);
        episode8 = findViewById(R.id.button8);
        episode9 = findViewById(R.id.button9);
        episode10 = findViewById(R.id.button10);
        fab = findViewById(R.id.floating_action_button);


        mapStarInit();

        episode1.setOnClickListener(view -> {
            Intent intent = new Intent(MapActivity.this, GameActivity.class);
            ModGLobal.episode = "episode1.json";
            ModGLobal.episodePref = "epi1";
            ModGLobal.level = 2;
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        episode2.setOnClickListener(view -> {
            if (pref.getInt("score", 0) > 1) {
                Intent intent = new Intent(MapActivity.this, GameActivity.class);
                ModGLobal.episode = "episode2.json";
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
                ModGLobal.episode = "episode3.json";
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
                ModGLobal.episode = "episode4.json";
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
                ModGLobal.episode = "episode5.json";
                ModGLobal.episodePref = "epi5";
                ModGLobal.level = 6;
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            } else {
                showAlert("4");
            }
        });

        episode6.setOnClickListener(view -> {

            if (pref.getInt("score", 0) > 5) {
                Intent intent = new Intent(MapActivity.this, GameActivity.class);
                ModGLobal.episode = "episode6.json";
                ModGLobal.episodePref = "epi6";
                ModGLobal.level = 7;
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            } else {
                showAlert("5");
            }
        });

        episode7.setOnClickListener(view -> {

            if (pref.getInt("score", 0) > 6) {
                Intent intent = new Intent(MapActivity.this, GameActivity.class);
                ModGLobal.episode = "episode7.json";
                ModGLobal.episodePref = "epi7";
                ModGLobal.level = 8;
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            } else {
                showAlert("6");
            }
        });

        episode8.setOnClickListener(view -> {
            if (pref.getInt("score", 0) > 7) {
                Intent intent = new Intent(MapActivity.this, GameActivity.class);
                ModGLobal.episode = "episode8.json";
                ModGLobal.episodePref = "epi8";
                ModGLobal.level = 9;
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            } else {
                showAlert("7");
            }
        });

        episode9.setOnClickListener(view -> {
            if (pref.getInt("score", 0) > 8) {
                Intent intent = new Intent(MapActivity.this, GameActivity.class);
                ModGLobal.episode = "episode9.json";
                ModGLobal.episodePref = "epi9";
                ModGLobal.level = 10;
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            } else {
                showAlert("8");
            }
        });

        episode10.setOnClickListener(view -> {
            if (pref.getInt("score", 0) > 9) {
                Intent intent = new Intent(MapActivity.this, GameActivity.class);
                ModGLobal.episode = "episode10.json";
                ModGLobal.episodePref = "epi10";
                ModGLobal.level = 11;
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            } else {
                showAlert("9");
            }
        });

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



        List<Bitmap> chunkedImages = new Sprite(getApplicationContext()).sprites("epi_sprite.png",
                10 , 5);

        int score = pref.getInt("score", 1);
        int epiScore = pref.getInt("epi1", 0);


        if (score > 0) {
            if (epiScore == 1)
                episode1.setImageBitmap(chunkedImages.get(2));
            else if (epiScore == 2)
                episode1.setImageBitmap(chunkedImages.get(3));
            else if (epiScore == 3)
                episode1.setImageBitmap(chunkedImages.get(4));
            else
                episode1.setImageBitmap(chunkedImages.get(1));
        }

        epiScore = pref.getInt("epi2", 0);
        Log.e("episcore", Integer.toString(epiScore));

        if (score > 1) {
            if (epiScore == 1) episode2.setImageBitmap(chunkedImages.get(7));
            else if (epiScore == 2) episode2.setImageBitmap(chunkedImages.get(8));
            else if (epiScore == 3) episode2.setImageBitmap(chunkedImages.get(9));
            else episode2.setImageBitmap(chunkedImages.get(6));
        }

        epiScore = pref.getInt("epi3", 0);
        if (score > 2) {
            if (epiScore == 1) episode3.setImageBitmap(chunkedImages.get(12));
            else if (epiScore == 2) episode3.setImageBitmap(chunkedImages.get(13));
            else if (epiScore == 3) episode3.setImageBitmap(chunkedImages.get(14));
            else episode3.setImageBitmap(chunkedImages.get(11));
        }

        epiScore = pref.getInt("epi4", 0);
        if (score > 3) {
            if (epiScore == 1) episode4.setImageBitmap(chunkedImages.get(17));
            else if (epiScore == 2) episode4.setImageBitmap(chunkedImages.get(18));
            else if (epiScore == 3) episode4.setImageBitmap(chunkedImages.get(19));
            else episode4.setImageBitmap(chunkedImages.get(16));
        }

        epiScore = pref.getInt("epi5", 0);
        if (score > 4) {
            if (epiScore == 1) episode5.setImageBitmap(chunkedImages.get(22));
            else if (epiScore == 2) episode5.setImageBitmap(chunkedImages.get(23));
            else if (epiScore == 3) episode5.setImageBitmap(chunkedImages.get(24));
            else episode5.setImageBitmap(chunkedImages.get(21));
        }

        epiScore = pref.getInt("epi6", 0);
        if (score > 5) {
            if (epiScore == 1) episode6.setImageBitmap(chunkedImages.get(27));
            else if (epiScore == 2) episode6.setImageBitmap(chunkedImages.get(28));
            else if (epiScore == 3) episode6.setImageBitmap(chunkedImages.get(29));
            else episode6.setImageBitmap(chunkedImages.get(26));
        }

        epiScore = pref.getInt("epi7", 0);
        if (score > 6) {
            if (epiScore == 1) episode7.setImageBitmap(chunkedImages.get(32));
            else if (epiScore == 2) episode7.setImageBitmap(chunkedImages.get(33));
            else if (epiScore == 3) episode7.setImageBitmap(chunkedImages.get(34));
            else episode7.setImageBitmap(chunkedImages.get(31));
        }

        epiScore = pref.getInt("epi8", 0);
        if (score > 7) {
            if (epiScore == 1) episode8.setImageBitmap(chunkedImages.get(37));
            else if (epiScore == 2) episode8.setImageBitmap(chunkedImages.get(38));
            else if (epiScore == 3) episode8.setImageBitmap(chunkedImages.get(39));
            else episode8.setImageBitmap(chunkedImages.get(36));
        }

        epiScore = pref.getInt("epi9", 0);
        if (score > 8) {
            if (epiScore == 1) episode9.setImageBitmap(chunkedImages.get(42));
            else if (epiScore == 2) episode9.setImageBitmap(chunkedImages.get(43));
            else if (epiScore == 3) episode9.setImageBitmap(chunkedImages.get(44));
            else episode9.setImageBitmap(chunkedImages.get(41));
        }

        epiScore = pref.getInt("epi10", 0);
        if (score > 9) {
            if (epiScore == 1) episode10.setImageBitmap(chunkedImages.get(47));
            else if (epiScore == 2) episode10.setImageBitmap(chunkedImages.get(48));
            else if (epiScore == 3) episode10.setImageBitmap(chunkedImages.get(49));
            else episode10.setImageBitmap(chunkedImages.get(46));
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
