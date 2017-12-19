package com.example.user.project01;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ManuActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu);

        ListView lvBento = findViewById(R.id.Bentolist);
        List bentolist = getBentolist();
        lvBento.setAdapter(new BentoAdapter(this, bentolist));

    }

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
                Intent intent = new Intent(this,StoreActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.Check_order:
                break;

            case R.id.Order_data:
                break;

            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    private class Bento
    {
        int ivImage;
        String Name,Price,Confirm_number,Confirm;

        public Bento(int getImage, String getName, String getPrice,String getConfirm_number, String getConfirm)
        {
            ivImage = getImage;
            Name = getName;
            Price = getPrice;
            Confirm_number = getConfirm_number;
            Confirm = getConfirm;
        }
    }

    private class BentoAdapter extends BaseAdapter
    {
        Context context;
        List bentolist;

        BentoAdapter(Context context,List bentolist)
        {
            this.context = context;
            this.bentolist = bentolist;
        }

        public int getCount()
        {
            return bentolist.size();
        }

        public View getView(final int position, View itemView, ViewGroup parent)
        {
            if(itemView == null)
            {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                itemView = layoutInflater.inflate(R.layout.bentolist, parent, false);
            }

            itemView.setTag(null);

            final Bento Bento = (ManuActivity.Bento) bentolist.get(position);

            ImageView ivImage = (ImageView) itemView.findViewById(R.id.BentoImage);
            ivImage.setImageResource(Bento.ivImage);

            TextView tvName = (TextView) itemView.findViewById(R.id.BentoName);
            tvName.setText(Bento.Name);

            TextView tvPrice = (TextView) itemView.findViewById(R.id.BentoPrice);
            tvPrice.setText(Bento.Price);

            final EditText tvConfirm_number = itemView.findViewById(R.id.Confirm_number);
            tvConfirm_number.setText(null);

            Button tvConfirm = (Button) itemView.findViewById(R.id.Confirm);
            tvConfirm.setText(Bento.Confirm);
            tvConfirm.setTag(position);

            tvConfirm.setOnClickListener(new View.OnClickListener()
            {
                //private final static String Tag = "ManuActivity";
                //private final static String Order_data = "Order_data.txt";
                @Override
                public void onClick(View view)
                {

                    /*
                    String num = tvConfirm_number.getText().toString();
                    String Note = Bento.Name +" "+ num + " 份已加入訂單";
                    BufferedWriter Order_bw = null;
                    try
                    {
                        FileOutputStream Order = openFileOutput(Order_data, MODE_PRIVATE);
                        Order_bw = new BufferedWriter(new OutputStreamWriter(Order));
                        Order_bw.write(Bento.Name +" "+ num + " 份");
                    }
                    catch (IOException e)
                    {
                        Log.e(Tag,e.toString());
                    }
                    finally
                    {
                        try
                        {
                            if(Order_bw != null)
                            {
                                Order_bw.close();
                            }
                        }
                        catch (IOException e)
                        {
                            Log.e(Tag,e.toString());
                        }

                        Toast.makeText(ManuActivity.this, Order_data, Toast.LENGTH_SHORT).show();
                    }
                    */

                    String num = tvConfirm_number.getText().toString();
                    String Note = Bento.Name +" "+ num + " 份已加入訂單";
                    Toast.makeText(ManuActivity.this, Note, Toast.LENGTH_SHORT).show();
                }

            });

            return itemView;
        }

        public Object getItem(int Position)
        {
            return bentolist.get(Position);
        }
        public long getItemId(int Position)
        {
            return 0;
        }
    }

    private List getBentolist()
    {
        Bundle bundle = getIntent().getExtras();
        int Id = bundle.getInt("ID");

        List<Bento> Bentolist = new ArrayList<>();
        int i = 1;

        do
            {
                int BentoImageId = getResources().
                        getIdentifier("store" +Id+ "_manu" +i, "drawable", this.getPackageName());
                int BentoNameId = getResources().
                        getIdentifier("Bento" +Id+ "_Name" +i, "string", this.getPackageName());
                int BentoPriceId = getResources().
                        getIdentifier("Bento" +Id+ "_Price" +i, "string", this.getPackageName());
                int BentoConfirm_numberId = getResources().
                        getIdentifier("Confirm_number", "string", this.getPackageName());
                int BentoConfirmId = getResources().
                        getIdentifier("Add", "string", this.getPackageName());

                Bentolist.add(new Bento(
                        BentoImageId,
                        getString(BentoNameId),
                        getString(BentoPriceId) + "元",
                        getString(BentoConfirm_numberId),
                        getString(BentoConfirmId)));

                i++;

            }while(getResources().getIdentifier("store" +Id+ "_manu" +i, "drawable", this.getPackageName()) != 0);

        return Bentolist;
    }
}
