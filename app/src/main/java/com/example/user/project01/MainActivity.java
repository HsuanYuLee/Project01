package com.example.user.project01;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ListView lvStore = findViewById(R.id.StoreList);
        List storelist = getStorelist();

        
        lvStore.setAdapter(new StoreAdapter(this, storelist));
        lvStore.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {


            }
        });
    }

    private class Store
    {
        int ivImage;
        String Name,Address,Time,Phone;

        public Store(int getImage, String getName, String getAddress, String getTime,String getPhone)
        {
            ivImage = getImage;
            Name = getName;
            Address = getAddress;
            Time = getTime;
            Phone = getPhone;
        }

    }

    private class StoreAdapter extends BaseAdapter
    {
        Context context;
        List Storelist;

        StoreAdapter(Context context, List Storelist)
        {
            this.context = context;
            this.Storelist = Storelist;
        }

        public int getCount()
        {
            return Storelist.size();
        }

        public View getView(int position, View itemView, ViewGroup parent)
        {
            if(itemView == null)
            {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                itemView = layoutInflater.inflate(R.layout.item, parent, false);
            }

            Store Store = (MainActivity.Store) Storelist.get(position);

            ImageView ivImage = (ImageView) itemView.findViewById(R.id.StoreImage);
            ivImage.setImageResource(Store.ivImage);

            TextView tvName = (TextView) itemView.findViewById(R.id.Name);
            tvName.setText(Store.Name);

            TextView tvTime = (TextView) itemView.findViewById(R.id.Time);
            tvTime.setText(Store.Time);

            TextView tvAddress = (TextView) itemView.findViewById(R.id.Address);
            tvAddress.setText(Store.Address);

            TextView tvPhone = (TextView) itemView.findViewById(R.id.Phone);
            tvPhone.setText(Store.Phone);

            return itemView;
        }



        public Object getItem(int Position)
        {
            return Storelist.get(Position);
        }

        public long getItemId(int Position)
        {
            return 0;
        }

    }

    private List getStorelist()
    {
        List<Store> Storelist = new ArrayList<>();
        Storelist.add(new Store(
                 R.drawable.store01,
                "洪元讚燒肉飯 中埔便當店",
                "地址：33076桃園市桃園區中埔一街340號",
                "營業時間：11:00–14:00, 16:30–20:00",
                "電話：03 3021611"));
        Storelist.add(new Store(
                R.drawable.store02,
                "\uD845\uDE19師傅便當專賣店-桃園店",
                "地址：330桃園市桃園區南華街152號",
                "營業時間：10:00–20:00",
                "電話：03 3472222"));
        Storelist.add(new Store(
                R.drawable.store03,
                "正一排骨",
                "地址：330桃園市桃園區中正路730號",
                "營業時間：10:30–20:30",
                "電話：03 3556165"));
        Storelist.add(new Store(
                R.drawable.store04,
                "台灣火車頭鐵路便當中山店",
                "地址：330桃園市桃園區中山路20號",
                "營業時間：11:00–21:00",
                "電話：03 3350290"));
        Storelist.add(new Store(
                R.drawable.store05,
                "好味池上便當",
                "地址：桃園區健行路22號",
                "營業時間：10:30–14:30, 16:30–20:00",
                "電話：03 3166559"));


        return Storelist;
    }

}





