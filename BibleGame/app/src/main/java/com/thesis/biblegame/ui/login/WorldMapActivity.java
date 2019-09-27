package com.thesis.biblegame.ui.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thesis.biblegame.R;

public class WorldMapActivity extends AppCompatActivity {

    AppCompatImageView map1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_map);


        map1 = findViewById(R.id.map1);
        map1.setImageBitmap(new Sprite(WorldMapActivity.this).sprites("places.png",
                2 , 6).get(0));

        map1.setOnClickListener(v -> {

            startActivity(new Intent(WorldMapActivity.this, MapActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        });

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
}
