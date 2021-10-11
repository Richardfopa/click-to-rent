package com.orange.click_2_rent;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.app.ActionBar;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orange.click_2_rent.Models.Users;
import com.squareup.picasso.Picasso;

public class PhotoProfilActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImgprofil;
    private ImageView mImgshareprofil;
    private ImageView mImgretour;
    public static final String url = "photo_user";
    public String userurl = "photo_user";
    private Uri uri;
    private String photourl;
    private String email;
    private String nom;
    private ActionBar actionbar;
    private Users user;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_profil);
        user = new Users();
        mImgprofil = findViewById(R.id.img_photo_profil);
        mImgshareprofil = findViewById(R.id.img_share_profil);
        mImgretour = findViewById(R.id.img_back_profil);
        userurl = getIntent().getStringExtra(url);
        uri = Uri.parse(userurl);
        actionbar = getActionBar();

        nom = getIntent().getStringExtra("nom");
        user.setNom(nom);
        email = getIntent().getStringExtra("email");
        user.setEmail(email);
        photourl = getIntent().getStringExtra("photo_user");
        user.setPhoto_user(photourl);

        //user.setDate_darriver(getIntent().getStringExtra("date_darriver"));
//        actionbar.setTitle(nom);
        //actionbar.setElevation(0.0F);
        //actionbar.hide();
        Picasso.with(this).load(uri).into(mImgprofil);
        mImgprofil.setOnClickListener(this);
        mImgshareprofil.setOnClickListener(this);
        mImgretour.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_share_profil:

                Toast.makeText(getApplicationContext(), "Partager votre profil ", Toast.LENGTH_SHORT).show();
                String mimeType = "Text/plain";
                ShareCompat.IntentBuilder.from(this)
                        .setType(mimeType)
                        .setChooserTitle("Share this text with")
                        .setText(photourl)
                        .startChooser();
                break;
            case R.id.img_back_profil:

                Toast.makeText(getApplicationContext(), "Retour sur le profil ", Toast.LENGTH_SHORT).show();
                Intent back = new Intent(this,ProfileMainActivity.class);
                back.putExtra("nom",nom);
                back.putExtra("email",email);
                back.putExtra("photo_user",photourl);
                startActivity(back);
                finish();
                break;
        }
    }
}