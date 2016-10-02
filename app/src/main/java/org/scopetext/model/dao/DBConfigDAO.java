package org.scopetext.model.dao;

import android.database.sqlite.SQLiteDatabase;

import org.scopetext.model.schema.MessageContract.MessageSchema;
import org.scopetext.model.schema.ResponseContract.ResponseSchema;
import org.scopetext.model.schema.ScopeTextContract.ScopeTextSchema;

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
        CREATE_RESPONSE_TABLE = "CREATE TABLE " + ResponseSchema.TABLE_NAME + "(\n\t"
                + ResponseSchema.RESPONSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\t"
                + ResponseSchema.ACTION_APP + " VARCHAR(6) NOT NULL CHECK("
                + ResponseSchema.ACTION_APP + " IS 'CREATE' OR " + ResponseSchema.ACTION_APP
                + " IS 'READ' OR " + ResponseSchema.ACTION_APP + " IS 'UPDATE' OR "
                + ResponseSchema.ACTION_APP + " IS 'DELETE'),\n\t"
                + ResponseSchema.EXTERNAL_APP
                + " VARCHAR(25) NOT NULL CHECK(EXTERNAL_APP IS 'ALARM')\n\t" +
                ");";
        db.execSQL(CREATE_RESPONSE_TABLE);
    }

    /**
     * Creates the Message Table.
     * @param db executes SQL.
     */
    public void createMessageTable(SQLiteDatabase db) {
        CREATE_MESSAGE_TABLE = "CREATE TABLE " + MessageSchema.TABLE_NAME + "(\n\t"
                + MessageSchema.MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\t"
                + MessageSchema.TYPE + " VARCHAR(4) NOT NULL CHECK("
                + MessageSchema.TYPE + " IS 'TEXT'),\n\t"
                + MessageSchema.REG_EXP + " VARCHAR(50) NOT NULL\n\t"
                + ");";
        db.execSQL(CREATE_MESSAGE_TABLE);
    }

    /**
     * Creates the ScopeText Table.
     * @param db executes SQL.
     */
    public void createScopeTextTable(SQLiteDatabase db) {
        CREATE_SCOPETEXT_TABLE = "CREATE TABLE " + ScopeTextSchema.TABLE_NAME + " (\n\t"
                + ScopeTextSchema.SCOPETEXT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\t"
                + MessageSchema.MESSAGE_ID + " INTEGER UNIQUE NOT NULL,\n\t"
                + ResponseSchema.RESPONSE_ID + " INTEGER UNIQUE NOT NULL,\n\t"
                + ScopeTextSchema.NAME + " VARCHAR(50) NOT NULL,\n\t"
                + ScopeTextSchema.IN_USE + "CHARACTER(1) NOT NULL CHECK(" + ScopeTextSchema.IN_USE
                + " IS 'Y' OR " + ScopeTextSchema.IN_USE + " IS 'N'),\n\t"
                + "FOREIGN KEY(" + MessageSchema.MESSAGE_ID + ") REFERENCES MESSAGE("
                + MessageSchema.MESSAGE_ID + "),\n\t"
                + "FOREIGN KEY(" + ResponseSchema.RESPONSE_ID + ") REFERENCES RESPONSE("
                + ResponseSchema.RESPONSE_ID + ")\n\t"
                + ");";
        db.execSQL(CREATE_SCOPETEXT_TABLE);
    }

    protected String getFOREIGN_KEY_ON() {
        return FOREIGN_KEY_ON;
    }

    protected String getCREATE_RESPONSE_TABLE() {
        return CREATE_RESPONSE_TABLE;
    }

    protected void setCREATE_RESPONSE_TABLE(String CREATE_RESPONSE_TABLE) {
        this.CREATE_RESPONSE_TABLE = CREATE_RESPONSE_TABLE;
    }

    protected String getCREATE_MESSAGE_TABLE() {
        return CREATE_MESSAGE_TABLE;
    }

    protected void setCREATE_MESSAGE_TABLE(String CREATE_MESSAGE_TABLE) {
        this.CREATE_MESSAGE_TABLE = CREATE_MESSAGE_TABLE;
    }

    protected String getCREATE_SCOPETEXT_TABLE() {
        return CREATE_SCOPETEXT_TABLE;
    }

    protected void setCREATE_SCOPETEXT_TABLE(String CREATE_SCOPETEXT_TABLE) {
        this.CREATE_SCOPETEXT_TABLE = CREATE_SCOPETEXT_TABLE;
    }
}
