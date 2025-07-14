package com.example.newmanagemoneyapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newmanagemoneyapp.NewDBSQlite.MyDBOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class HomeWarningdapter extends RecyclerView.Adapter<HomeWarningdapter.InnerHolder> {

    private List<RegionModel> mData = new ArrayList<>();
    private Context mContext;
    private MyDBOpenHelper mhelper;//定义数据库帮助类对象
    private SQLiteDatabase db;//定义一个可以操作的数据库对象
    public HomeWarningdapter(Context context){
        mContext=context;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spot_cell, parent, false);
        final InnerHolder viewHolder = new InnerHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.itemView.setTag(position);
        holder.setData(position);

    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    //设置数据
    public void setData(List<RegionModel> list) {
        if (list != null) {
            mData.clear();
            mData.addAll(list);
        }
        //更新一下UI
        notifyDataSetChanged();
    }


    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setData(int position) {
            TextView t_sno=itemView.findViewById(R.id.sno);
            TextView t_name = itemView.findViewById(R.id.name);
            TextView t_data1 = itemView.findViewById(R.id.data_1);
            TextView t_data2 = itemView.findViewById(R.id.data_2);


            RegionModel model = mData.get(position);
            t_sno.setText("项目名称："+model.getName());
            t_name.setText("金额："+model.getRegion());
            t_data1.setText("日期时间："+model.getData1());
            t_data2.setText("类别："+model.getData2());
            mhelper=new MyDBOpenHelper(mContext.getApplicationContext());//实例化数据库帮助类
            db=mhelper.getWritableDatabase();//创建数据库，获取数据库的读写权限

        }

    }


}
