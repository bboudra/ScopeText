package org.scopetext.model.dao;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by john.qualls on 5/10/2017.
 */

public class ContactDAO {
    // TODO Refactor once insert functionality is required
    /*
     * Currently just used for testing.
     */
    public static void insertContact(SQLiteDatabase db) {
        final String sql = "INSERT INTO CONTACT (CONTACT_ID, NAME) VALUES (0, 'Contact1')";
        db.execSQL(sql);
    }

    // TODO Refactor once insert functionality is required
    /*
     * Currently just used for testing.
     */
    public static void delete(SQLiteDatabase db) {
        db.delete("CONTACT", null, null);
    }
}
