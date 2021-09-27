package com.orange.click_2_rent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.Timestamp;
import com.google.firebase.ktx.Firebase;
import com.orange.click_2_rent.Firebase.FireBaseUtils;
import com.orange.click_2_rent.Models.Service;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AjoutServiceActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    TextInputLayout mTxtTitleService;
    TextInputLayout mTxtCategorieService;
    ImageView mImgPhotoService;
    Button mbtChoosePict;
    ImageView mImgPhotoDoc;
    Button mbtChooseDoc;
    TextView mtxtphoto;
    TextView mtxtdoc;
    Button mbtcancel;
    Button mbtConfirm;
    AutoCompleteTextView mautoComplete;
    TextView error;
    String title;
    String categorie;
    String description;
    TextInputLayout mTxtDescription;
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
        mbtChoosePict.setOnClickListener(this);
        //Bouton choisir doc service
        mbtChooseDoc.setOnClickListener(this);

        mTxtTitleService.setOnFocusChangeListener(this);

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
        mImgPhotoDoc = findViewById(R.id.image_add_service_document_item);
        mtxtphoto = findViewById(R.id.textphoto_add_service);
        mtxtdoc = findViewById(R.id.textdoc_add_service);
        mbtcancel = findViewById(R.id.cancel_add_service);
        mbtConfirm = findViewById(R.id.confirm_add_service);
        mbtChoosePict = findViewById(R.id.btn_con_parcourir_photoservice);
        mbtChooseDoc = findViewById(R.id.btn_con_parcourir_docu_user);
        error = findViewById(R.id.title_error_add_service);
        error.setVisibility(View.INVISIBLE);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_con_parcourir_photoservice :
                // Clic pour chercher photo de service

                ChooseDoc(REQUEST_SELECT_IMAGE_SERVICE);

                break;
            case R.id.btn_con_parcourir_docu_user :
                // Clic pour chercher photo du CV service

                ChooseDoc(REQUEST_SELECT_DOC_SERVICE);
                break;
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

                    Service service = new Service("0",
                            title,
                            true,
                            description,
                            null,categorie,
                            new Timestamp(new Date()),
                            "name_provider",
                            null,
                            null,
                            null,
                            null);

                    Map<String, Object> serv = new HashMap<>();
                    serv.put("status",service.getStatus());
                    serv.put("title",service.getTitle());
                    serv.put("categorie",service.getCategorie());
                    serv.put("description",service.getDescription());
                    serv.put("photo",service.getPhotoService());
                    serv.put("add_date",service.getAddDate());

                    FireBaseUtils.addUser(COLLECTION_SERVICE,service,this);

                    startActivity(new Intent(this,Presentation_prestations.class));
                }

                break;
            case R.id.cancel_add_service:
                // Clic sur le bouton annuler
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
            setImage(mtxtphoto,mImgPhotoService,data);
        }
        if (requestCode == REQUEST_SELECT_DOC_SERVICE && resultCode == RESULT_OK) {
            setImage(mtxtdoc,mImgPhotoDoc,data);
        }

    }

    private void setImage(TextView txtdesc, ImageView imageViewId, @Nullable Intent data){

        if (data != null) {
            Uri selectedImageUri = data.getData();
            if (Build.VERSION.SDK_INT < 19) {
                String selectedImagePath = getPath(selectedImageUri);
                Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath);
                txtdesc.setVisibility(View.INVISIBLE);
                imageViewId.setImageBitmap(bitmap);
            }
            else{
                ParcelFileDescriptor parcelFileDescriptor;
                try {
                    parcelFileDescriptor = getContentResolver().openFileDescriptor(selectedImageUri, "r");
                    FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                    Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                    parcelFileDescriptor.close();
                    txtdesc.setVisibility(View.INVISIBLE);
                    imageViewId.setImageBitmap(image);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
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