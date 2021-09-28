package com.orange.click_2_rent;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AddUserActivity extends AppCompatActivity {

    static final int REQUEST_SELECT_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Button btn_confirmer = findViewById(R.id.btn_con_valider);

        btn_confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),PremiereConnectionActivity.class);
                startActivity(intent);
            }
        });

        Button btn_parcourir = findViewById(R.id.btn_con_parcourir_user);

        btn_parcourir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                String[] type = {"image/png","image/jpeg","image/jpg","image/gif"};
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.putExtra(Intent.EXTRA_MIME_TYPES,type);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
//        intent.putExtra(ImageView);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_SELECT_IMAGE);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView apercu = findViewById(R.id.image_user_item);
        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                Picasso
                        .with(this)
                        .load(data.getData())
                        .fit()
                        .into(apercu);
            }

        }
    }
}