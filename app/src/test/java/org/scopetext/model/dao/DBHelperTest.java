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
import android.database.sqlite.SQLiteException;

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
    @Mock
    SQLiteDatabase db2;

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
        // Mock setup
        objUnderTest = spy(new DBHelper(mainActivity));
        doReturn(db).when(objUnderTest).getWritableDatabase();

        // Test
        objUnderTest.onCreate(db);
        verify(db).execSQL(DBConfigDAO.getCreateMessageTable());
        verify(db).execSQL(DBConfigDAO.getCreateResponseTable());
        verify(db).execSQL(DBConfigDAO.getCreateScoptextTable());
        assertEquals(db, objUnderTest.getWriteableDB());
    }

    @Test
    public void itShouldAssertDBForValidInstantiation() {
        // Mock setup
        objUnderTest = spy(new DBHelper(mainActivity));
        doReturn(db).when(objUnderTest).getWritableDatabase();
    }
}
