package com.example.rohit.kathaproject.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.rohit.kathaproject.constants.DbConstants;
import com.example.rohit.kathaproject.helpers.MindMapDataHelper;
import com.example.rohit.kathaproject.helpers.PollingDataHelper;

/**
 * Created by Rohit on 13-07-2017.
 */

public class MindMapCRUD {

    private SQLiteDatabase database;
    private MindMapDataHelper dbHelper;
    private String[] allColumns = { DbConstants.COLUMN_ID,
            DbConstants.ISSUE_NAME, DbConstants.START_TIME, DbConstants.STOP_TIME, DbConstants.IS_MAIN_TOPIC };

    public MindMapCRUD(Context context) {
        dbHelper = new MindMapDataHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

}
