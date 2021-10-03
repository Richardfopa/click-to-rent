package com.orange.click_2_rent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.orange.click_2_rent.Firebase.FireBaseUtils;
import com.orange.click_2_rent.Firebase.Storage;
import com.orange.click_2_rent.Models.Client;
import com.orange.click_2_rent.Models.Users;
import com.squareup.picasso.Picasso;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String USERS_CODE = "INSERTION_DUN_USERS";
    private FirebaseStorage storage;
    private StorageMetadata metadata;
    private StorageReference mStorageRef;
    private StorageReference mStorageRefImage;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;


    static final int REQUEST_SELECT_IMAGE = 1;
    TextInputLayout mUsername;
    TextInputLayout mEmail;
    TextInputLayout mProfilname;
    TextInputLayout mNumero;
    TextInputLayout mAdresse;
    ImageView mProfilPhoto;
    String Usertype;
    Client client;
    RadioButton mrbclient;
    RadioButton mrbprestataire;
    Users users;
    Button mbtn_parcourir;
    Button mbtn_confirmer;
    TextView mphotodes;
    ImageView apercu;
    CardView card;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        setVariablefromLayout();

        if (mrbclient.isChecked())
            Usertype = "client";

        else if (mrbprestataire.isChecked())
            Usertype = "prestataire";


        mbtn_confirmer.setOnClickListener(this);
        // Button for choose profile image

        apercu.setOnClickListener(this);

        //l mbtn_parcourir.setOnClickListener(this);
        card.setOnClickListener(this);

        // Configuration de firebasestorage

        storage = FirebaseStorage.getInstance();
        //  Create a storage reference from our app

        mStorageRef = storage.getReference();
    }

    private void setVariablefromLayout() {

        mphotodes = findViewById(R.id.textphoto_adduser);
        mrbclient = findViewById(R.id.rb_client);
        mrbprestataire = findViewById(R.id.rb_prestation);
        mbtn_confirmer = findViewById(R.id.btn_con_valider);
        mAdresse = findViewById(R.id.editname_update);
        apercu = findViewById(R.id.image_user_item);
        card = findViewById(R.id.card_ref);
        storage = FirebaseStorage.getInstance();

    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri selectedImageUri = data.getData();
                Picasso
                        .with(this)
                        .load(selectedImageUri)
                        .fit()
                        .centerCrop()
                        .into(apercu);
            }
        }
    }

    private String getPath(Uri uri) {
        if( uri == null ) {
            return null;
        }
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }


    // Gestion des clic sur l'interface

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {

//        Confirmer la saisi des donnees
        if (view.getId() == R.id.btn_con_valider){
            mUsername = findViewById(R.id.edittextusername_con);
            mEmail = findViewById(R.id.edittextemail_update);
            mProfilname = findViewById(R.id.editname_update);
            mNumero= findViewById(R.id.edit_numero_update);
//                onRadioButtonClicked(view);

            String username = mUsername.getEditText().getText().toString();
            String email = mEmail.getEditText().getText().toString();
            String mprofilname = mProfilname.getEditText().getText().toString();
            String numero = (mNumero.getEditText().getText().toString());
            if (username.isEmpty()){
                mUsername.setError("Veuiller Entrer un nom valide");
                mUsername.setErrorTextColor(ColorStateList.valueOf(R.color.rouge));
            }
            if (email.isEmpty() || !email.contains("@") || !email.contains(".")){
                mEmail.setError("Veuiller Entrer un mail valide");
            }
            if(mprofilname.isEmpty()){

            }
            if(numero.isEmpty()){

            }

            StorageReference storageRef = storage.getReference("users");

            // Child references can also take paths
            // spaceRef now points to "images/space.jpg
            // imagesRef still points to "images"

            UUID Uuid = UUID.randomUUID();
            StorageReference photoserviceRef = mStorageRef.child("users/photo" + username + Uuid);

            users = new Users();
            users.setAdresse("");
            users.setEmail(mEmail.getEditText().getText().toString());
            users.setId(Uuid.toString());
            users.setMotDePasse(null);
//            users.setPhotoProfil();
            users.setAdresse(mAdresse.getEditText().getText().toString());
            users.setNom(mProfilname.getEditText().getText().toString());
            users.setTelphone(mNumero.getEditText().getText().toString());

            Storage.uploadImageViewToStorage(apercu,photoserviceRef,users);
            //    FireBaseUtils.addUser(FireBaseUtils.CLIENT_COLLECTION,user,this);
            Intent intent = new Intent(view.getContext(), AjoutServiceActivity.class);
            intent.putExtra(USERS_CODE,users);
            startActivity(intent);
        }
        //Choose profile picture
        if (view.getId() == R.id.image_user_item){

            Intent intent = new Intent();
            String[] type = {"image/png", "image/jpeg", "image/jpg", "image/gif"};
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.putExtra(Intent.EXTRA_MIME_TYPES, type);
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        intent.putExtra(ImageView);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent,"Select your profile"), REQUEST_SELECT_IMAGE);

        }

        if (view.getId() == R.id.card_ref){
            Intent intent = new Intent();
            String[] type = {"image/png", "image/jpeg", "image/jpg", "image/gif"};
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.putExtra(Intent.EXTRA_MIME_TYPES, type);
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            //  intent.putExtra(ImageView);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent,"Select your profile"), REQUEST_SELECT_IMAGE);
        }


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}