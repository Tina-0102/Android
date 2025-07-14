package com.example.newmanagemoneyapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.newmanagemoneyapp.DBSQLite.ItemFactory;
import com.example.newmanagemoneyapp.DBSQLite.MySQLiteHelper;
import com.example.newmanagemoneyapp.DBSQLite.testmodel2;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private TextView mRegisterBtn;
    private Button mEditPasswordBtn;
    private Button mLoginBtn;
    private EditText mUserEdit;
    private EditText mPsdEdit;
    private String registrationID;
    private LinearLayout loadingLinearLayout;
    private SQLiteDatabase db;
    private MySQLiteHelper mDbHelper;
    List<testmodel2.DataDTO> list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_login);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN );//这句让view 顶到顶
        getWindow().setStatusBarColor(Color.TRANSPARENT);//这句让状态栏透明

        mRegisterBtn = findViewById(R.id.register_btn);
        mLoginBtn = findViewById(R.id.login_btn);
        mUserEdit = findViewById(R.id.lg_user);
        mPsdEdit = findViewById(R.id.lg_psd);

        initAction();

    }


    private void initAction() {
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loginAction();
            }
        });
    }

    private void loginAction(){
        //在点击登陆的时候 加入loading动画

        String user = mUserEdit.getText().toString();

        if (user.length()<=0) {
            Toast.makeText(LoginActivity.this,"用户名不能为空", Toast.LENGTH_LONG).show();
            return;
        }

        String psd = mPsdEdit.getText().toString();
        if (psd.length()<=0) {
            Toast.makeText(LoginActivity.this,"密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        else{
            //创建DatabaseHelper对象
            mDbHelper = MySQLiteHelper.getInstance(this);
            //调用getReadableDatabase方法如果数据库不存在 则创建  如果存在则打开
            db= mDbHelper.getReadableDatabase();
            list =  ItemFactory.createItem (db);
            for (int i=0;i<list.size();i++){
              testmodel2.DataDTO model = list.get(i);
              if (user.equals(model.getThumbnail_pic_s()) && psd.equals(model.getTitle())){
                  Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                  startActivity(new Intent(LoginActivity.this, MainActivity.class));
              }
            }

        }
    }

    //使得返回键无法返回
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //  return true;
            return false; // 这里假设你是返回的true,false，那么就不会运行系统的返回操作。不能返回//上一个界面，由于这样就没有回调父类的onKeyDown方法就返回了。所以应该这样写例如以下：
            // return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    //注册
    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

}
