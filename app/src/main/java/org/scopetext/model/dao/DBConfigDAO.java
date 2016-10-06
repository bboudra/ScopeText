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
    private String createResponseTable;
    private String createMessageTable;
    private String createScopetextTable;

    /**
     * Creates the Response Table.
     * @param db executes SQL.
     */
    public void createResponseTable(SQLiteDatabase db) {
        createResponseTable = "CREATE TABLE " + ResponseSchema.TABLE_NAME + "(\n\t"
                + ResponseSchema.RESPONSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\t"
                + ResponseSchema.ACTION_APP + " VARCHAR(6) NOT NULL CHECK("
                + ResponseSchema.ACTION_APP + " IS 'CREATE' OR " + ResponseSchema.ACTION_APP
                + " IS 'READ' OR " + ResponseSchema.ACTION_APP + " IS 'UPDATE' OR "
                + ResponseSchema.ACTION_APP + " IS 'DELETE'),\n\t"
                + ResponseSchema.EXTERNAL_APP
                + " VARCHAR(25) NOT NULL CHECK(EXTERNAL_APP IS 'ALARM')\n\t" +
                ");";
        db.execSQL(createResponseTable);
        createResponseTable = null;
    }

    /**
     * Creates the Message Table.
     * @param db executes SQL.
     */
    public void createMessageTable(SQLiteDatabase db) {
        createMessageTable = "CREATE TABLE " + MessageSchema.TABLE_NAME + "(\n\t"
                + MessageSchema.MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\t"
                + MessageSchema.TYPE + " VARCHAR(4) NOT NULL CHECK("
                + MessageSchema.TYPE + " IS 'TEXT'),\n\t"
                + MessageSchema.REG_EXP + " VARCHAR(50) NOT NULL\n\t"
                + ");";
        db.execSQL(createMessageTable);
        createMessageTable = null;
    }

    /**
     * Creates the ScopeText Table.
     * @param db executes SQL.
     */
    public void createScopeTextTable(SQLiteDatabase db) {
        createScopetextTable = "CREATE TABLE " + ScopeTextSchema.TABLE_NAME + " (\n\t"
                + ScopeTextSchema.SCOPETEXT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\t"
                + MessageSchema.MESSAGE_ID + " INTEGER UNIQUE NOT NULL,\n\t"
                + ResponseSchema.RESPONSE_ID + " INTEGER UNIQUE NOT NULL,\n\t"
                + ScopeTextSchema.NAME + " VARCHAR(50) UNIQUE NOT NULL,\n\t"
                + ScopeTextSchema.IN_USE + "CHARACTER(1) NOT NULL CHECK(" + ScopeTextSchema.IN_USE
                + " IS 'Y' OR " + ScopeTextSchema.IN_USE + " IS 'N'),\n\t"
                + "FOREIGN KEY(" + MessageSchema.MESSAGE_ID + ") REFERENCES MESSAGE("
                + MessageSchema.MESSAGE_ID + "),\n\t"
                + "FOREIGN KEY(" + ResponseSchema.RESPONSE_ID + ") REFERENCES RESPONSE("
                + ResponseSchema.RESPONSE_ID + ")\n\t"
                + ");";
        db.execSQL(createScopetextTable);
        createScopetextTable = null;
    }

    protected String getFOREIGN_KEY_ON() {
        return FOREIGN_KEY_ON;
    }

    protected String getCreateResponseTable() {
        return createResponseTable;
    }

    protected void setCreateResponseTable(String createResponseTable) {
        this.createResponseTable = createResponseTable;
    }

    protected String getCreateMessageTable() {
        return createMessageTable;
    }

    protected void setCreateMessageTable(String createMessageTable) {
        this.createMessageTable = createMessageTable;
    }

    protected String getCreateScopetextTable() {
        return createScopetextTable;
    }

    protected void setCreateScopetextTable(String createScopetextTable) {
        this.createScopetextTable = createScopetextTable;
    }
}
