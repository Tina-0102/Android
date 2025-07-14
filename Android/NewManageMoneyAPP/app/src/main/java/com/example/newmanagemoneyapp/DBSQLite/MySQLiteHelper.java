package com.example.newmanagemoneyapp.DBSQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

//SQlite，创建数据库，创建数据表 单例模式调用
public class MySQLiteHelper extends SQLiteOpenHelper {

    private Context mContext;
    private static MySQLiteHelper mInstance = null;
    public static final String DATABASE_NAME = "xys.db";
    private static final int DATABASE_VERSION = 1;

    public static final String CREATE_BOOK = "create table book ("
            + "id integer primary key autoincrement,  "
            +"image text,"
            + "title text, "
            + "data text, "
            + "name text, "
            + "url text)";

    public static final String CREATE_BOOK2 = "create table book2 ("
            + "id integer primary key autoincrement,  "
            +"image text,"
            + "title text, "
            + "data text, "
            + "name text, "
            + "url text)";

    public MySQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }



    public static synchronized MySQLiteHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySQLiteHelper(context);
        }
        return mInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL (CREATE_BOOK);
        db.execSQL(CREATE_BOOK2);
        Toast.makeText(mContext,"创建成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
