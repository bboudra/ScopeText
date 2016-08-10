package org.scopetext.org.scopetext.database.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.scopetext.database.schema.ScopeTextContract.ScopeTextSchema;

/**
 * Handles the lifecycle of the database.
 * Created by john.qualls on 8/7/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final String LOG_TAG = "DBHelper.java";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ScopeText.db";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ScopeTextSchema.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        // Initialize tables
/*        Log.d(LOG_TAG, ScopeTextSchema.SQL_CREATE_TABLE);
        db.execSQL(ScopeTextSchema.SQL_CREATE_TABLE);*/
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}

