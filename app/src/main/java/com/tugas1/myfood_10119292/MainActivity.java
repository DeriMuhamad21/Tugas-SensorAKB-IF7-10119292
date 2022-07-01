package com.tugas1.myfood_10119292;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

//Nama  : Deri Muhamad Handipa
//Nim   : 10119292
//Kelas : IF-7

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    MapsFragmen mapsFragmen = new MapsFragmen();
    Fragment1 fragment1 = new Fragment1();
    About about = new About();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, mapsFragmen).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Maps:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, mapsFragmen).commit();
                        return true;

                    case R.id.Profil:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                        return true;

                    case R.id.About:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, about).commit();
                        return true;
                }

                return false;
            }
        });


    }
}