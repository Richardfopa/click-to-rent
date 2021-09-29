package com.orange.click_2_rent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;

import com.google.firebase.Timestamp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.orange.click_2_rent.Models.FirebasesUtil;
import com.orange.click_2_rent.Models.Service;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class AjoutServiceActivity
        extends AppCompatActivity
        implements View.OnClickListener, View.OnFocusChangeListener {

    private FirebaseStorage storage;
    private TextInputLayout mTxtTitleService;
    private TextInputLayout mTxtCategorieService;
    private ImageView mImgPhotoService;
    private Button mbtChoosePict;
    private ImageView mImgPhotoDoc;
    private Button mbtChooseDoc;
    private TextView mtxtphoto;
    private TextView mtxtdoc;
    private Button mbtcancel;
    private Button mbtConfirm;
    private StorageMetadata metadata;
    private AutoCompleteTextView mautoComplete;
    private TextView error;
    private String title;
    private String categorie;
    private String mnomphoto;
    private Uri uriphotoservice;
    private String mnomdocument;
    private Uri uridocservice;
    private String description;
    private ProgressBar mProgressBar;
    private StorageReference mStorageRef;
    private StorageReference mStorageRefImage;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;
//    private FirebaseStorage storage;
    private TextInputLayout mTxtDescription;
    private static final int REQUEST_SELECT_IMAGE_SERVICE = 10004;
    private static final int REQUEST_SELECT_DOC_SERVICE = 10005;
    private static final String COLLECTION_SERVICE = "services";

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_service);
        
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

        mTxtTitleService.setOnFocusChangeListener(this);

        // Configuration de firebasestorage

        storage = FirebaseStorage.getInstance();
        //  Create a storage reference from our app
        mStorageRef = storage.getReference();

        //  Create a sotarage reference to images

        mStorageRefImage = mStorageRef.child("services");



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

        mImgPhotoDoc = findViewById(R.id.image_add_service_document_item);
        mImgPhotoDoc.setMaxHeight(R.dimen.size_img_upload);
        mImgPhotoDoc.setMaxWidth(R.dimen.size_img_upload);
        mtxtphoto = findViewById(R.id.textphoto_add_service);
        mtxtdoc = findViewById(R.id.textdoc_add_service);
        mbtcancel = findViewById(R.id.cancel_add_service);
        mbtConfirm = findViewById(R.id.confirm_add_service);
        mbtChoosePict = findViewById(R.id.btn_con_parcourir_photoservice);
        //mbtChooseDoc = findViewById(R.id.btn_con_parcourir_docu_user);
        error = findViewById(R.id.title_error_add_service);
        error.setVisibility(View.INVISIBLE);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        
        switch (view.getId()){
            case R.id.confirm_add_service: 
                // Clic pour confirmer les donnees saisir et envoyer
                title = mTxtTitleService.getEditText().getText().toString();
                categorie = mTxtCategorieService.getEditText().getText().toString();
                description = mTxtDescription.getEditText().getText().toString();

                if (title.isEmpty() || categorie.isEmpty() || description.isEmpty()){
                    error.setVisibility(View.VISIBLE);
                    error.setText("Verifier que vous avez rempli tous les champs !");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        error.setCompoundDrawables(null,null,getDrawable(R.drawable.ic_baseline_error_24),null);
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
                if (title.length() > 5 && !categorie.isEmpty() && description.length() >100){
                    if (error.getVisibility() == View.VISIBLE) {
                    } else {
                        error.setVisibility(View.VISIBLE);
                    }
                    error.setText("Donnees Valider ");
                    error.setTextColor(R.color.theme_color);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        error.setCompoundDrawablesRelative(null,null,getDrawable(R.drawable.ic_baseline_check_24),null);
                    }

                    Log.d("NOERROR","pas erreur");


                    // Creer une reference

                    // Create a storage reference from our app
                    StorageReference storageRef = storage.getReference("services");

                    // Child references can also take paths
                    // spaceRef now points to "images/space.jpg
                    // imagesRef still points to "images"
                    Service service = new Service();
                    UUID Uuid =  UUID.randomUUID();
                    StorageReference photoserviceRef = mStorageRef.child("services/photo"+title+Uuid);
                    StorageReference documentserviceRef = mStorageRef.child("services/doc"+title+Uuid);

                    service.setAddDate(new Timestamp(new Date()));
                    service.setId(Uuid.toString());
                    service.setCategorie(categorie);
                    service.setPhotoService(photoserviceRef.getPath());
                    service.setClients(null);
                    service.setUrlphotoService(documentserviceRef.getPath());
                    service.setCommentaire(null);
                    service.setDescription(description);
                    service.setId(null);
                    service.setNom_prestataire("Gambee");
                    service.setNote(null);
                    service.setTitle(title);
                    service.setPhotos(null);

                    // Get the data from an ImageView as bytes
                    uploadImageViewToStorage(mImgPhotoDoc,photoserviceRef);
                    uploadImageViewToStorage(mImgPhotoDoc,documentserviceRef);



                    Log.d("STORAGE","RECUPERATION DU SERVICE");
                    FirebasesUtil.addService(service);
                    Log.d("STORAGE","AJOUT EFFECTUER AVEC SUCCESS");
                    startActivity(new Intent(view.getContext(), MainActivity.class));

                }

                break;

            case R.id.image_add_service_photo_item:
                ChooseDoc(REQUEST_SELECT_IMAGE_SERVICE);
                break;

            case R.id.image_add_service_document_item:
                ChooseDoc(REQUEST_SELECT_DOC_SERVICE);
                break;

            case R.id.cancel_add_service:
                // Clic sur le bouton annuler

                startActivity(new Intent(this, MainActivity.class));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
        
            if (requestCode == REQUEST_SELECT_IMAGE_SERVICE && resultCode == RESULT_OK) {
                Picasso
                        .with(this)
                        .load(data.getData())
                        .resize(500,500)
                        .centerCrop()
                        .into(mImgPhotoService);
                //setImage(mtxtphoto,mImgPhotoService,data);
            }
            if (requestCode == REQUEST_SELECT_DOC_SERVICE && resultCode == RESULT_OK) {
                Picasso
                        .with(this)
                        .load(data.getData())
                        .resize(1000,1000)
                        .centerCrop()
                        .into(mImgPhotoDoc)
                        ;
                //setImage(mtxtdoc,mImgPhotoDoc,data);
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

    private void uploadFile() {

    }

    public void uploadImageViewToStorage(ImageView imageview, StorageReference photoRef){
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
    }

    public void uploadtoStorage(Uri filephotoservice){

        UploadTask uploadTask = mStorageRef.child("services/" + filephotoservice.getLastPathSegment()).putFile(filephotoservice, metadata);

        // Listen for state changes, errors, and completion of the upload.
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                Log.d("STORAGE", "Upload is " + progress + "% done");
            }
        }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d("STORAGE", "Upload is paused");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.w("STORAGE", "Upload is cancel",exception);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Handle successful uploads on complete
                // ...
                Log.d("STORAGE", "Upload is Success");
            }
        });

    }

    @Override
    public void onFocusChange(View view, boolean hasfocus) {
        switch(view.getId()){
            case R.id.txt_title_service:
                if(hasfocus){

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (mTxtTitleService.getEditText().toString().length() < 4){
                            mTxtTitleService.setBoxStrokeColor(getColor(R.color.rouge));
                        }else {
                            mTxtTitleService.setBoxStrokeColor(getColor(R.color.theme_color));
                            mTxtTitleService.setEndIconDrawable(getDrawable(R.drawable.ic_baseline_check_24));
                        }
                    }
                }
                break;
            }
    }
}