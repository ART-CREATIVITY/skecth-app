package io.artcreativity.sketchapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import io.artcreativity.sketchapp.models.Product;
import io.artcreativity.sketchapp.models.Profile;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "sketch_app.db";
    private static final int DB_VERSION = 1;
    private static final String TAG = "DataBaseHelper";

    private static DataBaseHelper dataBaseHelper;

    private DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public static DataBaseHelper getInstance(Context context) {
        if(dataBaseHelper==null){
            dataBaseHelper = new DataBaseHelper(context);
        }
        return dataBaseHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Product.CREATE_TABLE);
        sqLiteDatabase.execSQL(Profile.CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}

