package com.example.user.project01;

import android.content.Context;
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
                itemView = layoutInflater.inflate(R.layout.storelist, parent, false);
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

        int i = 1;
        do
        {

            int StoreImageId = getResources().
                    getIdentifier("store" + i, "drawable", this.getPackageName());
            int StoreNameId = getResources().
                    getIdentifier("Name" + i, "string", this.getPackageName());
            int StoreAddressId = getResources().
                    getIdentifier("Address" + i, "string", this.getPackageName());
            int StoreTimeId = getResources().
                    getIdentifier("Time" + i, "string", this.getPackageName());
            int StorePhoneId = getResources().
                    getIdentifier("Phone" + i, "string", this.getPackageName());

            Storelist.add(new Store(
                    StoreImageId,
                    getString(StoreNameId),
                    "地址：" + getString(StoreAddressId),
                    "營業時間：" + getString(StoreTimeId),
                    "電話：" + getString(StorePhoneId)));
            i++;

        }while(getResources().getIdentifier("store" + i, "drawable", this.getPackageName()) != 0);


        return Storelist;
    }

}





