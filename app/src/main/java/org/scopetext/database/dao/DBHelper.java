package org.scopetext.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.scopetext.database.schema.ScopeTextContract.ScopeTextSchema;

/**
 * Handles the lifecycle of the database and encapsulates all DAO's. Invokes specific DAO's for
 * SQL execution based on the database lifecycle call back.
 * Created by john.qualls on 8/7/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final String LOG_TAG = "DBHelper.java";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ScopeText.db";
    public static final String FOREIGN_KEY_ON = "PRAGMA FOREIGN_KEY = ON";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ScopeTextSchema.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        // Initialize tables
        db.execSQL(ScopeTextDAO.SQL_CREATE_TABLE);
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

    /**
     * Enables Foreign Key support
     * @param db The SQLite database.
     */
    public void onConfigure(SQLiteDatabase db) {
    }

    /**
     * Reads all existing ScopeText objects from the database.
     * @return The Result Set containing all of the ScopeTexts.
     */
    public Cursor getAllScopeTexts() {
        return null;
    }
}

