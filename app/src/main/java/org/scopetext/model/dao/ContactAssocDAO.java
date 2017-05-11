package org.scopetext.model.dao;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by john.qualls on 5/10/2017.
 */
public class ContactAssocDAO {
    // TODO Refactor once insert functionality is required
    /*
     * Currently just used for testing.
     */
    public static void insertContactAssoc(SQLiteDatabase db) {
        final String sql = "INSERT INTO CONTACT_ASSOCIATION (SCOPETEXT_ID, CONTACT_ID)\n " +
                "VALUES (0, 0);";
        db.execSQL(sql);
    }

    // TODO Refactor once insert functionality is required
    /*
     * Currently just used for testing.
     */
    public static void delete(SQLiteDatabase db) {
        db.delete("CONTACT_ASSOCIATION", null, null);
    }
}
