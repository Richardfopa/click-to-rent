package com.orange.click_2_rent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;




import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

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
        final android.app.ActionBar actionbar = getActionBar();


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


        if(savedInstanceState!=null){
            positionCourante = savedInstanceState.getInt(CLE_POSITION_CoURANTE,0);

            return;
        }


        //getSupportFragmentManager().beginTransaction()
        //      .add(R.id.principal,new Fragment1(),Fragment1.class.getSimpleName())
        //      .commit();

        myCardview = findViewById(R.id.monCadre);
        Tablelayout = findViewById(R.id.maTabLayout);
        Viewpage = findViewById(R.id.nom_de_page);

        //Recuperation et Affichage des icones

        // Tablelayout.getTabAt(0).setIcon(R.drawable.home_repair_service_24);
        //Tablelayout.getTabAt(1).setIcon(R.drawable.restaurant_24);
        // Tablelayout.getTabAt(2).setIcon(R.drawable.local_taxi_24);


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

    @Override
    protected void onStart() {
        super.onStart();

    }

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.botton_nav_bar,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.home:

                Toast.makeText(this, "vous avez selectionnez Home", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.Parametres:

                Toast.makeText(this, "Parametres", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.profil:

                Intent intent = new Intent(getApplicationContext(),ConnexionActivity.class);
                startActivity(intent);

            default:
                return super.onOptionsItemSelected(menuItem);
        }

    }

}