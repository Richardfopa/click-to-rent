package com.orange.click_2_rent;

import static com.orange.click_2_rent.DemarrageApp.SENT_USERS;
import static com.orange.click_2_rent.DemarrageApp.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;




import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.orange.click_2_rent.Models.FirebasesUtil;
import com.orange.click_2_rent.Models.Users;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    public static final String SENT_USERS_IT = "connection_ockay";
    private FirebaseUser mUser;
    private Users users ;
    TabLayout Tablelayout;
    ViewPager2  Viewpage;
    MaPageAdapter pagerAdapter;
    CardView myCardview;
    public static final String CLE_POSITION_CoURANTE = "com.orange.click_2_rent";
    public static int positionCourante;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        Log.d(TAG, "onCreate: " + mUser.toString());
        Toast.makeText(this,"votre Id " + mUser.getUid(),Toast.LENGTH_LONG).show();

        if(savedInstanceState!=null){
            positionCourante = savedInstanceState.getInt(CLE_POSITION_CoURANTE,0);
            return;
        }

        myCardview = findViewById(R.id.monCadre);
        Tablelayout = findViewById(R.id.maTabLayout);
        Viewpage = findViewById(R.id.nom_de_page);

        //Recuperation et Affichage des icones

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_menu);

        //On enleve l'elevation

        getSupportActionBar().setElevation(0);

        FragmentManager mfragment = getSupportFragmentManager();
        pagerAdapter = new MaPageAdapter(mfragment,getLifecycle());
        Viewpage.setAdapter(pagerAdapter);


        Tablelayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                CardView card = Viewpage.findViewById(R.id.monCadre);
                Viewpage.setCurrentItem(tab.getPosition());

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });
        Viewpage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Tablelayout.selectTab(Tablelayout.getTabAt(position));
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.botton_nav_bar,menu);
        return true;
    }
    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.home:

                Toast.makeText(this, "vous avez selectionnez Home", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.Parametres:

                Toast.makeText(this, "Parametres", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.profil:
                if(mUser != null){
                    Users user = new Users();

                    FirebaseUser fuser = mAuth.getCurrentUser();
                    if (fuser.getUid() != null) {
                        Toast.makeText(this, "User id" + fuser.getUid(), Toast.LENGTH_LONG).show();
                        Log.d(TAG, "updateUI: " + fuser.getUid());
                        user.setId(fuser.getUid());
                        Log.d(TAG, "updateUI: " + user.getId());

                        final DocumentReference docUser = FirebasesUtil.getUsers(fuser.getUid());

                        docUser.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {

                                if (snapshot != null && snapshot.exists()) {

                                    user.setTelphone(snapshot.getString("telphone"));
                                    user.setEmail(snapshot.getString("email"));
                                    user.setNom(snapshot.getString("nom"));
                                    user.setPhoto_user(snapshot.getString("photo_user"));
                                    user.setDate_darriver(snapshot.getTimestamp("date_darriver"));
                                    user.setAdresse(snapshot.getString("adresse"));
                                    Log.d(TAG, "onEvent: " + snapshot.getData().toString());
                                    Intent iput = new Intent(MainActivity.this, ProfileMainActivity.class);
                                    iput.putExtra("nom",user.getNom());
                                    iput.putExtra("email",user.getEmail());
                                    iput.putExtra("photo_user",user.getPhoto_user());
                                    startActivity(iput);
                                }else{
                                    startActivity(new Intent(MainActivity.this,ConnexionActivity.class));
                                }
                            }
                        });
                    }
                }
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }

    }

    //Verifier si un utilisateur a un document dans la collection user

    public Users getUsers(String firebaseuserid) {

        Users user = new Users();
        if (firebaseuserid != null) {
            Toast.makeText(this, "User id" + firebaseuserid, Toast.LENGTH_LONG).show();
            Log.d(TAG, "updateUI: " + firebaseuserid);
            user.setId(firebaseuserid);
            Log.d(TAG, "updateUI: " + user.getId());

            final DocumentReference docUser = FirebasesUtil.getUsers(firebaseuserid);

            docUser.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {

                    if (snapshot != null && snapshot.exists()) {

                        user.setTelphone(snapshot.getString("telphone"));
                        user.setEmail(snapshot.getString("email"));
                        user.setNom(snapshot.getString("nom"));
                        user.setPhoto_user(snapshot.getString("photo_user"));
                        user.setDate_darriver(snapshot.getTimestamp("date_darriver"));
                        user.setAdresse(snapshot.getString("adresse"));
                        Log.d(TAG, "onEvent: " + snapshot.getData().toString());
                        Log.d(TAG, "onEvent: " + users.toString());
                        Toast.makeText(MainActivity.this, users.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        return user;
    }
}