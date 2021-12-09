package com.example.powerlaptoplist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LaptopDBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "laptops.db";
    private static int DATABASE_VERSION = 1;

    public LaptopDBHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCommand = "create table laptop (_id integer primary key autoincrement, "
                + "laptopname text not null, "
                + "windowsversion text, "
                + "makemodel text, "
                + "processor text);";
        sqLiteDatabase.execSQL(sqlCommand);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old, int newVer) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS laptop");
        onCreate(sqLiteDatabase);
    }
}
