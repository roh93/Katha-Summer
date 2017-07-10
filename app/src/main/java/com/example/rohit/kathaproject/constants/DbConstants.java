package com.example.rohit.kathaproject.constants;

/**
 * Created by Rohit on 22-06-2017.
 */

public class DbConstants {

    public static final String TABLE_NAME = "POLL_RESULTS";
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_SEX = "SEX";
    public static final String COLUMN_AGE = "AGE";
    public static final String COLUMN_OCCUPATION = "OCCUPATION";
    public static final String COLUMN_ISSUE = "ISSUE";

    public static final String DATABASE_NAME = "PollingResults.db";
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_SEX
            + " text not null, " + COLUMN_AGE
            + " integer not null, " + COLUMN_OCCUPATION
            + " integer not null, " + COLUMN_ISSUE
            + " text not null" + ");";

}
