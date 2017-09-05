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
    public static final String COLUMN_ISSUE1 = "ISSUE1";
    public static final String COLUMN_ISSUE2 = "ISSUE2";
    public static final String COLUMN_ISSUE3 = "ISSUE3";

    public static final String DATABASE_NAME = "PollingResults.db";
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_SEX
            + " text not null, " + COLUMN_AGE
            + " integer not null, " + COLUMN_OCCUPATION
            + " integer not null, " + COLUMN_ISSUE1
            + " integer not null, " + COLUMN_ISSUE2
            + " integer not null, " + COLUMN_ISSUE3
            + " text not null" + ");";

    public static final String MIND_MAP_DB_NAME = "MindMap.db";
    public static final String MIND_MAP_TABLE_NAME = "MIND_MAP";
    public static final String ISSUE_NAME = "ISSUE NAME";
    public static final String START_TIME = "START TIME";
    public static final String STOP_TIME = "STOP TIME";
    public static final String IS_MAIN_TOPIC = " IS MAIN TOPIC";

    public static final String MIND_MAP_DB_CREATE = "create table "
            + MIND_MAP_TABLE_NAME + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + ISSUE_NAME
            + " text not null, " + START_TIME
            + " text not null, " + STOP_TIME
            + " text not null, " + IS_MAIN_TOPIC
            + " text not null" + ");";

}
