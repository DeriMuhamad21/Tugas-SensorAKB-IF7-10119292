package com.tugas1.myfood_10119292;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

//Nama  : Deri Muhamad Handipa
//Nim   : 10119292
//Kelas : IF-7

public class ViewPager extends AppCompatActivity {
    Button button;
    androidx.viewpager.widget.ViewPager viewPager;
    int[] layouts;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = findViewById(R.id.pager);

        layouts = new int[] {
                R.layout.menu1,
        };

        adapter = new Adapter(this, layouts);
        viewPager.setAdapter(adapter);


    }

    public void Alihkan(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}