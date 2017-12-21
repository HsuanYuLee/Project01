package com.example.user.project01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2017/12/21.
 */

public class MyDBHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "Store_data";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Store";
    private static final String COL_id = "id";
    private static final String COL_name = "name";
    private static final String COL_phone = "phone";
    private static final String COL_address = "address";
    private static final String COL_time = "time";
    private static final String COL_image = "image";

    private static final String STORE_TABLE_CREATE =
            "CREATE TABLE" + TABLE_NAME + "(" +
                    COL_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_name + " TEXT NOT NULL, " +
                    COL_phone + " TEXT, " +
                    COL_address + " TEXT, " +
                    COL_time + " TEXT, " +
                    COL_image + " BLOB ); ";

    public MyDBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(STORE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<Store> getAllStores()
    {
        SQLiteDatabase db = getReadableDatabase();

        String[] columns =
                {
                        COL_id,
                        COL_name,
                        COL_phone,
                        COL_address,
                        COL_time,
                        COL_image
                };

        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        List<Store> storeList = new ArrayList<>();

        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String address = cursor.getString(3);
            String time = cursor.getString(4);
            byte[] image = cursor.getBlob(5);

            Store store = new Store(id, name, phone, address, time, image);
            storeList.add(store);
        }

        cursor.close();
        return storeList;
    }

    public Store findById(int id)
    {
        SQLiteDatabase db = getWritableDatabase();

        String[] columns =
                {
                        COL_name,
                        COL_phone,
                        COL_address,
                        COL_time,
                        COL_image,
                };

        String selection = COL_id + " = ?;";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        Store store = null;

        if(cursor.moveToNext())
        {
            String name = cursor.getString(0);
            String phone = cursor.getString(1);
            String address = cursor.getString(2);
            String time = cursor.getString(3);
            byte[] image = cursor.getBlob(4);

            store = new Store(id, name, phone, address, time, image);
        }

        cursor.close();
        return store;
    }

    public long insert(Store store)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_name, store.getName());
        values.put(COL_phone, store.getPhone());
        values.put(COL_address, store.getAddress());
        values.put(COL_time,store.gettime());
        values.put(COL_image,store.getImage());

        return db.insert(TABLE_NAME, null, values);
    }

    public int update(Store store)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_name, store.getName());
        values.put(COL_phone, store.getPhone());
        values.put(COL_address, store.getAddress());
        values.put(COL_time,store.gettime());
        values.put(COL_image,store.getImage());

        String whereClause = COL_id + " = ?;";
        String[] whereArgs = {Integer.toString(store.getId())};

        return db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    public int deleteById(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = COL_id + " = ?;";
        String[] whereArgs = {String.valueOf(id)};

        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }
}
