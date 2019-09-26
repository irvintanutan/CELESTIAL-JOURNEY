package com.thesis.biblegame.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.thesis.biblegame.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class HowToPlayActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        viewPager = findViewById(R.id.viewPager);


        //For the number of rows and columns of the grid to be displayed
        int rows, cols;

        //For height and width of the small image chunks
        int chunkHeight, chunkWidth;

        //To store all the small image chunks in bitmap format in this list
        ArrayList<Bitmap> chunkedImages = new ArrayList<Bitmap>(5);
        Bitmap epiSprite = getBitmapFromAssets(this, "howtoplay.jpg");
        //Getting the scaled bitmap of the source image
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(epiSprite, epiSprite.getWidth(), epiSprite.getHeight(), true);

        rows = 1;
        cols = 5;
        chunkHeight = epiSprite.getHeight() / rows;
        chunkWidth = epiSprite.getWidth() / cols;

        //xCoord and yCoord are the pixel positions of the image chunks
        int yCoord = 0;
        for (int x = 0; x < rows; x++) {
            int xCoord = 0;
            for (int y = 0; y < cols; y++) {
                chunkedImages.add(Bitmap.createBitmap(scaledBitmap, xCoord, yCoord, chunkWidth, chunkHeight));
                xCoord += chunkWidth;
            }
            yCoord += chunkHeight;
        }


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, chunkedImages);

        viewPager.setAdapter(viewPagerAdapter);
    }


    @Override
    public void onBackPressed() {

        startActivity(new Intent(HowToPlayActivity.this, Menu.class));
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private Bitmap getBitmapFromAssets(HowToPlayActivity mainActivity,
                                       String filepath) {
        AssetManager assetManager = mainActivity.getAssets();
        InputStream istr = null;
        Bitmap bitmap = null;

        try {
            istr = assetManager.open(filepath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException ioe) {
            // manage exception
        } finally {
            if (istr != null) {
                try {
                    istr.close();
                } catch (IOException e) {
                }
            }
        }

        return bitmap;
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
