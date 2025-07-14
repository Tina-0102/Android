package com.example.newmanagemoneyapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newmanagemoneyapp.dbActivity.DeleteActivity2;
import com.example.newmanagemoneyapp.dbActivity.UpdateActivity2;

public class EditSRActivity2 extends AppCompatActivity {

    private Button medit_sr,medit_srsc;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sractivity2);
        medit_sr=findViewById(R.id.edit_sr);
        medit_srsc=findViewById(R.id.edit_srsc);
        medit_sr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  startActivity(new Intent(EditSRActivity2.this, UpdateActivity2.class));
            }
        });
        medit_srsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditSRActivity2.this, DeleteActivity2.class));
            }
        });

    }
}