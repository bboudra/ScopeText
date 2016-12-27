package org.scopetext.model.dao;


import static org.mockito.Matchers.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.scopetext.model.javabean.Message;
import org.scopetext.model.javabean.Response;
import org.scopetext.model.javabean.ScopeText;
import org.scopetext.model.javabean.type.ActionApp;
import org.scopetext.model.javabean.type.ExternalApp;
import org.scopetext.model.javabean.type.MessageType;
import org.scopetext.model.schema.MessageContract.MessageSchema;
import org.scopetext.model.schema.ResponseContract.ResponseSchema;
import org.scopetext.model.schema.ScopeTextContract.ScopeTextSchema;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for ScopeTextDAO. Created by john.qualls on 10/3/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScopeTextDAOTest {
    @Mock SQLiteDatabase db;
    @Mock Cursor cursor;

    @Before
    public void setup() {
        when(db.rawQuery(isA(String.class), isA(String[].class))).
                thenReturn(cursor);
    }

    @Test
    public void assertEmptyListForNullCursorResult() {
        when(db.rawQuery(isA(String.class), isA(String[].class))).
                thenReturn(null);
        List<ScopeText> actualList = ScopeTextDAO.getAllScopeTexts(db, null);
        assertEquals(0, actualList.size());
    }

    @Test
    public void verifyCorrectSQLStatement() {
        final String GET_ALL_SCOPETEXTS =
                "SELECT ST." + ScopeTextSchema.NAME + ", M." + MessageSchema.TYPE + ", M." +
                        MessageSchema.REG_EXP + ", R." + ResponseSchema.ACTION_APP + ", R." +
                        ResponseSchema.EXTERNAL_APP + ", ST." + ScopeTextSchema.IN_USE + "\n" +
                        "FROM " + ScopeTextSchema.TABLE_NAME + " ST INNER JOIN " +
                        MessageSchema.TABLE_NAME + " M ON ST." + ScopeTextSchema.MESSAGE_ID +
                        " = M." + MessageSchema.MESSAGE_ID + "\n" + "INNER JOIN " +
                        ResponseSchema.TABLE_NAME + " R ON ST." + ScopeTextSchema.RESPONSE_ID +
                        " = R." + ResponseSchema.RESPONSE_ID;

        // Test
        ScopeTextDAO.getAllScopeTexts(db, null);
        verify(db).rawQuery(GET_ALL_SCOPETEXTS, null);
    }

    @Test
    public void assert1CreatedScopeText() {
        // Build expected ScopeText object for comparison
        final String ST_NAME = "ST1";
        final String REG_EXPR = "REG_EXPR";
        ArrayList<ScopeText> expectedList = new ArrayList<>();
        Response response = new Response(ActionApp.CREATE, ExternalApp.ALARM);
        Message message = new Message(MessageType.TEXT, REG_EXPR);
        ScopeText expectedScopeText = new ScopeText(ST_NAME, message, response, true);

        // Stub setup
        when(cursor.moveToNext()).thenReturn(true, false);
        when(cursor.getString(0)).thenReturn(ST_NAME);
        when(cursor.getString(1)).thenReturn("TEXT");
        when(cursor.getString(2)).thenReturn(REG_EXPR);
        when(cursor.getString(3)).thenReturn("CREATE");
        when(cursor.getString(4)).thenReturn("ALARM");
        when(cursor.getString(4)).thenReturn("Y");

        // Test
        List<ScopeText> actualList = ScopeTextDAO.getAllScopeTexts(db, null);
        assertEquals(expectedScopeText, actualList.get(0));
    }

    @Test
    public void buildScopeTextListEmptyListTest() {
        fail("Not Yet Implemented!");
    }

    @Test
    public void buildScopeTextList2RowTest() {
        fail("Not Yet Implemented!");
    }

    @Test
    public void buildScopeTextListCloseCursorTest() {
        fail("Not Yet Implemented!");
    }

    @Test
    public void buildScopeTextListCachedListTest() {
        fail("Not Yet Implemented!");
    }
}
