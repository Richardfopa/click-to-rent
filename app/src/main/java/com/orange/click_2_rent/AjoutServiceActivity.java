package com.orange.click_2_rent;

import static com.orange.click_2_rent.DemarrageApp.TAG;
import static com.orange.click_2_rent.Models.FirebasesUtil.COL_SERVICES;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.orange.click_2_rent.Firebase.Storage;
import com.orange.click_2_rent.Models.FirebasesUtil;
import com.orange.click_2_rent.Models.Photo;
import com.orange.click_2_rent.Models.Service;
import com.orange.click_2_rent.Models.Users;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AjoutServiceActivity
        extends AppCompatActivity
        implements View.OnClickListener {

    public static final String SENTFIREBASEAUTHDATA = "sentfirebaseauthdata";
    private FirebaseStorage storage;
    private StorageReference mStorageRef;
    private StorageReference mStorageRefImage;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;

    private TextInputLayout mTxtTitleService;
    private TextInputLayout mTxtCategorieService;
    private TextInputLayout mtelephone;
    private ImageView mImgPhotoService;
    private Button mbtChoosePict;
    private ImageView mImgPhotoDoc;
    private Button mbtChooseDoc;
    private TextView mtxtphoto;
    private TextView mtxtdoc;
    private Button mbtcancel;
    private Button mbtConfirm;

    private AutoCompleteTextView mautoComplete;
    private TextView error;
    private String title;
    private String categorie;
    private String telephon;
    private String mnomphoto;
    private Uri uriphotoservice;
    private String mnomdocument;
    private Uri uridocservice;
    private String description;
    private ProgressBar mProgressBar;
    Users users = new Users();
    Service service = new Service();
    private ArrayList<Users> listClient ;

    private FirebaseAuth firebaseauth;
    private FirebaseUser currentuser ;



    //    private FirebaseStorage storage;
    private final ArrayList<Photo> listphoto = new ArrayList<>();
    private TextInputLayout mTxtDescription;
    private static final int REQUEST_SELECT_IMAGE_SERVICE = 10004;
    private static final int REQUEST_SELECT_DOC_SERVICE = 10005;
    private static final String COLLECTION_SERVICE = "services";
    private TextInputLayout telephone;
    FirebaseFirestore db;

    private UUID Uuid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_service);

        firebaseauth = FirebaseAuth.getInstance();
        currentuser = firebaseauth.getCurrentUser();

        // Initialisation des variable de la vue

        setVariablefromLayout();
        // Gestion des click sur la vue
        //Bouton confirmer
        mbtConfirm.setOnClickListener(this);
        //Bouton Annuler
        mbtcancel.setOnClickListener(this);
        //Bouton Choisir photo service
        //mbtChoosePict.setOnClickListener(this);
        //Bouton choisir doc service
        //mbtChooseDoc.setOnClickListener(this);

        mImgPhotoService.setOnClickListener(this);

        mImgPhotoDoc.setOnClickListener(this);

        // Configuration de firebasestorage

        storage = FirebaseStorage.getInstance();
        //  Create a storage reference from our app
        mStorageRef = storage.getReference();

        //  Create a sotarage reference to images

        mStorageRefImage = mStorageRef.child("services");

    }

    private void updateUi(FirebaseUser currentuser) {

        if (currentuser != null){
            users.setId(currentuser.getUid());
            final DocumentReference docUser = FirebasesUtil.getUsers(users.getId());

            docUser.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {

                    if (snapshot != null && snapshot.exists()) {

                        Log.d(TAG, " data: " + snapshot.getData());
                        users.setTelphone(snapshot.getString("telphone"));
                        users.setEmail(snapshot.getString("email"));
                        users.setNom(snapshot.getString("nom"));
                        users.setDate_darriver(snapshot.getTimestamp("date_darriver"));
                        users.setAdresse(snapshot.getString("adresse"));
                        Log.d(TAG, "onEvent: "+snapshot.getData().toString());

                    } else {
                        Log.d(TAG, " data: null");
                    }
                }
            });
        }
    }

    private void setVariablefromLayout() {
        String[] type = new String[] {"Batiment", "Electricite", "Hotellerie", "Covoiturage","Immobiler"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.category_item,
                        type);

        mautoComplete =
                findViewById(R.id.category);
        mautoComplete.setAdapter(adapter);
        mTxtTitleService = findViewById(R.id.txt_title_service);
        mTxtCategorieService = findViewById(R.id.txt_category_service);
        mTxtDescription = findViewById(R.id.tv_description);
        mImgPhotoService = findViewById(R.id.image_add_service_photo_item);
        mImgPhotoService.setMaxWidth(R.dimen.size_img_upload);
        mImgPhotoService.setMaxHeight(R.dimen.size_img_upload);
        telephone = findViewById(R.id.edit_numero_service);

        mImgPhotoDoc = findViewById(R.id.image_add_service_document_item);
        mImgPhotoDoc.setMaxHeight(R.dimen.size_img_upload);
        mImgPhotoDoc.setMaxWidth(R.dimen.size_img_upload);
        mtxtphoto = findViewById(R.id.textphoto_add_service);
        mtxtdoc = findViewById(R.id.textdoc_add_service);
        mbtcancel = findViewById(R.id.cancel_add_service);
        mbtConfirm = findViewById(R.id.confirm_add_service);
        error = findViewById(R.id.title_error_add_service);
        uridocservice = null;
        uriphotoservice = null;
    }

    @SuppressLint({"ResourceAsColor", "NonConstantResourceId"})
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.confirm_add_service:

                Users user = new Users();

                // Clic pour confirmer les donnees saisir et envoyer
                title = mTxtTitleService.getEditText().getText().toString();
                categorie = mTxtCategorieService.getEditText().getText().toString();
                description = mTxtDescription.getEditText().getText().toString();
                telephon = telephone.getEditText().getText().toString();

                if (title.isEmpty() || categorie.isEmpty() || description.isEmpty()){
                    error.setText("Verifier que vous avez rempli tous les champs !");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        error.setCompoundDrawables(null,null,getResources().getDrawable(R.drawable.ic_baseline_error_24),null);
                    }
                }
                if (description.length() < 50){
                    mTxtDescription.setError("Veuillez bien remplir la description !");
                }
                if (title.isEmpty()){
                    mTxtTitleService.setError("Veuiller remplir un titre ");
                    mTxtTitleService.setErrorIconDrawable(R.drawable.ic_baseline_error_24);
                }
                if (categorie.isEmpty()){
                    mautoComplete.setError("Veuiller choisir une categorie !!");
                }
                if (title.length() > 5 && !categorie.isEmpty() && description.length() >100) {
                    error.setText("Donnees Valider ");
                    error.setTextColor(getResources().getColor(R.color.theme_color));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        error.setCompoundDrawablesRelative(null, null, getResources().getDrawable(R.drawable.ic_baseline_check_24), null);
                    }
                    Log.d("NOERROR", "pas erreur");

                    // Creer une reference

                    // Create a storage reference from our app
                    StorageReference storageRef = storage.getReference("services");

                    // Child references can also take paths
                    // spaceRef now points to "images/space.jpg
                    // imagesRef still points to "images"

                    Uuid = UUID.randomUUID();
                    StorageReference photoserviceRef = mStorageRef.child("services/photo" + title + Uuid);
                    StorageReference documentserviceRef = mStorageRef.child("services/doc" + title + Uuid);

                    service.setAddDate(Timestamp.now());
                    service.setId(Uuid.toString());
                    service.setTelephone(telephon);
                    service.setCategorie(categorie);
                    //listClient.add(users);
                    //service.setClients(listClient);
                    service.setCommentaire(null);
                    service.setDescription(description);
                    service.setStatus(false);
                    service.setName_provider(currentuser.getUid());
                    service.setNote(null);
                    service.setTitle(title);

                    //    FirebasesUtil.addService(service);
                    db =  FirebaseFirestore.getInstance();
                    //db.collection("services").document(Uuid.toString()).set(service);

                    // Get the data from an ImageView as bytes
                    //uploadImageViewToStorage(mImgPhotoDoc, photoserviceRef);
                    //uploadDocToStorage(mImgPhotoService,documentserviceRef);

                    Storage.uploadImageServiceToStorage(this,mImgPhotoService,photoserviceRef,service);

                    //On creer une reference vers user-service

                    Map<String , Object> user_service =new HashMap<>();
                    user_service.put("user_id", firebaseauth.getCurrentUser().getUid());
                    user_service.put("service_id", service.getId());

                    db.collection("users").document(firebaseauth.getCurrentUser().getUid())
                            .update("mesServices", FieldValue.arrayUnion(service.getId()));

                    db.collection("user_service").add(user_service);



                    Toast.makeText(this, "Insertion reussi avec success ", Toast.LENGTH_SHORT).show();
