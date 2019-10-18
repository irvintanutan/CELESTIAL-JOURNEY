package com.thesis.biblegame.ui.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.thesis.biblegame.R;

public class WorldMapActivity extends AppCompatActivity {

    AppCompatImageView map1, map2, map3, map4, map5,
            map6, map7, map8, map9, map10, map11, map12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_map);


        map1 = findViewById(R.id.map1);
        map2 = findViewById(R.id.map2);
        map3 = findViewById(R.id.map3);
        map4 = findViewById(R.id.map4);
        map5 = findViewById(R.id.map5);
        map6 = findViewById(R.id.map6);
        map7 = findViewById(R.id.map7);
        map8 = findViewById(R.id.map8);
        map9 = findViewById(R.id.map9);
        map10 = findViewById(R.id.map10);
        map11 = findViewById(R.id.map11);
        map12 = findViewById(R.id.map12);

        Glide.with(getApplicationContext()).asBitmap().load( ModGLobal.worldMapSprite.get(0)).into(map1);
        Glide.with(getApplicationContext()).asBitmap().load(ModGLobal.worldMapSprite.get(1)).into(map2);
        Glide.with(getApplicationContext()).asBitmap().load(ModGLobal.worldMapSprite.get(2)).into(map3);
        Glide.with(getApplicationContext()).asBitmap().load(ModGLobal.worldMapSprite.get(3)).into(map4);
        Glide.with(getApplicationContext()).asBitmap().load(ModGLobal.worldMapSprite.get(4)).into(map5);
        Glide.with(getApplicationContext()).asBitmap().load(ModGLobal.worldMapSprite.get(5)).into(map6);
        Glide.with(getApplicationContext()).asBitmap().load(ModGLobal.worldMapSprite.get(6)).into(map7);
        Glide.with(getApplicationContext()).asBitmap().load(ModGLobal.worldMapSprite.get(7)).into(map8);
        Glide.with(getApplicationContext()).asBitmap().load(ModGLobal.worldMapSprite.get(8)).into(map9);
        Glide.with(getApplicationContext()).asBitmap().load(ModGLobal.worldMapSprite.get(9)).into(map10);
        Glide.with(getApplicationContext()).asBitmap().load(ModGLobal.worldMapSprite.get(10)).into(map11);
        Glide.with(getApplicationContext()).asBitmap().load(ModGLobal.worldMapSprite.get(11)).into(map12);

        map1.setDrawingCacheEnabled(true);
        map1.setOnTouchListener(changeColorListener);
        map2.setDrawingCacheEnabled(true);
        map2.setOnTouchListener(changeColorListener);
        map3.setDrawingCacheEnabled(true);
        map3.setOnTouchListener(changeColorListener);
        map4.setDrawingCacheEnabled(true);
        map4.setOnTouchListener(changeColorListener);
        map5.setDrawingCacheEnabled(true);
        map5.setOnTouchListener(changeColorListener);
        map6.setDrawingCacheEnabled(true);
        map6.setOnTouchListener(changeColorListener);
        map7.setDrawingCacheEnabled(true);
        map7.setOnTouchListener(changeColorListener);
        map8.setDrawingCacheEnabled(true);
        map8.setOnTouchListener(changeColorListener);
        map9.setDrawingCacheEnabled(true);
        map9.setOnTouchListener(changeColorListener);
        map10.setDrawingCacheEnabled(true);
        map10.setOnTouchListener(changeColorListener);
        map11.setDrawingCacheEnabled(true);
        map11.setOnTouchListener(changeColorListener);
        map12.setDrawingCacheEnabled(true);
        map12.setOnTouchListener(changeColorListener);


    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(WorldMapActivity.this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you want to go to main MENU ?");

        builder.setPositiveButton("YES", (dialog, which) -> {
            // Do nothing but close the dialog
            // Do nothing
            startActivity(new Intent(WorldMapActivity.this, Menu.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        });
        builder.setNegativeButton("NO", (dialog, which) -> dialog.dismiss());

        AlertDialog alert = builder.create();
        alert.show();

    }

    private final View.OnTouchListener changeColorListener = (v, event) -> {

        Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
        int color = 0;
        try {
            color = bmp.getPixel((int) event.getX(), (int) event.getY());
        } catch (Exception e) {
            // e.printStackTrace();
        }
        if (color == Color.TRANSPARENT)
            return false;
        else {

            switch (v.getId()) {

                case R.id.map1:
                    ModGLobal.worldPref = "world1";
                    break;
                case R.id.map2:
                    ModGLobal.worldPref = "world2";
                    break;
                case R.id.map3:
                    ModGLobal.worldPref = "world3";
                    break;
                case R.id.map4:
                    ModGLobal.worldPref = "world4";
                    break;
                case R.id.map5:
                    ModGLobal.worldPref = "world5";
                    break;
                case R.id.map6:
                    ModGLobal.worldPref = "world6";
                    break;
                case R.id.map7:
                    ModGLobal.worldPref = "world7";
                    break;
                case R.id.map8:
                    ModGLobal.worldPref = "world8";
                    break;
                case R.id.map9:
                    ModGLobal.worldPref = "world9";
                    break;
                case R.id.map10:
                    ModGLobal.worldPref = "world10";
                    break;
                case R.id.map11:
                    ModGLobal.worldPref = "world11";
                    break;
                case R.id.map12:
                    ModGLobal.worldPref = "world12";
                    break;
            }

            startActivity(new Intent(WorldMapActivity.this, MapActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            return true;

        }
    };
}
