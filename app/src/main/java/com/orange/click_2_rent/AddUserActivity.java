package com.orange.click_2_rent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orange.click_2_rent.Firebase.FireBaseUtils;
import com.orange.click_2_rent.Models.Client;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener {

    static final int REQUEST_SELECT_IMAGE = 1;
    TextInputLayout mUsername;
    TextInputLayout mEmail;
    TextInputLayout mProfilname;
    TextInputLayout mNumero;
    ImageView mProfilPhoto;
    String Usertype;
    Client client;
    RadioButton mrbclient;
    RadioButton mrbprestataire;
    Button mbtn_parcourir;
    Button mbtn_confirmer;
    TextView mphotodes;
    ImageView apercu;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


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


        mbtn_parcourir.setOnClickListener(this);
    }

    private void setVariablefromLayout() {

        mphotodes = findViewById(R.id.textphoto_adduser);
        mrbclient = findViewById(R.id.rb_client);
        mrbprestataire = findViewById(R.id.rb_prestation);
        mbtn_confirmer = findViewById(R.id.btn_con_valider);
        mbtn_parcourir = findViewById(R.id.btn_con_parcourir_photoservice);
        apercu = findViewById(R.id.image_user_item);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri selectedImageUri = data.getData();
                if (Build.VERSION.SDK_INT < 19) {
                    String selectedImagePath = getPath(selectedImageUri);
                    Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath);
                    mphotodes.setVisibility(View.INVISIBLE);
                    apercu.setImageBitmap(bitmap);
                }
                else{
                    ParcelFileDescriptor parcelFileDescriptor;
                    try {
                        parcelFileDescriptor = getContentResolver().openFileDescriptor(selectedImageUri, "r");
                        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);

                        parcelFileDescriptor.close();
                        mphotodes.setVisibility(View.INVISIBLE);
                        apercu.setImageBitmap(image);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
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
            if (email.isEmpty() || email.isEmpty() || !email.contains("@") || !email.contains(".")){
                mEmail.setError("Veuiller Entrer un mail valide");
            }
            if(mprofilname.isEmpty()){

            }
            if(numero.isEmpty()){

            }




            client = new Client();
            client = new Client(mUsername.getEditText().getText().toString(),
                    mEmail.getEditText().getText().toString(),
                    mProfilname.getEditText().getText().toString(),
                    mProfilname.getEditText().getText().toString(),
                    Usertype);
            Map<String, Object> user = new HashMap<>();
            user.put("nom", client.getNom());
            user.put("telephone", client.getTelphone());
            user.put("email", client.email);
            user.put("datearrive", client.getDatedarrive());
            user.put("motdepasse", client.getNom());
            user.put("nom", client.getNom());
            user.put("type", client.getTypeclient());
            user.put("service_souscri", client.getDemande());
            user.put("datedesorti", client.getDatedesorti());
            Log.w("REUSSI",client.toString());
//            FireBaseUtils.addUser(client, view.getContext());
            final int[] sentData = {0};

            FireBaseUtils.addUser(FireBaseUtils.CLIENT_COLLECTION,user,this);
//            if(sentData[0] == 1){
                Intent intent = new Intent(view.getContext(), MainActivity.class);
//                intent.putExtra(user);
                startActivity(intent);
//            }

        }
        //Choose profile picture
        if (view.getId() == R.id.btn_con_parcourir_photoservice){

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


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}