//                    Intent prestation = new Intent(this, PrestationsActivity.class);
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }

                Toast.makeText(this, "User id" + currentuser.getUid(), Toast.LENGTH_LONG).show();
                Log.d(TAG, "updateUI: " + currentuser.getUid());
                user.setId(currentuser.getUid());
                Log.d(TAG, "updateUI: " + user.getId());

                //FirebasesUtil.setUsersidService(currentuser.getUid());


                break;

            case R.id.image_add_service_photo_item:
                ChooseDoc(REQUEST_SELECT_IMAGE_SERVICE);
                break;

            case R.id.image_add_service_document_item:
                ChooseDoc(REQUEST_SELECT_DOC_SERVICE);
                break;

            case R.id.cancel_add_service:
                // Clic sur le bouton annuler
                Intent cancel = new Intent(this, MainActivity.class);
                cancel.putExtra(SENTFIREBASEAUTHDATA,currentuser);
                startActivity(cancel);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    private void addservice() {

    }

    private void ChooseDoc(int requestSelectImageService) {

        Intent intent = new Intent();
        String[] type = {"image/png", "image/jpeg", "image/jpg", "image/gif"};
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.putExtra(Intent.EXTRA_MIME_TYPES, type);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        }
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Select your profile"), requestSelectImageService);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SELECT_IMAGE_SERVICE && resultCode == RESULT_OK) {
            Picasso
                    .with(this)
                    .load(data.getData())
                    .fit()
                    .centerCrop()
                    .into(mImgPhotoService);
            //setImage(mtxtphoto,mImgPhotoService,data);
        }
        if (requestCode == REQUEST_SELECT_DOC_SERVICE && resultCode == RESULT_OK) {
            Picasso
                    .with(this)
                    .load(data.getData())
                    .fit()
                    .centerCrop()
                    .into(mImgPhotoDoc)
            ;
            //setImage(mtxtdoc,mImgPhotoDoc,data);
        }

    }

    public void uploadImageViewToStorage(@NonNull ImageView imageview, @NonNull StorageReference photoRef){
        imageview.setDrawingCacheEnabled(true);
        imageview.buildDrawingCache();

        Bitmap bitmap = ((BitmapDrawable) imageview.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = photoRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.d("STORAGE","FAILLURE INSERTION");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Log.d("STORAGE","INSERTION REUSSI");
            }
        });

        // image imagedownload uri from firebase

        Task<Uri> urlTask = uploadTask.continueWithTask(
                new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return photoRef.getDownloadUrl();
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {

                            Uri photoservice = task.getResult();

                            Log.d("FILEURL", "onComplete: "+ photoservice.toString());
                            listphoto.add(new Photo(Uuid.toString(),photoservice.toString(),photoservice.toString()));
                            service.setPhotoService(photoservice.toString());
                            service.setPhoto_service(photoservice.toString());
                            service.setPhotos(listphoto);

                            Log.d("STORAGE","RECUPERATION DU SERVICE");
                            FirebasesUtil.addService(service);
                            Log.d("STORAGE","AJOUT EFFECTUER AVEC SUCCESS");

                            Log.d("STORAGE","RECUPERATION DU SERVICE");


                            service.setId(currentuser.getUid());
                            //users.setId(fuser.getUid());

                            //users.addService(service);

                           // FirebasesUtil.getReferenceFirestore(COL_SERVICES).document(Uuid.toString()).update("photos", FieldValue.arrayUnion(photoservice.toString()));

                            db.collection("services")
                                    .document(Uuid.toString())
                                    .update("photos_service",photoservice.toString());

                            Toast.makeText(AjoutServiceActivity.this, "addService succees ", Toast.LENGTH_SHORT).show();
                            FirebasesUtil.setUsersidService(currentuser.getUid());
                            Toast.makeText(AjoutServiceActivity.this, "setUsersidservicesuccees ", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "Modification des url photoeffectuer avec success", Toast.LENGTH_SHORT).show();

                            Log.d("STORAGE","AJOUT EFFECTUER AVEC SUCCESS");
                            //startActivity(new Intent(view.getContext(), MainActivity.class));

                        } else {
                            // Handle failures
                            // ...
                        }
                    }
                });

    }
    public void uploadDocToStorage(@NonNull ImageView imageview, @NonNull StorageReference photoRef){
        imageview.setDrawingCacheEnabled(true);
        imageview.buildDrawingCache();

        Bitmap bitmap = ((BitmapDrawable) imageview.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = photoRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.d("STORAGE","FAILLURE INSERTION");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Log.d("STORAGE","INSERTION REUSSI");
            }
        });

        // image imagedownload uri from firebase

        Task<Uri> urlTask = uploadTask.continueWithTask(
                new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return photoRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    uridocservice = task.getResult();
                    Log.d("FILEURL", "onComplete: "+ uridocservice.toString());
                    listphoto.add(new Photo(Uuid.toString(), uridocservice.toString(),uridocservice.getPath()));
                    service.setPhotos(listphoto);

                    //db.collection("services").document(Uuid.toString()).update("doc_service", uridocservice.toString());
                    listphoto.add(new Photo(Uuid.toString(), uridocservice.toString(),uridocservice.getPath()));
                    service.addPhotos(new Photo(Uuid.toString(), uridocservice.toString(),uridocservice.getPath()));
                    FirebasesUtil.setService(service);
                    Toast.makeText(getApplicationContext(), "Modification des url photoeffectuer avec success", Toast.LENGTH_SHORT).show();

                } else {
                    // Handle failures
                    // ...
                }
            }
        });

    }

}