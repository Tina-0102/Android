package com.example.newmanagemoneyapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.newmanagemoneyapp.DBSQLite.MySQLiteHelper;

public class RegisterActivity extends AppCompatActivity{

    private EditText mRegisteruser;
    private EditText mRegisterpsd;
    private EditText mRegisterpsd2;
    private Button   mRegisterbutton;
    private LinearLayout loadingLinearLayout;
    private MySQLiteHelper mDbHelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mRegisteruser = (EditText) findViewById(R.id.re_user);
        mRegisterpsd = (EditText) findViewById(R.id.re_psd);
        mRegisterpsd2 = (EditText) findViewById(R.id.re_psd2);
        mRegisterbutton= (Button) findViewById(R.id.re_btn);

        mRegisterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    private void insertData(SQLiteDatabase db, String s, String s1, String s2, String s3, String s4) {
        ContentValues cv = new ContentValues ();
        cv.put ("image",s);
        cv.put ("title",s1);
        cv.put ("data",s2);
        cv.put ("name",s3);
        cv.put ("url",s4);
        db.insert ("book",null,cv);
    }

    /**
     * 账号密码注册
     */
    private void signUp() {

        final String username = mRegisteruser.getText().toString();
        final String password = mRegisterpsd.getText().toString();
        final String password2 = mRegisterpsd2.getText().toString();

//        loadingLinearLayout.setVisibility(View.VISIBLE);
        if ("".equals(username) || "".equals(password) || "".equals(password2)) {
            Toast.makeText(RegisterActivity.this,"请将信息填写完整", Toast.LENGTH_LONG).show();
            return;
        }
        else if (!password.equals(password2)){
            Toast.makeText(RegisterActivity.this,"两次输入密码不一致", Toast.LENGTH_LONG).show();
            return;
        }
        else if (username.length()<2){
            Toast.makeText(RegisterActivity.this,"请输入不小于二位的账号", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            //创建DatabaseHelper对象
            mDbHelper = MySQLiteHelper.getInstance(this);
            //调用getReadableDatabase方法如果数据库不存在 则创建  如果存在则打开
            db= mDbHelper.getReadableDatabase();
            insertData (db,username,
                    password,"","","");
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

        }

    }
}