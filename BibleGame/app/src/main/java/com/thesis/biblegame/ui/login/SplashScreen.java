package com.thesis.biblegame.ui.login;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.thesis.biblegame.R;

public class SplashScreen extends AppCompatActivity {
    Typeface custom_font = null;
    TextView splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        custom_font = Typeface.createFromAsset(getAssets(), "fonts/bubbles.ttf");

        ModGLobal.storySprite = new Sprite(getApplicationContext())
                .sprites("sprite_storyline.jpg", 1, 5);

        ModGLobal.howToSprite = new Sprite(getApplicationContext())
                .sprites("howtoplay.jpg", 1, 5);

        ModGLobal.episodeSprites = new Sprite(getApplicationContext()).sprites("epi_sprite.png",
                10, 5);

        ModGLobal.worldMapSprite = new Sprite(getApplicationContext()).sprites("places.png",
                2, 6);

        ModGLobal.buttonSprites = new Sprite(getApplicationContext()).sprites("sprite_button.png",
                2, 5);

       /* splash = (TextView) findViewById(R.id.splash);
        splash.setTypeface(custom_font);*/

        new CountDownTImer(3000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }.start();


    }
}
