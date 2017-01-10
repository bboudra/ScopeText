package org.scopetext.model.dao;

import static org.junit.Assert.*;
import android.database.sqlite.SQLiteDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.scopetext.model.schema.ContactContract.ContactSchema;
import org.scopetext.model.schema.ScopeTextContract.ScopeTextSchema;
import org.scopetext.model.schema.MessageContract.MessageSchema;
import org.scopetext.model.schema.ResponseContract.ResponseSchema;

/**
 * Unit tests for DBConfigDAOTest.java.
 * Created by john.qualls on 9/30/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class DBConfigDAOTest {
    @Mock
    SQLiteDatabase db;

    @Test
    public void itShouldVerifyScopeTextCreateTableSQL() {
        // Setup
        String expectedSQL = "CREATE TABLE " + ScopeTextSchema.TABLE_NAME + " (\n\t" +
                ScopeTextSchema.SCOPETEXT_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\t" +
                MessageSchema.MESSAGE_ID + " INTEGER UNIQUE NOT NULL,\n\t" +
                ResponseSchema.RESPONSE_ID + " INTEGER UNIQUE NOT NULL,\n\t" +
                ScopeTextSchema.NAME + " VARCHAR(50) UNIQUE NOT NULL,\n\t" +
                ScopeTextSchema.IN_USE + "CHARACTER(1) NOT NULL CHECK(" +
                ScopeTextSchema.IN_USE + " IS 'Y' OR " + ScopeTextSchema.IN_USE +
                " IS 'N'),\n\t" + "FOREIGN KEY(" + MessageSchema.MESSAGE_ID +
                ") REFERENCES MESSAGE(" + MessageSchema.MESSAGE_ID + "),\n\t" +
                "FOREIGN KEY(" + ResponseSchema.RESPONSE_ID + ") REFERENCES RESPONSE(" +
                ResponseSchema.RESPONSE_ID + ")\n\t" + ");";

        // Test
        DBConfigDAO.createScopeTextTable(db);
        Mockito.verify(db).execSQL(expectedSQL);
    }

    @Test
    public void itShouldVerifyMessageCreateTableSQL() {
        // Setup
        String expectedSQL = "CREATE TABLE " + MessageSchema.TABLE_NAME + "(\n\t" + MessageSchema.MESSAGE_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\t" + MessageSchema.TYPE +
                " VARCHAR(4) NOT NULL CHECK(" + MessageSchema.TYPE + " IS 'TEXT'),\n\t" +
                MessageSchema.REG_EXP + " VARCHAR(50) NOT NULL\n\t" + ");";

        // Test
        DBConfigDAO.createMessageTable(db);
        Mockito.verify(db).execSQL(expectedSQL);
    }

    @Test
    public void itShouldVerifyResponseCreateTableSQL() {
        // Setup
        String expectedSQL = "CREATE TABLE " + ResponseSchema.TABLE_NAME + "(\n\t" + ResponseSchema.RESPONSE_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\t" +
                ResponseSchema.ACTION_APP + " VARCHAR(6) NOT NULL CHECK(" +
                ResponseSchema.ACTION_APP + " IS 'CREATE' OR " + ResponseSchema.ACTION_APP +
                " IS 'READ' OR " + ResponseSchema.ACTION_APP + " IS 'UPDATE' OR " +
                ResponseSchema.ACTION_APP + " IS 'DELETE'),\n\t" +
                ResponseSchema.EXTERNAL_APP +
                " VARCHAR(25) NOT NULL CHECK(EXTERNAL_APP IS 'ALARM')\n\t" +
                ");";

        // Test
        DBConfigDAO.createResponseTable(db);
        Mockito.verify(db).execSQL(expectedSQL);
    }

    @Test
    public void itShouldVerifyContactCreateTableSQL(){
        // Setup
        String expectedSQL = "CREATE TABLE " + ContactSchema.TABLE_NAME + " (\n\t" +
                ContactSchema.ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\t" +
                ScopeTextSchema.NAME + " VARCHAR(50) NOT NULL" + ");";

        // Test
        DBConfigDAO.createContactTable(db);
        Mockito.verify(db).execSQL(expectedSQL);
    }
}
