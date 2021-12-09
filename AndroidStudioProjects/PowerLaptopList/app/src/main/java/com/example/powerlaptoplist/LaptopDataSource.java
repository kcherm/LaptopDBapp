package com.example.powerlaptoplist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class LaptopDataSource {

    private SQLiteDatabase database;
    private LaptopDBHelper dbHelper;

    public LaptopDataSource(Context context){
        dbHelper = new LaptopDBHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public boolean insertLaptop(Laptop c){
        boolean didSucceed = false;
        try {
            ContentValues values = new ContentValues();
            values.put("laptopname", c.getName());
            values.put("windowsversion", c.getwinVersion());
            values.put("makemodel", c.getmakeModel());
            values.put("processor", c.getProcessor());

            didSucceed = database.insert("laptop", null, values) >0;

        } catch (Exception e){

        }
        return didSucceed;
    }

    public boolean updateLaptop(Laptop c){
        boolean didSucceed = false;
        try {
            ContentValues values = new ContentValues();
            values.put("laptopname", c.getName());
            values.put("windowsversion", c.getwinVersion());
            values.put("makemodel", c.getmakeModel());
            values.put("processor", c.getProcessor());

            Long id = (long) c.getLaptopID();
            didSucceed = database.update("laptop", values, "_id=" +id, null) >0;

        } catch (Exception e){

        }
        return didSucceed;
    }

    public int getLastLaptopID(){
        int lastID = -1;
        try {
            String query = "Select MAX(_id) from laptop";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            lastID = cursor.getInt(0);
            cursor.close();
        }catch (Exception e){

        }
        return lastID;
    }

    public ArrayList<String> getLaptopNames(){
        ArrayList<String> names = new ArrayList<String>();
        try {
            String query = "Select laptopname from laptop";
            Cursor cursor = database.rawQuery(query,null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                names.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
        }catch (Exception e){

        }
        return names;
    }

    public ArrayList<Laptop> getLaptops(){
        ArrayList<Laptop> laptops = new ArrayList<Laptop>();
        try {
            String query = "Select * from laptop";
            Cursor cursor = database.rawQuery(query,null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Laptop c = new Laptop();
                c.setName(cursor.getString(1));
                c.setwinVersion(cursor.getString(2));
                c.setmakeModel(cursor.getString(3));
                laptops.add(c);
                cursor.moveToNext();
            }
            cursor.close();
        }catch (Exception e){

        }
        return laptops;
    }

    public Laptop getLaptop(int id){
        Laptop c = new Laptop();
        try {
            String query = "Select * from laptop where _id="+id;
            Cursor cursor = database.rawQuery(query,null);
            cursor.moveToFirst();
            c.setLaptopID(id);
            c.setName(cursor.getString(1));
            c.setwinVersion(cursor.getString(2));
            c.setmakeModel(cursor.getString(3));
            cursor.close();
        }catch (Exception e){

        }
        return c;
    }

}
