package com.example.user.project01;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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


        lvBento.setAdapter(new BentoAdapter(this,bentolist));

    }

    private class Bento
    {
        int ivImage;
        String Name,Price;

        public Bento(int getImage, String getName, String getPrice)
        {
            ivImage = getImage;
            Name = getName;
            Price = getPrice;
        }
    }

    private class BentoAdapter extends BaseAdapter
    {
        Context context;
        List Bentolist;

        BentoAdapter(Context context,List Bentolist)
        {
            this.context = context;
            this.Bentolist = Bentolist;
        }

        public int getCount()
        {
            return Bentolist.size();
        }

        public View getView(int position, View itemView, ViewGroup parent)
        {
            if(itemView == null)
            {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                itemView = layoutInflater.inflate(R.layout.storelist, parent, false);
            }

            Bento Bento = (ManuActivity.Bento) Bentolist.get(position);

            ImageView ivImage = (ImageView) itemView.findViewById(R.id.BentoImage);
            ivImage.setImageResource(Bento.ivImage);

            TextView tvName = (TextView) itemView.findViewById(R.id.BentoName);
            tvName.setText(Bento.Name);

            TextView tvPrice = (TextView) itemView.findViewById(R.id.BentoPrice);
            tvPrice.setText(Bento.Price);

            return itemView;
        }

        public Object getItem(int Position)
        {
            return Bentolist.get(Position);
        }

        public long getItemId(int Position)
        {
            return 0;
        }
    }

    private List getBentolist()
    {
        List<Bento> Bentolist = new ArrayList<>();
        int i = 1;

        do
            {
                int BentoImageId = getResources().
                        getIdentifier("store" +1+ "_manu" +i, "drawable", this.getPackageName());
                int BentoNameId = getResources().
                        getIdentifier("Bento" +1+ "_Name" +i, "string", this.getPackageName());
                int BentoPriceId = getResources().
                        getIdentifier("Bento" +1+ "_Price" +i, "string", this.getPackageName());

                Bentolist.add(new Bento(
                        BentoImageId,
                        getString(BentoNameId),
                        "價格：" + getString(BentoNameId)));

                i++;

            }while(getResources().getIdentifier("store" +1+ "_manu" +i, "drawable", this.getPackageName()) != 0);

        return Bentolist;
    }

}
