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
        final String sql = "INSERT INTO CONTACT (NAME) VALUES ('Contact1')";
        try {
            db.rawQuery(sql, null);
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    // TODO Refactor once insert functionality is required
    /*
     * Currently just used for testing.
     */
    public static void delete(SQLiteDatabase db) {
        final String sql = "DELETE FROM CONTACT";
        try {
            db.rawQuery(sql, null);
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }
}
