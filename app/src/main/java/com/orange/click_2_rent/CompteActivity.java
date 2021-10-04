package com.orange.click_2_rent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.common.net.InternetDomainName;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.orange.click_2_rent.Firebase.Storage;
import com.orange.click_2_rent.Models.Users;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.UUID;

public class CompteActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputLayout username;
    private TextInputLayout password;
    private TextInputLayout email;
    private ImageView mImgPhoto;
    private Button valider;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    public static final int REQUEST_SELECT_IMAGE_PROFILE = 10005;
    FirebaseStorage storage;
    UUID Uuid;
    private StorageReference mStorageRef;
    private StorageReference photoserviceRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte2);
        username = findViewById(R.id.edittextnom_con);
        password = findViewById(R.id.edittextpassword_con);
        email = findViewById(R.id.edittextusername_con);
        valider = findViewById(R.id.btn_con_valider);
        mImgPhoto = findViewById(R.id.image_add_create_account);

        // Configuration de firebasestorage

        storage = FirebaseStorage.getInstance();
        //  Create a storage reference from our app

        mStorageRef = storage.getReference("users");

        // Child references can also take paths
        // spaceRef now points to "images/space.jpg
        // imagesRef still points to "images"

        Uuid = UUID.randomUUID();
        photoserviceRef = mStorageRef.child("users/photo" + username + Uuid);

        mImgPhoto.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        valider.setOnClickListener(this);
    }

    private void register(String username, String password, String email) {
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                            FirebaseUser firebaseUser = auth.getCurrentUser();

                            Users users = new Users();
                            users.setAdresse("");
                            users.setTelphone("");
                            users.setEmail(email);
                            users.setMotDePasse(password);
                            users.setId(firebaseUser.getUid());
                            users.setNom(username);
                            users.setPhotoClient(null);
                            users.setServicesDemande(null);
                            users.setMesServices(null);
                            users.setId(firebaseUser.getUid());
                            users.setDate_darriver(Timestamp.now());

                            Storage.uploadImageViewToStorage(mImgPhoto,photoserviceRef,users);

                            String userid = firebaseUser.getUid();
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("name",username);
                            hashMap.put("email",email);
                            hashMap.put("password",password);

                            Intent intent = new Intent(getApplicationContext(), ConnexionActivity.class);
                            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
        UserRepository.addUser(password);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.image_add_create_account:
                ChooseDoc(REQUEST_SELECT_IMAGE_PROFILE);

                break;

            case R.id.btn_con_valider:

                String txt_user = username.getEditText().getText().toString();
                String txt_pass = password.getEditText().getText().toString();
                String txt_email = email.getEditText().getText().toString();
                if(TextUtils.isEmpty(txt_user) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_pass))
                    {
                        Toast.makeText(getApplicationContext(),"Veuillez remplir tout les champs",Toast.LENGTH_LONG).show();
                    }
                else if(txt_pass.length() < 6 )
                    {
                        Toast.makeText(getApplicationContext(), "Mot passe doit etre au moins 6 caracteres", Toast.LENGTH_SHORT).show();
                    }
                else
                    {
                        register(txt_user,txt_pass,txt_email);
                    }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_IMAGE_PROFILE && resultCode == RESULT_OK) {
            Picasso
                    .with(this)
                    .load(data.getData())
                    .fit()
                    .centerCrop()
                    .into(mImgPhoto);
            //setImage(mtxtphoto,mImgPhotoService,data);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void ChooseDoc(int requestSelectImageService) {

        Intent intent = new Intent();
        String[] type = {"image/png", "image/jpeg", "image/jpg", "image/gif"};
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.putExtra(Intent.EXTRA_MIME_TYPES, type);
        }
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Select your profile"), requestSelectImageService);

    }

}