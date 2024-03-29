package com.thesis.biblegame.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.thesis.biblegame.R;

public class HowToPlayActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        viewPager = findViewById(R.id.viewPager);



        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, ModGLobal.howToSprite);

        viewPager.setAdapter(viewPagerAdapter);
    }


    @Override
    public void onBackPressed() {

        startActivity(new Intent(HowToPlayActivity.this, Menu.class));
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }



    public void back(View view) {
        if (getItem(-1) >= 0)
            viewPager.setCurrentItem(getItem(-1), true);

    }

    public void next(View view) {
        if (getItem(+1) < 5)
            viewPager.setCurrentItem(getItem(+1), true);

    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }
}
