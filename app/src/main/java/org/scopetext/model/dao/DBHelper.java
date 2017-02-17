package org.scopetext.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Handles the lifecycle of the database and encapsulates all DAO's. Invokes specific DAO's for SQL
 * execution based on the database lifecycle call back.
 * <pre>
 * NOTES:
 * 1. Not meant to be subclassed.
 * 2. Classes that call getWriteableDatabase() need to make sure that access to the database is
 * thread safe.
 * </pre>
 * Created by john.qualls on 8/7/2016.
 */
public class DBHelper extends SQLiteOpenHelper implements DatabaseProvider {
    private static final String DATABASE_NAME = "ScopeText.db";
    private static int DATABASE_VERSION = 1;
    public final String LOG_TAG = "DBHelper.java";

    public DBHelper(Context context) throws SQLiteException {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        // Initialize tables
        if (db != null) {
            DBConfigDAO.createResponseTable(db);
            DBConfigDAO.createMessageTable(db);
            DBConfigDAO.createScopeTextTable(db);
            DBConfigDAO.createContactTable(db);
            DBConfigDAO.createContactAssocTable(db);
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        onOpenHelper(db);
    }

    /*
     * Needed for unit testing to avoid interacting with Android API super.onOpen() call.
     */
    void onOpenHelper(SQLiteDatabase db) {
        if (!db.isReadOnly()) {
            db.execSQL(DBConfigDAO.FOREIGN_KEY_ON);
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * Enables Foreign Key support
     *
     * @param db The SQLite database.
     */
    public void onConfigure(SQLiteDatabase db) {
    }

    @Override
    public SQLiteDatabase getWriteableDB() throws SQLiteException {
        return getWritableDatabase();
    }
}

