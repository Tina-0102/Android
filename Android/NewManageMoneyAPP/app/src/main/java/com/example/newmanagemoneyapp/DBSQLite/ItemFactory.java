package com.example.newmanagemoneyapp.DBSQLite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

//读取数据库中的book表。并且将读取到的数据按照testmodel2.ResultDTO.DataDTO的形式放入Item集合
public class ItemFactory {
    public static String[] bookImage = new String[1000];
    public static String[] bookUrl = new String[1000];
    public static String[] bookTitle = new String[1000];
    public static String[] bookData = new String[1000];
    public static String[] bookName = new String[1000];
    public static int bookCount;

    public static void queryDatabase(SQLiteDatabase db){

        String[] strings = new String[]{"id","image","title","data","name","url"};
        Cursor cursor = db.query ("book",strings,null,null,null,null,null);
        bookCount = cursor.getCount ();
        for (int i = 0; i < cursor.getCount (); i++) {
            if(cursor.moveToFirst ()) {
                cursor.move (i);
                int id = cursor.getInt (cursor.getColumnIndex ("id"));
                bookImage[i] = cursor.getString (cursor.getColumnIndex ("image"));
                bookTitle[i] = cursor.getString (cursor.getColumnIndex ("title"));
                bookData[i] = cursor.getString (cursor.getColumnIndex ("data"));
                bookName[i] = cursor.getString (cursor.getColumnIndex ("name"));
                bookUrl[i] = cursor.getString (cursor.getColumnIndex ("url"));
            }
        }
    }


    public static List<testmodel2.DataDTO> createItem(SQLiteDatabase db){
        List<testmodel2.DataDTO> items = new ArrayList<>();
        queryDatabase (db);
        for (int i=0;i < bookCount;i++){
            String image = bookImage[i];
            String title = bookTitle[i];
            String url = bookUrl[i];
            String data = bookData[i];
            String name = bookName[i];
            items.add(new testmodel2.DataDTO(image,title,data,name,url));
        }
        return items;
    }

}
