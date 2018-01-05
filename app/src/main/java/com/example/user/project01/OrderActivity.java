package com.example.user.project01;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.telecom.Call;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity
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
                Intent intent = new Intent(OrderActivity.this, StoreActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.My_order:
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
        setContentView(R.layout.activity_order);

        Bundle bundle = getIntent().getExtras();
        int BentoNum = 0;
        if (bundle != null)
        {
            BentoNum = bundle.getInt("BentoNum");
        }

        String order = null;

        TextView Order = findViewById(R.id.Show_Order);

        for(int i=0;i<BentoNum;i++)
        {

            int Price = Integer.valueOf(bundle.getString("Price"+i));
            int Num = Integer.valueOf(bundle.getString("Num"+i));

            order = order
                    + bundle.getString("Name"+i)
                    + " "
                    + bundle.getString("Num"+i)
                    + " "
                    + "份,共"
                    + (Price*Num)
                    + " 元\n";
        }
        Order.setText("order");


    }



}
