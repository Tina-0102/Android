package com.example.newmanagemoneyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button msr,mzc,mlssr,mlszc;
    private Button msrbj,mzcbj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        msr=findViewById(R.id.sr);
        mzc=findViewById(R.id.zc);
        mlssr=findViewById(R.id.lssr);
        mlszc=findViewById(R.id.lszc);
        msrbj=findViewById(R.id.srbj);
        mzcbj=findViewById(R.id.zcbj);
        msr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,IncomeActivity.class));
            }
        });
        mzc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Income2Activity.class));
            }
        });
        mlssr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SeeAllActivity.class));
            }
        });
        mlszc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SeeAll2Activity.class));
            }
        });

        msrbj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,EditSRActivity.class));
            }
        });

        mzcbj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,EditSRActivity2.class));
            }
        });
    }
}