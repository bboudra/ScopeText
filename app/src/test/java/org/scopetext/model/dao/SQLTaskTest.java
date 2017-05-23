package org.scopetext.model.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.scopetext.presenter.ScopeTextPresenter;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    private SQLiteDatabase db;
    @Mock
    private ScopeTextPresenter presenter;

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

    @Test
    public void itShouldVerifySelectAllScopeTextsAndContacts() {
        // Mock setup
        Cursor cursor = mock(Cursor.class);
        when(dbHelper.getReadableDatabase()).thenReturn(db);
        when(db.rawQuery(isA(String.class), (String[]) isNull())).thenReturn(cursor);

        // Test
        List<Object> result = objUnderTest.doInBackground(dbHelper, SQL.SELECT_ALL_SCOPETEXTS_CONTACTS);
        assertTrue(result.isEmpty());
    }

}
