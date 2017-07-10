package com.example.rohit.kathaproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.rohit.kathaproject.constants.DbConstants;
import com.example.rohit.kathaproject.helpers.PollingDataHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Rohit on 22-06-2017.
 */

public class PollingResultsCRUD {

    private SQLiteDatabase database;
    private PollingDataHelper dbHelper;
    private String[] allColumns = { DbConstants.COLUMN_ID,
            DbConstants.COLUMN_SEX, DbConstants.COLUMN_AGE, DbConstants.COLUMN_OCCUPATION, DbConstants.COLUMN_ISSUE };

    public PollingResultsCRUD(Context context) {
        dbHelper = new PollingDataHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertPollDetail(PollingDetail pollingObject) {

        ContentValues values = new ContentValues();
        values.put(DbConstants.COLUMN_SEX, pollingObject.getSex());
        values.put(DbConstants.COLUMN_AGE, pollingObject.getAge());
        values.put(DbConstants.COLUMN_OCCUPATION, pollingObject.getOccupation());
        values.put(DbConstants.COLUMN_ISSUE, pollingObject.getIssueName());

        return database.insert(DbConstants.TABLE_NAME, null, values);
    }


    public List<String> getPolledIssueListBySex(String sex) {
        List<String> polledIssueList = new ArrayList<>();
        Cursor cursor = database.query(DbConstants.TABLE_NAME
                ,new String[]{DbConstants.COLUMN_ISSUE}
                , DbConstants.COLUMN_SEX + " = ? "
                ,new String[]{sex} , null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            polledIssueList.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return polledIssueList;
    }

    public List<String> getPolledIssueListByAge(int lowerAge, int upperAge) {
        List<String> polledIssueList = new ArrayList<>();
        Cursor cursor = database.query(DbConstants.TABLE_NAME
                ,new String[]{DbConstants.COLUMN_ISSUE}
                , DbConstants.COLUMN_AGE + " > ? and " + DbConstants.COLUMN_AGE + " < ?"
                ,new String[]{Integer.toString(lowerAge),Integer.toString(upperAge)} , null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            polledIssueList.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return polledIssueList;
    }

    public List<String> getPolledIssueListByCaste(String occupation) {
        List<String> polledIssueList = new ArrayList<>();
        Cursor cursor = database.query(DbConstants.TABLE_NAME
                ,new String[]{DbConstants.COLUMN_ISSUE}
                , DbConstants.COLUMN_OCCUPATION + " = ? "
                ,new String[]{occupation} , null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            polledIssueList.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return polledIssueList;
    }

}
