package org.scopetext.database.dao;

import android.database.sqlite.SQLiteDatabase;

import org.scopetext.database.schema.ResponseContract.ResponseSchema;

/**
 * Executes database configuration SQL.
 * Created by john.qualls on 9/30/2016.
 */
public class DBConfigDAO {
    public final String FOREIGN_KEY_ON = "PRAGMA FOREIGN_KEY = ON";
    private String CREATE_RESPONSE_TABLE;
    private String CREATE_MESSAGE_TABLE;
    private String CREATE_SCOPETEXT_TABLE;

    /**
     * Creates the Response Table.
     * @param db executes SQL.
     */
    public void createResponseTable(SQLiteDatabase db) {
        CREATE_RESPONSE_TABLE = "CREATE TABLE" + ResponseSchema.TABLE_NAME + "( "
                + ResponseSchema.RESPONSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + ResponseSchema.ACTION_APP + "VARCHAR(6) NOT NULL CHECK("
                + ResponseSchema.ACTION_APP + "IS 'CREATE' OR" + ResponseSchema.ACTION_APP
                + "IS 'READ' OR " + ResponseSchema.ACTION_APP + " IS 'UPDATE' OR "
                + ResponseSchema.ACTION_APP + "IS 'DELETE'),"
                + ResponseSchema.EXTERNAL_APP
                + " VARCHAR(25) NOT NULL CHECK(EXTERNAL_APP IS 'ALARM'));";
        db.execSQL(CREATE_RESPONSE_TABLE);
    }
}
