package com.example.newmanagemoneyapp.dbActivity;

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

public class DeleteActivity extends AppCompatActivity {

    EditText edit_foursno;
    Button btn_fourdelete;
    MyDBOpenHelper mhelper;//定义一个数据库帮助类对象
    SQLiteDatabase db;//定义一个操作的数据库的类对象
    private TextView title;
    private ImageButton backward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        initView();
        //2 删除按钮功能的实现
        btnDelete();
    }


    private void initView() {
        title=findViewById(R.id.nav_title);
        backward=findViewById(R.id
                .button_backward);
        backward.setVisibility(View.VISIBLE);
        title.setText("删除收入");
        edit_foursno=findViewById(R.id.editText_foursno);
        btn_fourdelete=findViewById(R.id.button_fourdelete);
        mhelper=new MyDBOpenHelper(DeleteActivity.this);//实例化数据库帮助类对象
        db=mhelper.getWritableDatabase();//获取数据库的读写权限
    }

    private void btnDelete() {
        btn_fourdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //怎么样删除呢？参数：（表名，删除的条件，条件的参数）
                db.delete("stu_info","sno=?",new String[]{edit_foursno.getText().toString()});
                Toast.makeText(DeleteActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}