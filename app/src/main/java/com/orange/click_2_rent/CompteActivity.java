package com.orange.click_2_rent;

import static com.orange.click_2_rent.DemarrageApp.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.orange.click_2_rent.Firebase.Storage;
import com.orange.click_2_rent.Models.Users;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Pattern;


public class CompteActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputLayout username;
    private TextInputLayout password;
    private TextInputLayout email;
    private ImageView mImgPhoto;
    private static final String USERS_OBJECT = "users_object" ;
    public static final String REQUEST_SELECT = "select";
    private Button valider;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    public static final int REQUEST_SELECT_IMAGE_PROFILE = 10005;
    FirebaseStorage storage;
    UUID Uuid;
    private StorageReference mStorageRef;
    private StorageReference photoserviceRef;

    private static final Pattern MOT_DE_PASSE_VALIDATION = Pattern.compile(

            "^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-zA-Z])" +
                    "(?=.*[@#$%^&+=])" +
                    "(?=\\S+$)" +
                    ".{6,}" +
                    "$"
    );

    private static final Pattern EMAIL_VALIDATION = Pattern.compile(

            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA0-9][a-zA-Z0-9\\-] {0,24}" +
                    ")"
    );

    private static final Pattern NAME_VALIDATION = Pattern.compile(

            "[a-zA-Z0-9]"

            );
    private AuthCredential credential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte2);
        username = findViewById(R.id.edittextnom_con);
        password = findViewById(R.id.edittextpassword_con);
        email = findViewById(R.id.edittextusername_con);
        valider = findViewById(R.id.btn_con_valider);
        mImgPhoto = findViewById(R.id.image_add_create_account);

        validationName();
        validationMotDePasse();

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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

        credential = EmailAuthProvider.getCredential(email, password);

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //
                            Log.d(TAG, "onComplete: Creation de comte reussi Mr " + email );
                        }else{
                            Toast.makeText(CompteActivity.this, "Verifier votre connexion internet " , Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // Convertir le compte anonyme en compte email

        auth.getCurrentUser().linkWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                            Log.d(TAG, "linkWithCredential:success"+user);
                            Toast.makeText(CompteActivity.this,"Creation de compte reussi ",Toast.LENGTH_LONG).show();
                            createAccount(user,username,password,email);
                        } else {
                            Log.w(TAG, "linkWithCredential:failure", task.getException());
                            Toast.makeText(CompteActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            createAccount(null,username,password,email);
                        }
                    }
                });
    }

    private void createAccount(FirebaseUser user, String username, String password, String email) {

        Users users = new Users();

        if (user != null){
            //On cree son compte dans la collection user

            String userid = user.getUid();
            // Create new users with firebase
            users.setAdresse("");
            users.setTelphone("");
            users.setEmail(email);
            users.setMotDePasse(password);
            users.setId(userid);
            users.setNom(username);
            users.setPhotoClient(null);
            users.setServicesDemande(null);
            users.setMesServices(null);
            users.setId(user.getUid());
            users.setDate_darriver(Timestamp.now());
            //Upload users datatofirestore

            Log.d(TAG, "onComplete: "+users.toString());
            Storage.uploadImageUserToStorage(CompteActivity.this,mImgPhoto,photoserviceRef,users);
            //  Change activity to ConnectionActivity
            Intent intent = new Intent(getApplicationContext(), ConnexionActivity.class);
            intent.putExtra(USERS_OBJECT,users);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }
        else{
            Toast.makeText(this,"Verifier vos donnees saisis ",Toast.LENGTH_LONG).show();
        }

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

    public boolean validationMotDePasse() {

        String txt_pass = password.getEditText().getText().toString().trim();
        String noWhiteSpace = "(?=\\S+$ )";

        if (txt_pass.isEmpty()) {

            password.setError("Le champs Mot de passe ne peut pas etre vide.\nVeuillez le remplir SVP");
            return false;

        }
        else if(!MOT_DE_PASSE_VALIDATION.matcher(txt_pass).matches()) {

            password.setError("votre mot de passe ne correspond pas au format");
            return false;

        }else if(!txt_pass.matches(noWhiteSpace)){

            password.setError("Le mot de passe ne peu pas contenir les espaces");

            return false;

        }else

            password.setError(null);

            return true;

    }
    public boolean validationEmail() {

        String txt_email = email.getEditText().getText().toString().trim();


     return false;

    }
    public boolean validationName() {

        String nom = username.getEditText().getText().toString().trim();
        String exceptWhiteSpace = "(?=\\S+$ )";

        if(nom.isEmpty()){

            username.setError("Ce champ ne peu pas etre vide, SVP veuillez renseigner votre nom");
            return false;
        }
        else if(!NAME_VALIDATION.matcher(nom).matches()){

            username.setError("Votre mot de passe ne respecte pas le formalisme");
        }
        else if (!nom.matches(exceptWhiteSpace)) {

            username.setError("Votre nom ne doit pas contenir d'espaces");

            return false;

        }else

            username.setError(null);
            return true;
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
