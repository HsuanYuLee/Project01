package com.example.user.project01;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class ManuActivity extends AppCompatActivity
{

    String Save_Order = "";
    String Trans_Order = "";
    ArrayList<Order> Orderlist = new ArrayList<>();

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
                finish();
                break;
            case R.id.My_order:

                if(Orderlist.isEmpty())
                {
                    Toast.makeText(ManuActivity.this,"尚未建立訂單，請先選擇餐點！",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(ManuActivity.this, OrderActivity.class);
                    Bundle bundle = new Bundle();

                    int i = 0;
                    for(Order order: Orderlist)
                    {
                        bundle.putString("Name"+i,order.Name);
                        bundle.putString("Price"+i,order.Price);
                        bundle.putString("Num"+i,order.Number);
                        intent.putExtras(bundle);

                        i++;
                    }
                    bundle.putInt("BentoNum",i);

                    startActivity(intent);
                }
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
        setContentView(R.layout.activity_manu);

        final ListView lvBento = findViewById(R.id.Bentolist);
        final List bentolist = getBentolist();
        lvBento.setAdapter(new BentoAdapter(this, bentolist));
    }

    private class Bento
    {
        int ivImage;
        String Name,Price,Confirm_number,Confirm;

        Bento(int getImage, String getName, String getPrice, String getConfirm_number, String getConfirm)
        {
            ivImage = getImage;
            Name = getName;
            Price = getPrice;
            Confirm_number = getConfirm_number;
            Confirm = getConfirm;
        }
    }

    private class Order
    {
        int ivImage;
        String Name,Price,Number;

        Order(int getImage, String getName, String getPrice, String getNumber)
        {
            ivImage = getImage;
            Name = getName;
            Price = getPrice;
            Number = getNumber;
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

            ImageView ivImage = itemView.findViewById(R.id.BentoImage);
            ivImage.setImageResource(Bento.ivImage);

            TextView tvName = itemView.findViewById(R.id.BentoName);
            tvName.setText(Bento.Name);

            TextView tvPrice = itemView.findViewById(R.id.BentoPrice);
            tvPrice.setText(Bento.Price);

            final EditText tvConfirm_number = itemView.findViewById(R.id.Confirm_number);
            tvConfirm_number.setText(null);

            Button tvConfirm = itemView.findViewById(R.id.Confirm);
            tvConfirm.setText(Bento.Confirm);
            tvConfirm.setTag(position);

            tvConfirm.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Bundle bundle = getIntent().getExtras();
                    int Id = 0;
                    if (bundle != null) {Id = bundle.getInt("ID");}

                    final int OrderImageId = getResources().
                            getIdentifier("store" +Id+ "_manu" + (position+1), "drawable", getPackageName());
                    final int OrderNameId = getResources().
                            getIdentifier("Bento" +Id+ "_Name" + (position+1), "string", getPackageName());
                    final int OrderPriceId = getResources().
                            getIdentifier("Bento" +Id+ "_Price" + (position+1), "string", getPackageName());
                    final String OrderNum = tvConfirm_number.getText().toString();

                    if(OrderNum.isEmpty())
                    {
                        Toast.makeText(ManuActivity.this, "尚未選擇餐點！",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        int X = 0;
                        final Order order = new Order(OrderImageId,getString(OrderNameId),getString(OrderPriceId),OrderNum);

                        for(final Order A: Orderlist)
                        {
                            if(A.Name.equals(order.Name))
                            {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(ManuActivity.this);
                                dialog.setMessage("已選過餐點,目前為"+A.Number+"份,是否更改為" + OrderNum + "份?");
                                dialog.setNegativeButton("否",new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1){}
                                });
                                dialog.setPositiveButton("是",new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1)
                                    {
                                        Orderlist.remove(A);
                                        Orderlist.add(order);
                                        Toast.makeText(ManuActivity.this, "更改完畢!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                dialog.show();
                            }else
                            {
                                X++;
                            }
                        }

                        if(X == Orderlist.size())
                        {
                            Orderlist.add(order);
                            Toast.makeText(ManuActivity.this, order.Name + order.Number + "份已加入購物車", Toast.LENGTH_SHORT).show();
                        }
                    }
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

    public void Save_Order(View view)
    {
        if(Save_Order.isEmpty())
        {
            Toast.makeText(ManuActivity.this, "尚未選擇餐點！",Toast.LENGTH_SHORT).show();
        }
        else
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(ManuActivity.this);
            dialog.setTitle("訂單確認");
            dialog.setMessage("經過統計，您選擇的餐點總共是：\n" + Save_Order);
            dialog.setNegativeButton("清除訂單，重新選擇", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface arg0, int arg1)
                {
                    Save_Order = "";
                    Toast.makeText(ManuActivity.this, "已清除", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.setPositiveButton("建立訂單", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface arg0, int arg1)
                {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(ManuActivity.this);
                    Trans_Order = Save_Order;
                    dialog.setMessage("已將您的訂單建立，可至\n\n我的訂單\n\n內查看");
                    dialog.show();
                }
            });
            dialog.setNeutralButton("繼續點餐", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface arg0, int arg1){}
            });
            dialog.show();
        }
    }

    private List getBentolist()
    {
        Bundle bundle = getIntent().getExtras();
        int Id = 0;
        if (bundle != null) {Id = bundle.getInt("ID");}

        List<Bento> Bentolist = new ArrayList<>();
        int i = 1;

        do
            {
                int BentoImageId = getResources().
                        getIdentifier("store" +Id+ "_manu" +i, "drawable", getPackageName());
                int BentoNameId = getResources().
                        getIdentifier("Bento" +Id+ "_Name" +i, "string", getPackageName());
                int BentoPriceId = getResources().
                        getIdentifier("Bento" +Id+ "_Price" +i, "string", getPackageName());
                int BentoConfirm_numberId = getResources().
                        getIdentifier("Confirm_number", "string", getPackageName());
                int BentoConfirmId = getResources().
                        getIdentifier("Add", "string", getPackageName());

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
