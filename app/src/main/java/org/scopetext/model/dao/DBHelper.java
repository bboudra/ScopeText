package org.scopetext.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Handles the lifecycle of the database and encapsulates all DAO's. Invokes specific DAO's for
 * SQL execution based on the database lifecycle call back.
 * Created by john.qualls on 8/7/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ScopeText.db";
    private static int DATABASE_VERSION = 1;
    public final String LOG_TAG = "DBHelper.java";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        // Initialize tables
        DBConfigDAO.createResponseTable(db);
        DBConfigDAO.createMessageTable(db);
        DBConfigDAO.createScopeTextTable(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    /**
     * Enables Foreign Key support
     * @param db The SQLite database.
     */
    public void onConfigure(SQLiteDatabase db) {
    }
}

