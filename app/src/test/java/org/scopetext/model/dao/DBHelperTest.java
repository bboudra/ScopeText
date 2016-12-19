package org.scopetext.model.dao;

import android.database.sqlite.SQLiteDatabase;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.scopetext.view.MainActivity;

/**
 * Unit tests for DBHelper.java
 * Created by john.qualls on 9/30/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class DBHelperTest {
    private DBHelper objUnderTest;
    @Mock
    MainActivity mainActivity;
    @Mock
    SQLiteDatabase db;

    @Before
    public void setup() {
        objUnderTest = new DBHelper(mainActivity);
    }

    @Test
    public void itShouldVerifyNoDBInteractionsForNullDB() {
        try {
            objUnderTest.onCreate(null);
        } catch (NullPointerException e) {
            fail("Null check for SQLiteDatabase is not provided.");
        }
    }

    @Test
    public void itShouldVerifyCorrectTableCreationsDuringOnCreate() {
        objUnderTest.onCreate(db);
        verify(db).execSQL(DBConfigDAO.getCreateMessageTable());
        verify(db).execSQL(DBConfigDAO.getCreateResponseTable());
        verify(db).execSQL(DBConfigDAO.getCreateScoptextTable());
    }

    @Test
    public void itShouldVerifyReadableDatabaseForValidDB() {
        fail("Not Yet Implemented!");
    }

    @Test
    public void itShouldVerifyWriteableDatabaseForValidDB() {
        fail("Not Yet Implemented!");
    }
}
