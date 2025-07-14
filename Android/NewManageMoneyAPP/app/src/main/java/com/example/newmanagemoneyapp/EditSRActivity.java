package com.example.newmanagemoneyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.newmanagemoneyapp.dbActivity.DeleteActivity;
import com.example.newmanagemoneyapp.dbActivity.UpdateActivity;

public class EditSRActivity extends AppCompatActivity {

    private Button medit_sr,medit_srsc;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sractivity);
        medit_sr=findViewById(R.id.edit_sr);
        medit_srsc=findViewById(R.id.edit_srsc);
        medit_sr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  startActivity(new Intent(EditSRActivity.this, UpdateActivity.class));
            }
        });
        medit_srsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditSRActivity.this, DeleteActivity.class));
            }
        });

    }
}