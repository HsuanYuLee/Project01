package com.example.user.project01;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity
{
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.Home:
                break;
            case R.id.My_order:
                Toast.makeText(StoreActivity.this,"尚未建立訂單，請先選擇餐點！",Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        ListView lvStore = findViewById(R.id.StoreList);
        List storelist = getStorelist();
        lvStore.setAdapter(new StoreAdapter(this, storelist));


        lvStore.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Store store = (Store)adapterView.getItemAtPosition(i);
                Intent intent = new Intent(StoreActivity.this, ManuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID", store.Id);
                bundle.putString("PHONE",store.Phone);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });
    }


    private class Store
    {
        int Id,ivImage;
        String Name,Address,Time,Phone;

        Store(int getId, int getImage, String getName, String getAddress, String getTime, String getPhone)
        {
            Id = getId;
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
        List storelist;

        StoreAdapter(Context context, List storelist)
        {
            this.context = context;
            this.storelist = storelist;
        }

        public int getCount()
        {
            return storelist.size();
        }

        public View getView(int position, View itemView, ViewGroup parent)
        {
            if(itemView == null)
            {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                itemView = layoutInflater.inflate(R.layout.storelist, parent, false);
            }

            Store Store = (StoreActivity.Store) storelist.get(position);

            ImageView ivImage = itemView.findViewById(R.id.StoreImage);
            ivImage.setImageResource(Store.ivImage);

            TextView tvName = itemView.findViewById(R.id.Name);
            tvName.setText(Store.Name);

            TextView tvTime = itemView.findViewById(R.id.Time);
            tvTime.setText(Store.Time);

            TextView tvAddress = itemView.findViewById(R.id.Address);
            tvAddress.setText(Store.Address);

            TextView tvPhone = itemView.findViewById(R.id.Phone);
            tvPhone.setText(Store.Phone);

            return itemView;
        }

        public Object getItem(int Position)
        {
            return storelist.get(Position);
        }

        public long getItemId(int Position)
        {
            return 0;
        }

    }

    private List getStorelist()
    {
        List<Store> Storelist = new ArrayList<>();
        int Id = 1;

        do
        {

            int StoreImageId = getResources().
                    getIdentifier("store" + Id, "drawable", this.getPackageName());
            int StoreNameId = getResources().
                    getIdentifier("Store_Name" + Id, "string", this.getPackageName());
            int StoreAddressId = getResources().
                    getIdentifier("Store_Address" + Id, "string", this.getPackageName());
            int StoreTimeId = getResources().
                    getIdentifier("Store_Time" + Id, "string", this.getPackageName());
            int StorePhoneId = getResources().
                    getIdentifier("Store_Phone" + Id, "string", this.getPackageName());

            Storelist.add(new Store(
                    Id,
                    StoreImageId,
                    getString(StoreNameId),
                    "地址：" + getString(StoreAddressId),
                    "營業時間：" + getString(StoreTimeId),
                    "電話：" + getString(StorePhoneId)));
            Id++;

        }while(getResources().getIdentifier("store" + Id, "drawable", this.getPackageName()) != 0);


        return Storelist;
    }

}





