package com.example.rohit.kathaproject.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rohit.kathaproject.constants.DbConstants;

import static com.example.rohit.kathaproject.constants.DbConstants.DATABASE_NAME;
import static com.example.rohit.kathaproject.constants.DbConstants.DATABASE_VERSION;

/**
 * Created by Rohit on 22-06-2017.
 */

public class PollingDataHelper extends SQLiteOpenHelper {

    public PollingDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbConstants.DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.DATABASE_VERSION);
        onCreate(db);
    }
}
