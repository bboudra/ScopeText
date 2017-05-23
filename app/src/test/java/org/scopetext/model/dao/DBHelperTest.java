package org.scopetext.model.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.scopetext.view.MainActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        verify(db).execSQL(DBConfigDAO.getCreateContactTable());
        verify(db).execSQL(DBConfigDAO.getCreateContactAssocTable());
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
