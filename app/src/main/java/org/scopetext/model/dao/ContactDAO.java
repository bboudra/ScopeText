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
        final String sql = "INSERT INTO CONTACT (CONTACT_ID, NAME) VALUES (0, 'Contact1')",
        sql2 = "INSERT INTO CONTACT (CONTACT_ID, NAME) VALUES (1, 'Contact2')",
        sql3 = "INSERT INTO CONTACT (CONTACT_ID, NAME) VALUES (2, 'Contact3')";
        db.execSQL(sql);
        db.execSQL(sql2);
        db.execSQL(sql3);
    }

    // TODO Refactor once insert functionality is required
    /*
     * Currently just used for testing.
     */
    public static void delete(SQLiteDatabase db) {
        db.delete("CONTACT", null, null);
    }
}
