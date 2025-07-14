package com.example.newmanagemoneyapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.newmanagemoneyapp.NewDBSQlite.MyDBOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Income2Activity extends AppCompatActivity {
    private MyDBOpenHelper mhelper;//定义数据库帮助类对象
    private SQLiteDatabase db;//定义一个可以操作的数据库对象
    private EditText met_name,met_money;
    private TextView met_lb,met_time;
    private OptionsPickerView mAlarmTypeNameOptions;
    private List<String> mAreaTypelist = new ArrayList<>();
    private Button mlook_btn;
    private TimePickerView mTimePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        initView();
        //创建时间选择器
        initTimePickView();
        getAllageList();
    }

    private void initTimePickView() {
        //时间选择器
        mTimePicker = new TimePickerBuilder(Income2Activity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String selectTime = TimeHelper.getTime(date);
                if (v.getId() == R.id.et_time) {
                    met_time.setText(selectTime);
                }
            }

        }).setType(new boolean[]{true, true, true, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(true)
                .setCancelText("取消")
                .setSubmitText("确认")
                .setCancelColor(this.getResources().getColor(R.color.white))
                .setSubmitColor(this.getResources().getColor(R.color.white))
                .setBgColor(this.getResources().getColor(R.color.teal_700))
                .setTitleBgColor(this.getResources().getColor(R.color.teal_700))
                .setTextColorCenter(this.getResources().getColor(R.color.white))
                .setTextColorOut(this.getResources().getColor(R.color.white))
                .setTextColorOut(this.getResources().getColor(R.color.white))
                .setDecorView(null).build();
    }

    private void getAllageList() {
        mAreaTypelist.clear();
        mAreaTypelist.add("信用卡");
        mAreaTypelist.add("现金");
        mAreaTypelist.add("手机支付");
    }

    @SuppressLint("WrongViewCast")
    private void initView() {
        met_name=findViewById(R.id.et_name);
        met_money=findViewById(R.id.et_money);
        met_time=findViewById(R.id.et_time);
        met_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimePicker.show(met_time);
            }
        });
        met_lb=findViewById(R.id.et_lb);
        met_lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDataTypePickerView();
            }
        });
        mlook_btn=findViewById(R.id.look_btn);
        mlook_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values=new ContentValues();
                values.put("sno",met_name.getText().toString());
                values.put("name",met_money.getText().toString());
                values.put("data1",met_time.getText().toString());
                values.put("data2",met_lb.getText().toString());
                db.insert("stu_info2",null,values);
                Toast.makeText(Income2Activity.this, "记录成功！", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Income2Activity.this,MainActivity.class));
            }
        });
        mhelper=new MyDBOpenHelper(Income2Activity.this);//实例化数据库帮助类
        db=mhelper.getWritableDatabase();//创建数据库，获取数据库的读写权限
    }

    private void ShowDataTypePickerView() {
        mAlarmTypeNameOptions=new OptionsPickerBuilder(Income2Activity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String xx=mAreaTypelist.get(options1);
                met_lb.setText(xx);
                Log.e("leo","ssss"+met_lb);
            }
        }).setCancelText("取消")
                .setSubmitText("确认")
                .setSubCalSize(16)
                .setContentTextSize(16)
                .setTitleSize(16)
                .setCancelColor(Color.WHITE)
                .setSubmitColor(Color.WHITE)
                .setBgColor(this.getResources().getColor(R.color.teal_700))
                .setTitleBgColor(this.getResources().getColor(R.color.teal_700))
                .setTextColorCenter(Color.WHITE)
                .setTextColorOut(Color.WHITE)
                .setTextColorOut(Color.WHITE).build();

        mAlarmTypeNameOptions.setPicker(mAreaTypelist);
        mAlarmTypeNameOptions.show();
    }
}