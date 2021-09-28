package com.orange.click_2_rent;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ajout_service extends AppCompatActivity {
    static final int REQUEST_SELECT_IMAGE_SERVICE = 1;
    static final int REQUEST_SELECT_IMAGE_REF = 2;
    Button btnParcourirService;
    Button btnParcourirRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_service);
    }

}