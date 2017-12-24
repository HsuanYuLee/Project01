package com.example.user.project01;

import android.content.Intent;
import android.net.Uri;
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

public class OrderActivity extends AppCompatActivity
{
    /*
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
            case R.id.Order_data:
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    Bundle bundle = getIntent().getExtras();
    TextView Order = findViewById(R.id.Show_Order);
    Button Call_Phone = findViewById(R.id.Call);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        /*

        if(bundle != null)
        {
            Order.setText(bundle.getString("My_Order"));
            Call_Phone.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent Call = new Intent(Intent.ACTION_CALL);
                    Call.setData(Uri.parse("tel:" + bundle.getString("PHONE").trim()));
                }
            });
        }
        else
        {
            Order.setText("尚未加入訂單");
            Call_Phone.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Toast.makeText(OrderActivity.this,"尚未選擇店家",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }*/
}
