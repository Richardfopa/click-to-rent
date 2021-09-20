package com.orange.click_2_rent;

import androidx.annotation.NonNull;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.orange.click_2_rent.Firebase.FireBaseUtils;
import com.orange.click_2_rent.Models.Client;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class AddUserActivity extends AppCompatActivity {

    static final int REQUEST_SELECT_IMAGE = 1;
    TextInputLayout mUsername;
    TextInputLayout mEmail;
    TextInputLayout mProfilname;
    TextInputLayout mNumero;
    ImageView mProfilPhoto;
    String Usertype;
    Client client;
    RadioButton mRadioButton;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Button btn_confirmer = findViewById(R.id.btn_con_valider);

        btn_confirmer.setOnClickListener(view -> {

            mUsername = findViewById(R.id.edittextusername_con);
            mEmail = findViewById(R.id.edittextemail_update);
            mProfilname = findViewById(R.id.editname_update);
            mNumero= findViewById(R.id.edit_numero_update);
//                onRadioButtonClicked(view);

            Map<String, Object> user = new HashMap<>();
            user.put("first", "Ada");
            user.put("last", "Lovelace");
            user.put("born", 1815);

            client = new Client(mUsername.getEditText().getText().toString(),
                    mEmail.getEditText().getText().toString(),
                    mProfilname.getEditText().getText().toString(),
                    mProfilname.getEditText().getText().toString(),
                    "client");
//                FireBaseUtils.addUser(client, view.getContext());
            Log.w("REUSSI",client.toString());
                FireBaseUtils.addUser(client, view.getContext());

//            db.collection("users")
//                    .add(user)
//                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference) {
//                            Log.d("CLIENT", "DocumentSnapshot added with ID: " + documentReference.getId());
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.w("CLIENT", "Error adding document", e);
//                        }
//                    });
            Intent intent = new Intent(view.getContext(), PremiereConnectionActivity.class);
            startActivity(intent);
        });

        Button btn_parcourir = findViewById(R.id.btn_con_parcourir_user);

        btn_parcourir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                String[] type = {"image/png", "image/jpeg", "image/jpg", "image/gif"};
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.putExtra(Intent.EXTRA_MIME_TYPES, type);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        intent.putExtra(ImageView);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_SELECT_IMAGE);
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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_client:
                if (checked)
                    Usertype = "Client";
                break;
            case R.id.rb_prestation:
                if (checked)
                    Usertype = "Prestataire";
                break;
        }

    }
}