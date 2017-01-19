package org.scopetext.model.dao;

import android.database.sqlite.SQLiteDatabase;

import org.scopetext.model.schema.ContactAssocContract.ContactAssocSchema;
import org.scopetext.model.schema.ContactContract.ContactSchema;
import org.scopetext.model.schema.MessageContract.MessageSchema;
import org.scopetext.model.schema.ResponseContract.ResponseSchema;
import org.scopetext.model.schema.ScopeTextContract.ScopeTextSchema;

/**
 * Executes database configuration SQL. Created by john.qualls on 9/30/2016.
 */
public class DBConfigDAO {
    public static final String FOREIGN_KEY_ON = "PRAGMA FOREIGN_KEY = ON";
    private static final String CREATE_RESPONSE_TABLE =
            "CREATE TABLE " + ResponseSchema.TABLE_NAME + "(\n\t" + ResponseSchema.RESPONSE_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\t" +
                    ResponseSchema.ACTION_APP + " VARCHAR(6) NOT NULL CHECK(" +
                    ResponseSchema.ACTION_APP + " IS 'CREATE' OR " + ResponseSchema.ACTION_APP +
                    " IS 'READ' OR " + ResponseSchema.ACTION_APP + " IS 'UPDATE' OR " +
                    ResponseSchema.ACTION_APP + " IS 'DELETE'),\n\t" +
                    ResponseSchema.EXTERNAL_APP +
                    " VARCHAR(25) NOT NULL CHECK(EXTERNAL_APP IS 'ALARM')\n\t" +
                    ");";
    private static final String CREATE_MESSAGE_TABLE =
            "CREATE TABLE " + MessageSchema.TABLE_NAME + "(\n\t" + MessageSchema.MESSAGE_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\t" + MessageSchema.TYPE +
                    " VARCHAR(4) NOT NULL CHECK(" + MessageSchema.TYPE + " IS 'TEXT'),\n\t" +
                    MessageSchema.REG_EXP + " VARCHAR(50) NOT NULL\n\t" + ");";
    private static final String CREATE_SCOPETEXT_TABLE =
            "CREATE TABLE " + ScopeTextSchema.TABLE_NAME + " (\n\t" +
                    ScopeTextSchema.SCOPETEXT_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\t" +
                    MessageSchema.MESSAGE_ID + " INTEGER UNIQUE,\n\t" +
                    ResponseSchema.RESPONSE_ID + " INTEGER UNIQUE,\n\t" +
                    ScopeTextSchema.NAME + " VARCHAR(50) UNIQUE NOT NULL,\n\t" +
                    ScopeTextSchema.IN_USE + "CHARACTER(1) NOT NULL CHECK(" +
                    ScopeTextSchema.IN_USE + " IS 'Y' OR " + ScopeTextSchema.IN_USE +
                    " IS 'N'),\n\t" + "FOREIGN KEY(" + ScopeTextSchema.MESSAGE_ID +
                    ") REFERENCES " + MessageSchema.TABLE_NAME + "(" + ScopeTextSchema.MESSAGE_ID +
                    ") ON DELETE CASCADE,\n\t" + "FOREIGN KEY(" + ScopeTextSchema.RESPONSE_ID +
                    ") REFERENCES " + ResponseSchema.TABLE_NAME + " (" +
                    ResponseSchema.RESPONSE_ID + ") ON DELETE CASCADE);";
    private static final String CREATE_CONTACT_TABLE =
            "CREATE TABLE " + ContactSchema.TABLE_NAME + " (\n\t" +
                    ContactSchema.CONTACT_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\t" +
                    ContactSchema.NAME + " VARCHAR(50) NOT NULL" + ");";
    private static final String CREATE_CONTACT_ASSOC_TABLE =
            "CREATE TABLE " + ContactAssocSchema.TABLE_NAME + " (\n\t" +
                    ContactAssocSchema.CONTACT_ASSOC_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\t" +
                    ContactAssocSchema.SCOPETEXT_ID + " INTEGER," + "\n\t" +
                    ContactAssocSchema.CONTACT_ID + " INTEGER,\n\t" +
                    "FOREIGN KEY(" + ContactAssocSchema.SCOPETEXT_ID + ") REFERENCES " +
                    ScopeTextSchema.TABLE_NAME + "(" + ScopeTextSchema.SCOPETEXT_ID +
                    ") ON DELETE CASCADE,\n\t" + "FOREIGN KEY(" + ContactAssocSchema.CONTACT_ID +
                    ")" + " REFERENCES " + ContactSchema.TABLE_NAME + "(" + ContactSchema.CONTACT_ID +
                    ") " + "ON DELETE CASCADE);";
    /**
     * Creates the Response Table.
     *
     * @param db executes SQL.
     */
    public static void createResponseTable(SQLiteDatabase db) {
        db.execSQL(CREATE_RESPONSE_TABLE);
    }

    /**
     * Creates the Message Table.
     *
     * @param db executes SQL.
     */
    public static void createMessageTable(SQLiteDatabase db) {
        db.execSQL(CREATE_MESSAGE_TABLE);
    }

    /**
     * Creates the ScopeText Table.
     *
     * @param db executes SQL.
     */
    public static void createScopeTextTable(SQLiteDatabase db) {
        db.execSQL(CREATE_SCOPETEXT_TABLE);
    }

    /**
     * Creates the Contact Table.
     *
     * @param db executes SQL.
     */
    public static void createContactTable(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACT_TABLE);
    }

    /**
     * Creates the ContactAssoc Table.
     *
     * @param db executes SQL.
     */
    public static void createContactAssocTable(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACT_ASSOC_TABLE);
    }

    /*
     * Unit test getters.
     */
    public static String getCreateResponseTable() {
        return CREATE_RESPONSE_TABLE;
    }

    public static String getCreateMessageTable() {
        return CREATE_MESSAGE_TABLE;
    }

    public static String getCreateScoptextTable() {
        return CREATE_SCOPETEXT_TABLE;
    }
}
