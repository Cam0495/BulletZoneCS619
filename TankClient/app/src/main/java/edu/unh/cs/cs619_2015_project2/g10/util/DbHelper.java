package edu.unh.cs.cs619_2015_project2.g10.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BBryant12 on 12/1/15.
 */
public class DbHelper extends SQLiteOpenHelper {



    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Replay";

    public static final String TABLE_GRID = "grid";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_GRID = "grid";
    private static final String SQL_CREATE_ENTRIES = "create table "
            + TABLE_GRID + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_GRID
            + " text not null);";

    public static final String DATABASE_NAME = "Replay Database";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GRID);
        onCreate(db);
    }
}


