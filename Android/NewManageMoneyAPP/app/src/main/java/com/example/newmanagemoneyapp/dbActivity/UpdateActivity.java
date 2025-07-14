package com.example.newmanagemoneyapp.dbActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.newmanagemoneyapp.NewDBSQlite.MyDBOpenHelper;
import com.example.newmanagemoneyapp.R;

public class UpdateActivity extends AppCompatActivity {

    EditText edit_threeinputsno,edit_threesno,edit_threename,edit_threedep,edit_sex;
    Button btn_threequery,btn_threemodify;
    MyDBOpenHelper mhelper;//定义一个数据库帮助类对象
    SQLiteDatabase db;//定义一个操作的数据库的类对象
    private TextView title;
    private ImageButton backward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();
        //2 查询按钮功能的实现
        btnQuery();
        //3 修改按钮功能的实现
        btnModify();
    }


    private void initView() {
        title=findViewById(R.id.nav_title);
        backward=findViewById(R.id
                .button_backward);
        backward.setVisibility(View.VISIBLE);
        title.setText("修改收入");
        edit_threeinputsno=findViewById(R.id.editText_threeinputsno);
        edit_threesno=findViewById(R.id.editText_threesno);
        edit_threename=findViewById(R.id.editText_threename);
        edit_threedep=findViewById(R.id.editText_threedep);
        edit_sex=findViewById(R.id.editText_sex);
        btn_threequery=findViewById(R.id.button_threequery);
        btn_threemodify=findViewById(R.id.button_threemodify);
        mhelper=new MyDBOpenHelper(UpdateActivity.this);//实例化数据库帮助类对象
        db= mhelper.getWritableDatabase();//获取数据库的读写权限
    }


    private void btnQuery() {
        btn_threequery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先查询显示，再修改。参数（String sql，String[ ] selectionArgs）
                Cursor cursor=db.rawQuery("select * from stu_info where sno=?",new String[]{edit_threeinputsno.getText().toString()});
                if(cursor.getCount()!=0){
                    Toast.makeText(UpdateActivity.this,"查询成功",Toast.LENGTH_SHORT).show();
                    while(cursor.moveToNext()){
                        @SuppressLint("Range") String mysno=cursor.getString(cursor.getColumnIndex("sno"));
                        @SuppressLint("Range") String myname=cursor.getString(cursor.getColumnIndex("name"));
                        @SuppressLint("Range") String mysex=cursor.getString(cursor.getColumnIndex("data1"));
                        @SuppressLint("Range") String mydep=cursor.getString(cursor.getColumnIndex("data2"));
                        edit_threesno.setText(mysno);
                        edit_threename.setText(myname);
                        edit_threedep.setText(mydep);
                        edit_sex.setText(mysex);
                    }
                }else{
                    Toast.makeText(UpdateActivity.this,"没有查询到该条收入",Toast.LENGTH_SHORT).show();
                    edit_threesno.setText("");
                    edit_threename.setText("");
                    edit_threedep.setText("");
                    edit_sex.setText("");
                }
            }
        });
    }

    private void btnModify() {
        btn_threemodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //修改数据代码如何写呢？参数：（表名，ContentValues 对象，更新的条件，条件的参数）
                ContentValues values=new ContentValues();
                values.put("sno",edit_threesno.getText().toString());
                values.put("name",edit_threename.getText().toString());
                values.put("data1",edit_sex.getText().toString());
                values.put("data2",edit_threedep.getText().toString());
                db.update("stu_info",values,"sno=?",new String[]{edit_threesno.getText().toString()});
                Toast.makeText(UpdateActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


}