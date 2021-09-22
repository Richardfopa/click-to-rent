package com.orange.click_2_rent;


import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;


public class ConnexionActivity extends AppCompatActivity {


    private static final int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        button = findViewById(R.id.btn_con_valider);
        button.setOnClickListener(View ->{
            startSignIn();
        });

    }
    private void startSignIn() {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Create and launch sign-in intent
    startActivityForResult(AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setTheme(R.style.ThemeClick2rent)
            .setAvailableProviders(providers)
            .setIsSmartLockEnabled(false, true)
            .build(), RC_SIGN_IN);
    }


    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();

        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            // Sign in failed
            if (response == null) {
                // User pressed back button
                showSnackbar(R.string.sign_in_cancelled);
                return;
            }

            if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                showSnackbar(R.string.no_internet_connection);
                return;
            }

            showSnackbar(R.string.unknown_error);
            Log.e(TAG, "Sign-in error: ", response.getError());
        }
    }

    private void showSnackbar(int sign_in_cancelled) {
    }
}