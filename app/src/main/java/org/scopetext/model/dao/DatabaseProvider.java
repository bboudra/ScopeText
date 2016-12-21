package org.scopetext.model.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DatabaseProvider provides access to ScopeText's SQLiteDatabase. Created by john.qualls on
 * 12/20/2016.
 */

public interface DatabaseProvider {
    /**
     * @see SQLiteOpenHelper#getWritableDatabase()
     */
    public SQLiteDatabase getWriteableDB();
}