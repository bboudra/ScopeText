package org.scopetext.model.dao;

import android.database.sqlite.SQLiteDatabase;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Assert;
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
        objUnderTest.onCreate(db);
        verify(db).execSQL(DBConfigDAO.getCreateMessageTable());
        verify(db).execSQL(DBConfigDAO.getCreateResponseTable());
        verify(db).execSQL(DBConfigDAO.getCreateScoptextTable());
    }

    @Test
    public void itShouldAssertValidWriteableDB() {
        // Mock setup
        objUnderTest = spy(new DBHelper(mainActivity));
        doReturn(db).when(objUnderTest).getWritableDatabase();

        // Test
        assertEquals(db, objUnderTest.getWriteableDB());
    }

    @Test(expected = SQLiteException.class)
    public void itShouldAssertExceptionForInvalidWriteableDB() {
        // Mock setup
        objUnderTest = spy(new DBHelper(mainActivity));
        doThrow(new SQLiteException()).when(objUnderTest).getWritableDatabase();

        // Test
        assertEquals(db, objUnderTest.getWriteableDB());
        fail("Expected SQLiteException, but no Exception was thrown.");
    }

    @Test
    public void itShouldNotExecuteFKSetupSQLWhenDBIsReadOnly() {
        when(db.isReadOnly()).thenReturn(true);
        objUnderTest.onOpenHelper(db);
        verify(db, times(0)).execSQL(isA(String.class));
    }

    @Test
    public void itShouldExecuteFKSetupSQLWhenDBIsNotReadOnly() {
        objUnderTest.onOpenHelper(db);
        verify(db).execSQL(isA(String.class));
    }
}
