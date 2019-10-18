package com.thesis.biblegame.ui.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.preference.PreferenceManager;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.thesis.biblegame.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity implements RewardedVideoAdListener {
    SharedPreferences settingPref;
    ProgressBar mProgressBar;
    SharedPreferences.Editor editor;
    SharedPreferences pref;
    Typeface custom_font = null, score_font = null;
    String finalAnswer = "";
    MediaPlayer mp = null, mp2 = null;
    boolean retry = true;
    boolean hasIntern = false;
    Integer life = 3, score = 1;
    TextView txtScore, txtQuestion, txtAnswerA, txtAnswerB, txtAnswerC, txtAnswerD;
    ImageView life1, life2, life3, pause;
    LinearLayout mainContainer;

    CountDownTImer countDownTimer = null;
    Animation animShake = null;
    boolean playMusic = true;
    JSONArray jsonArray;

    private RewardedVideoAd mRewardedVideoAd;
    int length = 0;
    ArrayList<String> questions = new ArrayList<>();
    int progress = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ModGLobal.gameBackgroundMusic = R.raw.game_background_music;
        Intent svc=new Intent(this, BackgroundSoundService.class);
        stopService(svc);
        startService(svc);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-4279809567962244~7900555063");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mRewardedVideoAd = null;
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        custom_font = Typeface.createFromAsset(getAssets(), "fonts/Athletic.ttf");
        score_font = Typeface.createFromAsset(getAssets(), "fonts/bubbles.ttf");
        animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
        init();
        settingPref = PreferenceManager.getDefaultSharedPreferences(this);
        new setInternet().execute("");


    }


    private void init() {
        mainContainer = findViewById(R.id.main_container);
        //countdown = findViewById(R.id.countDown);
        mProgressBar = findViewById(R.id.progressbar);
        pause = findViewById(R.id.pause);
        Glide.with(getApplicationContext()).load(new Sprite(GameActivity.this).sprites("sprite_button.png",
                2, 5).get(2)).into(pause);

        txtScore = findViewById(R.id.score);
        life1 = findViewById(R.id.life1);
        life2 = findViewById(R.id.life2);
        life3 = findViewById(R.id.life3);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtAnswerA = findViewById(R.id.txtAnswerA);
        txtAnswerB = findViewById(R.id.txtAnswerB);
        txtAnswerC = findViewById(R.id.txtAnswerC);
        txtAnswerD = findViewById(R.id.txtAnswerD);


        //countdown.setTypeface(custom_font);
        txtScore.setTypeface(custom_font);
        mp = new MediaPlayer();

        try {
            JSONObject jsonObject = new JSONObject(EpisodeJsonString());
            jsonArray = jsonObject.getJSONArray("task");
            length = jsonArray.length();
            txtScore.setText("QUESTION : " + score + "/" + jsonArray.length());


            for (int a = 0; a < length; a++) {

                JSONObject object = jsonArray.getJSONObject(a);
                Log.e("INDEX " + a, object.toString());
                questions.add(object.toString());
            }
            Log.e("size shuffle", Integer.toString(questions.size()));
            Collections.shuffle(questions);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pause.setOnClickListener(v -> {

            countDownTimer.pause();

            LayoutInflater inflater = getLayoutInflater();
            View alertLayout = inflater.inflate(R.layout.pause, null);

            final ImageView resume = alertLayout.findViewById(R.id.resume);

            final ImageView restart = alertLayout.findViewById(R.id.restart);

            final ImageView home = alertLayout.findViewById(R.id.home);


            Glide.with(getApplicationContext()).asBitmap().load(new Sprite(GameActivity.this).sprites("sprite_button.png",
                    2, 5).get(8)).into(resume);

            Glide.with(getApplicationContext()).asBitmap().load(new Sprite(GameActivity.this).sprites("sprite_button.png",
                    2, 5).get(6)).into(restart);

            Glide.with(getApplicationContext()).asBitmap().load(new Sprite(GameActivity.this).sprites("sprite_button.png",
                    2, 5).get(5)).into(home);


            AlertDialog.Builder alert = new AlertDialog.Builder(GameActivity.this);
            // this is set the view from XML inside AlertDialog
            alert.setView(alertLayout);
            // disallow cancel of AlertDialog on click of back button and outside touch
            alert.setCancelable(false);
            final AlertDialog dialog = alert.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

            resume.setOnClickListener(v1 -> {

                dialog.dismiss();
                countDownTimer.resume();
            });

            restart.setOnClickListener(v1 -> {

                dialog.dismiss();
                life = 3;
                score = 1;
                createQuestion();

            });

            home.setOnClickListener(v1 -> {

                dialog.dismiss();
                startActivity(new Intent(GameActivity.this, Menu.class));
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            });

        });

        createQuestion();
    }


    private String EpisodeJsonString() {

        return CommonUtils.loadJSONFromAsset(GameActivity.this, ModGLobal.episode);

    }


    private void reduceLife() {
        try {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (playMusic && settingPref.getBoolean("vibrate" , true)) v.vibrate(500);
            life--;

            if (life == 2) {
                life3.setVisibility(View.INVISIBLE);
                createQuestion();
                if (playMusic) {
                    playAudio("wrong.wav");
                }
            } else if (life == 1) {
                life2.setVisibility(View.INVISIBLE);
                createQuestion();
                if (playMusic) {
                    playAudio("wrong.wav");
                }
            } else if (life == 0) {
                countDownTimer.cancel();
                if (retry) {
                    if (mRewardedVideoAd.isLoaded()) {
                        showVideoAds();
                    } else {
                        life1.setVisibility(View.INVISIBLE);
                        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                        dialog.setCancelable(false);
                        dialog.setTitle("Game Over")
                                .setMessage("You Failed to finish this episode better luck next time")
                                .setPositiveButton("Ok", (paramDialogInterface, paramInt) -> {
                                    startActivity(new Intent(GameActivity.this, MapActivity.class));
                                    finish();

                                    overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
                                });

                        dialog.show();

                    }
                } else {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setCancelable(false);
                    dialog.setTitle("Game Over")
                            .setMessage("You Failed to finish this episode better luck next time")
                            .setPositiveButton("Ok", (paramDialogInterface, paramInt) -> {
                                startActivity(new Intent(GameActivity.this, MapActivity.class));
                                finish();

                                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
                            });

                    dialog.show();
                }
            }
        } catch (Exception e) {
            Log.e("error", e.toString());
        }
    }


    private void addScore() {
        try {

            if (score == length) {
                countDownTimer.pause();
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.congratulation, null);

                final Button positive = alertLayout.findViewById(R.id.positive);
                final AppCompatImageView imageView = alertLayout.findViewById(R.id.congrats);
                imageView.setImageBitmap(new Sprite(GameActivity.this).getBitmapFromAssets("congratulation.png"));

                AlertDialog.Builder alert = new AlertDialog.Builder(GameActivity.this);
                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                alert.setCancelable(false);
                final AlertDialog dialog = alert.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                positive.setOnClickListener(v1 -> {
                    dialog.dismiss();
                    pref = getApplicationContext().getSharedPreferences(ModGLobal.worldPref, 0);
                    editor = pref.edit();

                    int lastScore = pref.getInt("score", 0);

                    if (lastScore < ModGLobal.level) {
                        editor.putInt("score", ModGLobal.level);
                        editor.apply();
                    }

                    int epiScore = pref.getInt(ModGLobal.episodePref, 0);
                    if (epiScore < life) {
                        editor.putInt(ModGLobal.episodePref, life);
                        editor.apply();
                    }

                    startActivity(new Intent(GameActivity.this, MapActivity.class));
                    finish();

                    overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
                });



            } else {

                score++;
                playAudio("correct.mp3");
                txtScore.setText("QUESTION : " + score + "/" + jsonArray.length());

                createQuestion();
            }

        } catch (Exception e) {
            Log.e("error", e.toString());
        }

    }


    private void playAudio(String filename) {
        if (settingPref.getBoolean("music" , true)) {
            if (mp.isPlaying()) {
                mp.stop();
            }
            try {
                mp.reset();
                AssetFileDescriptor afd;
                afd = getAssets().openFd(filename);
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp.prepare();
                mp.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createQuestion() {
        try {

            if (countDownTimer != null) {
                countDownTimer.cancel();
            }

            JSONObject obj = new JSONObject(questions.get(score - 1));

            finalAnswer = obj.getString("answer");
            txtQuestion.setText(obj.getString("question"));

            switch (obj.getString("type")) {
                /*case "epi_1":
                    cardQuestion.setCardBackgroundColor(Color.parseColor(math));
                    break;

                case "epi_2":
                    cardQuestion.setCardBackgroundColor(Color.parseColor(science));
                    break;

                case "epi_3":
                    cardQuestion.setCardBackgroundColor(Color.parseColor(history));
                    break;

                case "epi_4":
                    cardQuestion.setCardBackgroundColor(Color.parseColor(trivia));
                    break;*/
            }

            JSONArray choices = obj.getJSONArray("choices");
            for (int x = 0; x < choices.length(); x++) {
                JSONObject objChoices = choices.getJSONObject(x);
                switch (x) {
                    case 0:
                        txtAnswerA.setText(objChoices.getString("choice"));
                        txtAnswerA.setOnClickListener(view -> {
                            if (txtAnswerA.getText().toString().equals(finalAnswer)) {
                                addScore();
                            } else {
                                txtAnswerA.startAnimation(animShake);
                                reduceLife();
                            }

                        });
                        break;

                    case 1:
                        txtAnswerB.setText(objChoices.getString("choice"));
                        txtAnswerB.setOnClickListener(view -> {
                            if (txtAnswerB.getText().toString().equals(finalAnswer)) {
                                addScore();

                            } else {
                                txtAnswerB.startAnimation(animShake);
                                reduceLife();

                            }

                        });
                        break;

                    case 2:
                        txtAnswerC.setText(objChoices.getString("choice"));
                        txtAnswerC.setOnClickListener(view -> {
                            if (txtAnswerC.getText().toString().equals(finalAnswer)) {
                                addScore();

                            } else {
                                txtAnswerC.startAnimation(animShake);
                                reduceLife();

                            }

                        });
                        break;

                    case 3:
                        txtAnswerD.setText(objChoices.getString("choice"));
                        txtAnswerD.setOnClickListener(view -> {
                            if (txtAnswerD.getText().toString().equals(finalAnswer)) {
                                addScore();

                            } else {
                                txtAnswerD.startAnimation(animShake);
                                reduceLife();

                            }

                        });
                        break;

                }
            }

            progress = 0;
            int time = Integer.parseInt(obj.getString("time"));
            mProgressBar.setProgress(progress);
            countDownTimer = new CountDownTImer(time, 100) {

                public void onTick(long millisUntilFinished) {
                    //countdown.setText("TIME LEFT : " + String.format("%.2f", (float) millisUntilFinished / 1000));
                    progress++;
                    mProgressBar.setProgress(progress * 100 / (time / 100));
                }

                public void onFinish() {
                    reduceLife();
                }
            }.start();


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void showVideoAds() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setIcon(R.drawable.film_icon);
        dialog.setTitle("Confirm")
                .setMessage("One more chance ?")
                .setPositiveButton("Watch Video", (paramDialogInterface, paramInt) -> {
                    Log.e("I am here", "asdasdasd");
                    mRewardedVideoAd.show();
                })
                .setNegativeButton("Cancel", (paramDialogInterface, paramInt) -> {

                    paramDialogInterface.dismiss();
                    life1.setVisibility(View.INVISIBLE);
                    startActivity(new Intent(GameActivity.this, MapActivity.class));
                    finish();

                    overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
                });
        dialog.show();
    }


    private void loadRewardedVideoAd() {
        Log.e("video", "is loaded");
        AdRequest request = new AdRequest.Builder().build();
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", request);
    }


    @Override
    public void onRewardedVideoAdLoaded() {
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Intent svc=new Intent(this, BackgroundSoundService.class);
        stopService(svc);
    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        life = 1;
        retry = false;
        createQuestion();
        Intent svc=new Intent(this, BackgroundSoundService.class);
        startService(svc);
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    boolean hasActiveInternetConnection() {
        if (isNetworkAvailable()) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("https://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(10000);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                Log.e("asd", "Error checking internet connection", e);
            }
        } else {
            Log.d("asd", "No network available!");
        }
        return false;
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        life1.setVisibility(View.INVISIBLE);
        startActivity(new Intent(GameActivity.this, MapActivity.class));
        finish();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    @Override
    public void onRewardedVideoCompleted() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    class setInternet extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... params) {
            int counter = 1;
            while (counter <= 5) {
                hasIntern = hasActiveInternetConnection();
                //hasIntern = true;
                if (hasIntern) break;
                Log.e("connection", Boolean.toString(hasIntern));
                counter++;
            }

            return "";
        }

        @Override
        protected void onPostExecute(String strFromDoInBg) {


            if (hasIntern) {
                loadRewardedVideoAd();
            }
        }
    }

    @Override
    public void onResume() {
        try {
            playMusic = true;
        } catch (Exception e) {

        }
        super.onResume();
    }

    @Override
    public void onPause() {
        try {
            playMusic = false;
        } catch (Exception e) {

        }

        super.onPause();
    }

    @Override
    public void onDestroy() {
        try {
            countDownTimer.cancel();
            mp.release();
        } catch (Exception e) {

        }


        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

    }


}
