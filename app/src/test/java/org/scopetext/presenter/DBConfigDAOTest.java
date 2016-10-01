package org.scopetext.presenter;

import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;
import org.mockito.Mockito;
import org.scopetext.database.dao.DBConfigDAO;
import org.scopetext.database.schema.ResponseContract.ResponseSchema;

/**
 * Unit tests for DBConfigDAOTest.java.
 * Created by john.qualls on 9/30/2016.
 */
public class DBConfigDAOTest {
    private DBConfigDAO objUnderTest;

    @Test
    public void createResponseTableTest() {
        // Setup
        String CREATE_RESPONSE_TABLE = "CREATE TABLE" + ResponseSchema.TABLE_NAME + "( "
                + ResponseSchema.RESPONSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + ResponseSchema.ACTION_APP + "VARCHAR(6) NOT NULL CHECK("
                + ResponseSchema.ACTION_APP + "IS 'CREATE' OR" + ResponseSchema.ACTION_APP
                + "IS 'READ' OR " + ResponseSchema.ACTION_APP + " IS 'UPDATE' OR "
                + ResponseSchema.ACTION_APP + "IS 'DELETE'),"
                + ResponseSchema.EXTERNAL_APP
                + " VARCHAR(25) NOT NULL CHECK(EXTERNAL_APP IS 'ALARM'));";
        objUnderTest = new DBConfigDAO();
        SQLiteDatabase db = Mockito.mock(SQLiteDatabase.class);
        objUnderTest.createResponseTable(db);

        // Tests
        Mockito.verify(db).execSQL(CREATE_RESPONSE_TABLE);
    }
}
