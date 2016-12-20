package org.scopetext.model;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DatabaseProvider provides access to ScopeText's SQLiteDatabase.
 * Created by john.qualls on 12/20/2016.
 */

public interface DatabaseProvider {
    /**
     * @see SQLiteOpenHelper#getReadableDatabase()
     */
    public SQLiteDatabase getReadableDB();

    /**
     * @see SQLiteOpenHelper#getWritableDatabase()
     */
    public SQLiteDatabase getWriteableDB();
}
