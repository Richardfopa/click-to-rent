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
        setContentView(R.layout.activity_ajout_service1);

        String[] type = new String[] {"Bed-sitter", "Single", "1- Bedroom", "2- Bedroom","3- Bedroom"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.category_item,
                        type);

        AutoCompleteTextView editTextFilledExposedDropdown =
                findViewById(R.id.category);
        editTextFilledExposedDropdown.setAdapter(adapter);

        btnParcourirService=findViewById(R.id.btn_parcourir_service);

        btnParcourirService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                String[] type = {"image/png","image/jpeg","image/jpg","image/gif"};
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.putExtra(Intent.EXTRA_MIME_TYPES,type);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
//        intent.putExtra(ImageView);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_SELECT_IMAGE_SERVICE);
            }
        });

        btnParcourirRef = findViewById(R.id.btn_ref);
        btnParcourirRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                String[] type = {"image/png","image/jpeg","image/jpg","image/gif"};
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.putExtra(Intent.EXTRA_MIME_TYPES,type);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
//        intent.putExtra(ImageView);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_SELECT_IMAGE_REF);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView apercu = findViewById(R.id.image_user_item);
        ImageView apercu2 = findViewById(R.id.image_ref_item);
        if (requestCode == REQUEST_SELECT_IMAGE_SERVICE && resultCode == RESULT_OK) {
            if (data != null) {
                Picasso
                        .with(this)
                        .load(data.getData())
                        .fit()
                        .into(apercu);
            }

        }

        else if (requestCode == REQUEST_SELECT_IMAGE_REF && resultCode == RESULT_OK) {
            if (data != null) {
                Picasso
                        .with(this)
                        .load(data.getData())
                        .fit()
                        .into(apercu2);
            }
    }

    }
}
