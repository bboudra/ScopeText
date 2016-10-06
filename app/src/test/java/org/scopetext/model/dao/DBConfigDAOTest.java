package org.scopetext.model.dao;

import android.database.sqlite.SQLiteDatabase;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;
import org.scopetext.model.schema.MessageContract.MessageSchema;
import org.scopetext.model.schema.ResponseContract.ResponseSchema;
import org.scopetext.model.schema.ScopeTextContract.ScopeTextSchema;

/**
 * Unit tests for DBConfigDAOTest.java.
 * Created by john.qualls on 9/30/2016.
 */
public class DBConfigDAOTest {
    private DBConfigDAO objUnderTest;
    private enum TestName {
        CREATE_RESPONSE_TABLE,
        CREATE_MESSAGE_TABLE,
        CREATE_SCOPETEXT_TABLE;
    }

    @Test
    public void createTablesTest() {
        Object[] params = {
                TestName.CREATE_RESPONSE_TABLE,
                "CREATE TABLE " + ResponseSchema.TABLE_NAME + "(\n\t"
                + ResponseSchema.RESPONSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\t"
                + ResponseSchema.ACTION_APP + " VARCHAR(6) NOT NULL CHECK("
                + ResponseSchema.ACTION_APP + " IS 'CREATE' OR " + ResponseSchema.ACTION_APP
                + " IS 'READ' OR " + ResponseSchema.ACTION_APP + " IS 'UPDATE' OR "
                + ResponseSchema.ACTION_APP + " IS 'DELETE'),\n\t"
                + ResponseSchema.EXTERNAL_APP
                + " VARCHAR(25) NOT NULL CHECK(EXTERNAL_APP IS 'ALARM')\n\t" +
                ");",

                TestName.CREATE_MESSAGE_TABLE,
                "CREATE TABLE " + MessageSchema.TABLE_NAME + "(\n\t"
                        + MessageSchema.MESSAGE_ID
                        + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\t"
                        + MessageSchema.TYPE + " VARCHAR(4) NOT NULL CHECK("
                        + MessageSchema.TYPE + " IS 'TEXT'),\n\t"
                        + MessageSchema.REG_EXP + " VARCHAR(50) NOT NULL\n\t"
                        + ");",

                TestName.CREATE_SCOPETEXT_TABLE,
                "CREATE TABLE " + ScopeTextSchema.TABLE_NAME + " (\n\t"
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
                        + ");"};

        for(int i = 0; i < params.length; i += 2)
            createTablesTestHelper((TestName)params[i], (String)params[i + 1]);
    }

    private void createTablesTestHelper(TestName testName, String expectedSQL) {
        // Setup
        objUnderTest = new DBConfigDAO();
        SQLiteDatabase db = Mockito.mock(SQLiteDatabase.class);

        // Test
        String actualSQL = "",
                tableName = "";
        switch(testName) {
            case CREATE_RESPONSE_TABLE:
                objUnderTest.createResponseTable(db);
                actualSQL = objUnderTest.getCreateResponseTable();
                tableName = ResponseSchema.TABLE_NAME;
                break;
            case CREATE_MESSAGE_TABLE:
                objUnderTest.createMessageTable(db);
                actualSQL = objUnderTest.getCreateMessageTable();
                tableName = MessageSchema.TABLE_NAME;
                break;
            case CREATE_SCOPETEXT_TABLE:
                objUnderTest.createScopeTextTable(db);
                actualSQL = objUnderTest.getCreateScopetextTable();
                tableName = ScopeTextSchema.TABLE_NAME;
        }
        Mockito.verify(db).execSQL(actualSQL);
        Assert.assertEquals("SQL CREATE TABLE " + tableName +
                        "... Statement is incorrect.", expectedSQL,
                actualSQL);
    }
}
