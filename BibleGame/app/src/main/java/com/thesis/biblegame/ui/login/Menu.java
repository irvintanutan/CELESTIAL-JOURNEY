package com.thesis.biblegame.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thesis.biblegame.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.chatsdk.core.session.ChatSDK;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class Menu extends AppCompatActivity {
    AppCompatImageView playButton, howToPlay, settingsButton, quitButton, chatButton, leaderBoardButton;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ModGLobal.gameBackgroundMusic = R.raw.homepage_background_music;
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-4279809567962244~7900555063");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        FirebaseApp.initializeApp(this);
        // 0 - for private mode`
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();
        int total = 0;

        for (int a = 1; a <= 10; a++) {
            total += pref.getInt("epi" + a, 0);
        }

        FirebaseUser usersRef = FirebaseAuth.getInstance().getCurrentUser();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("score");

        DatabaseReference hopperRef = ref.child(usersRef.getUid());
        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put("name", usersRef.getDisplayName());
        hopperUpdates.put("score", total);
        hopperUpdates.put("picture", usersRef.getPhotoUrl().toString());
        hopperRef.updateChildren(hopperUpdates);


        Intent svc = new Intent(this, BackgroundSoundService.class);
        stopService(svc);
        startService(svc);


        init();
    }


    private void init() {
        List<Bitmap> menu = new Sprite(getApplicationContext()).sprites("MENU_BUTTONS.png", 2, 3);


        playButton = findViewById(R.id.playButton);
        howToPlay = findViewById(R.id.howToPlay);
        settingsButton = findViewById(R.id.settingsButton);
        quitButton = findViewById(R.id.quitButton);
        chatButton = findViewById(R.id.chatButton);
        leaderBoardButton = findViewById(R.id.leaderBoard);

        playButton.setImageBitmap(menu.get(0));
        howToPlay.setImageBitmap(menu.get(1));
        settingsButton.setImageBitmap(menu.get(2));
        chatButton.setImageBitmap(menu.get(3));
        leaderBoardButton.setImageBitmap(menu.get(4));
        quitButton.setImageBitmap(menu.get(5));

        playButton.setOnClickListener(view -> {

            startActivity(new Intent(Menu.this, StoryLineActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });


        howToPlay.setOnClickListener(view -> {
            startActivity(new Intent(Menu.this, HowToPlayActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        settingsButton.setOnClickListener(view -> {

            startActivity(new Intent(Menu.this, SettingsActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        });

        quitButton.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure you want to quit ?");

            builder.setPositiveButton("YES", (dialog, which) -> {
                // Do nothing but close the dialog
                // Do nothing
                dialog.dismiss();
                finish();
                System.exit(0);

            });
            builder.setNegativeButton("NO", (dialog, which) -> dialog.dismiss());

            AlertDialog alert = builder.create();
            alert.show();


        });

        leaderBoardButton.setOnClickListener(v -> {
            startActivity(new Intent(Menu.this, LeaderBoardActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        chatButton.setOnClickListener(v ->

                ChatSDK.auth().authenticate()
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(() -> {
                        })
                        .subscribe(() -> {
                            ChatSDK.ui().startMainActivity(Menu.this);
                        }, throwable -> {
                            // Setup failed
                        })
        );

    }

    @Override
    public void onBackPressed() {

    }

}
