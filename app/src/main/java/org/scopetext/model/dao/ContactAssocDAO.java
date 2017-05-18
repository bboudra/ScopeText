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
                "VALUES (0, 0);",
        sql2 = "INSERT INTO CONTACT_ASSOCIATION (SCOPETEXT_ID, CONTACT_ID)\n " +
                "VALUES (0, 1);",
        sql3 = "INSERT INTO CONTACT_ASSOCIATION (SCOPETEXT_ID, CONTACT_ID)\n " +
                "VALUES (1, 2);";
        db.execSQL(sql);
        db.execSQL(sql2);
        db.execSQL(sql3);
    }

    // TODO Refactor once insert functionality is required
    /*
     * Currently just used for testing.
     */
    public static void delete(SQLiteDatabase db) {
        db.delete("CONTACT_ASSOCIATION", null, null);
    }
}
