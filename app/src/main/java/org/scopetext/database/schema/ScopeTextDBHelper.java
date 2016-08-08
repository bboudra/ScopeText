package org.scopetext.database.schema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.scopetext.database.schema.ScopeTextContract.ScopeTextSchema;

/**
 * Database helper class for the SCOPETEXT table.
 * Created by john.qualls on 8/7/2016.
 */
public class ScopeTextDBHelper {
    // If you change the database schema, you must increment the database version.
    public static final String LOG_TAG = "ScopeTextDBHelper.java";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ScopeText.db";
    private static final String SQL_CREATE_SCOPETEXT_TABLE =
            "CREATE TABLE " + ScopeTextSchema.TABLE_NAME + " (" +
                    ScopeTextSchema._ID + " INTEGER PRIMARY KEY NOT NULL," +
                    ScopeTextSchema.NAME + " VARCHAR(25) NOT NULL," +
                    ScopeTextSchema.MESSAGE_ID + " INTEGER FOREIGN KEY(" +
                    ScopeTextSchema.MESSAGE_ID + ") REFERENCES MESSAGE(" +
                    MessageSchema.MESSAGE_ID + ") " +
                    ScopeTextSchema.RESPONSE_ID + " INTEGER FOREIGN KEY(" +
                    ScopeTextSchema.RESPONSE_ID + ") REFERENCES MESSAGE(" +
                    ResponseSchema.RESPONSE_ID + ") " +
                    ScopeTextSchema.IN_USE + "VARCHAR(1) NOT NULL CHECK(" +
                    ScopeTextSchema.IN_USE + " IN ('Y','N'))\n" +
                    ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ScopeTextSchema.TABLE_NAME;

    public SampleDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, SQL_CREATE_SCOPETEXT_TABLE);
        db.execSQL(SQL_CREATE_SCOPETEXT_TABLE);
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

