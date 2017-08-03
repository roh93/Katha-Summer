package com.example.rohit.kathaproject.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rohit.kathaproject.constants.DbConstants;

import static com.example.rohit.kathaproject.constants.DbConstants.DATABASE_VERSION;
import static com.example.rohit.kathaproject.constants.DbConstants.MIND_MAP_DB_NAME;

/**
 * Created by Rohit on 13-07-2017.
 */

public class MindMapDataHelper extends SQLiteOpenHelper {

    public MindMapDataHelper(Context context){
        super(context, MIND_MAP_DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbConstants.MIND_MAP_DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.DATABASE_VERSION);
        onCreate(db);
    }
}
