package com.example.newmanagemoneyapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newmanagemoneyapp.NewDBSQlite.MyDBOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class SeeAll2Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private HomeWarningdapter mListAdapter;
    List<RegionModel> list = new ArrayList<>();
    MyDBOpenHelper mhelper;//定义一个数据库帮助类对象
    SQLiteDatabase db;//定义一个操作的数据库的类对象
    private TextView title;
    private ImageButton backward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all2);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN );//这句让view 顶到顶
        getWindow().setStatusBarColor(Color.TRANSPARENT);//这句让状态栏透明

        initView();
        selectall();
    }


    private void initView() {
        title=findViewById(R.id.nav_title);
        backward=findViewById(R.id
                .button_backward);
        backward.setVisibility(View.VISIBLE);
        title.setText("历史支出");
        mhelper=new MyDBOpenHelper(SeeAll2Activity.this);//实例化数据库帮助类对象
        db=mhelper.getWritableDatabase();//获取数据库的读写权限
        mRecyclerView = findViewById(R.id.common_recycleView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        //设置偏移量
        mListAdapter = new HomeWarningdapter(this);
        //设置偏移量
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
//                   outRect.left = UIUtil.dip2px(view.getContext(),10);
//                   outRect.right = UIUtil.dip2px(view.getContext(),10);
            }
        });
        mRecyclerView.setAdapter(mListAdapter);
    }

    private void selectall() {
        //开始查询 参数：（实现查询的 sql 语句，条件参数）
        Cursor cursor =db.rawQuery("select * from stu_info2",null);
        if(cursor.getCount()!=0){//判断结果集中是否有数据，有：查询成功；无：查询失败
            Toast.makeText(SeeAll2Activity.this,"查询成功",Toast.LENGTH_SHORT).show();
            //循环遍历结果集，取出数据，显示出来
            //移动到首位
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                @SuppressLint("Range") String mname=cursor.getString(cursor.getColumnIndex("sno"));
                @SuppressLint("Range") String mregion=cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String mdata1=cursor.getString(cursor.getColumnIndex("data1"));
                @SuppressLint("Range") String mdata2=cursor.getString(cursor.getColumnIndex("data2"));


                RegionModel model = new RegionModel();
                model.setName(mname);
                model.setRegion(mregion);
                model.setData1(mdata1);
                model.setData2(mdata2);
                list.add(model);
                //移动到下一位
                cursor.moveToNext();
            }
            mListAdapter.setData(list);
            cursor.close();
        }else{
            Toast.makeText(SeeAll2Activity.this,"没有查询到任何支出记录！",Toast.LENGTH_SHORT).show();
        }

    }

}