package org.scopetext.model.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.scopetext.presenter.Presenter;

import static org.junit.Assert.fail;

/**
 * Unit tests for SQLTask.java Created by john.qualls on 12/21/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class SQLTaskTest {
    private final static String ILLEGAL_ARG_MSG =
            "The expected IllegalArgumentException was never thrown";
    private SQLTask objUnderTest;
    @Mock private DBHelper dbHelper;
    @Mock
    SQLiteDatabase db;
    @Mock
    Presenter presenter;

    @Before
    public void before() {
        objUnderTest = new SQLTask(presenter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWithNoParameters() {
        objUnderTest.doInBackground();
        fail(ILLEGAL_ARG_MSG);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWithOneParameter() {
        objUnderTest.doInBackground("param");
        fail(ILLEGAL_ARG_MSG);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWith3Parameters() {
        objUnderTest.doInBackground("param", "param1", "param2");
        fail(ILLEGAL_ARG_MSG);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWithNullDBHelperParam() {
        objUnderTest.doInBackground(null, "param");
        fail(ILLEGAL_ARG_MSG);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWithNullSQLParam() {
        objUnderTest.doInBackground("param", null);
        fail(ILLEGAL_ARG_MSG);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWithNoDBHelperParam() {
        objUnderTest.doInBackground("param", "param1");
        fail(ILLEGAL_ARG_MSG);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWithNoSQLParam() {
        objUnderTest.doInBackground(dbHelper, "param1");
        fail(ILLEGAL_ARG_MSG);
    }


    // TODO Finish test
/*    @Test
    public void itShouldVerifySelectAllScopeTextsAndContacts() {
        // Mock setup
        Cursor cursor = mock(Cursor.class);
        when(dbHelper.getReadableDatabase()).thenReturn(db);
        when(db.rawQuery(isA(String.class), isNull())).thenReturn(cursor);
        when(objUnderTest.getAllScopeTextsAndContacts(isA(SQLiteDatabase.class))).thenReturn(null);

        // Test
        objUnderTest.doInBackground(dbHelper, SQL.SELECT_ALL_SCOPETEXTS_CONTACTS);
        verify(objUnderTest).getAllScopeTextsAndContacts(db);
    }*/

}
