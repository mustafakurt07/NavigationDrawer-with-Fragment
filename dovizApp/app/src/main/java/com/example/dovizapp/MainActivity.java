package com.example.dovizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.sdsmdg.tastytoast.TastyToast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
private NavigationView navigationView;
private DrawerLayout drawerLayout;
private Toolbar toolbar;
private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView=findViewById(R.id.navigationView);
        drawerLayout=findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        View baslik=navigationView.inflateHeaderView(R.layout.navigation_tasarim);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.coronaData)
        {   fragment=new CorinaFragment();
            TastyToast.makeText(getApplicationContext(), "Veriler Başarıyla çekildi !", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
        }
        if(item.getItemId()==R.id.coronaFavori)
        {   fragment=new DepremFragment();
            TastyToast.makeText(getApplicationContext(), "Veriler Başarıyla çekildi !", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);

        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentTutucu,fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            Intent intent=new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }


}