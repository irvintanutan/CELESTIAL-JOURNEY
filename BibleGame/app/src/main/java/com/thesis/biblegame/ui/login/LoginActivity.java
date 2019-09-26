package com.thesis.biblegame.ui.login;



import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.FirebaseApp;
import com.thesis.biblegame.R;

import java.util.ArrayList;

import co.chatsdk.core.session.ChatSDK;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class LoginActivity extends AppCompatActivity {

    Button signInButton;
    public static final int RC_SIGN_IN = 900;
    protected ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        FirebaseApp.initializeApp(this);
        signInButton = findViewById(R.id.button);

    }

    @Override
    protected void onResume() {
        super.onResume();
        authenticateWithCachedToken();
    }

    protected void authenticateWithCachedToken () {
        signInButton.setEnabled(false);

        ChatSDK.auth().authenticate()
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    signInButton.setEnabled(true);
                })
                .subscribe(() -> {
                    startActivity(new Intent(LoginActivity.this, Menu.class));
                    finish();
                }, throwable -> {
                    // Setup failed
                });
    }

    public void login_click (View v) {
        startAuthenticationActivity();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // RC_SIGN_IN is the request code you passed into  startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext() , "Registration Successful" , Toast.LENGTH_LONG);
                startActivity(new Intent(LoginActivity.this, Menu.class));
                finish();
            }
            else {
                // Handle Error
            }
        }
    }

    public void startAuthenticationActivity () {

        ArrayList<AuthUI.IdpConfig> idps = new ArrayList<>();

        idps.add(new AuthUI.IdpConfig.EmailBuilder().build());

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(idps)
                        .build(),
                RC_SIGN_IN);
    }



}
