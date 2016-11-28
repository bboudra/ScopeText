package org.scopetext.model.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
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

/**
 * Unit tests for ScopeTextDAO.
 * Created by john.qualls on 10/3/2016.
 */
public class ScopeTextDAOTest {
    @Mock
    SQLiteDatabase db;
    @Mock
    Cursor cursor;

    @Test
    public void getAllScopeTextsSQLError() {
        getAllScopeTextSetup();
        Mockito.when(db.rawQuery(Matchers.anyString(), Matchers.any(String[].class))).
                thenReturn(null);
        Assert.assertNull(ScopeTextDAO.getAllScopeTexts(db, null));
    }

    @Test
    public void getAllScopeTextsSQL(){
        getAllScopeTextSetup();
        final String GET_ALL_SCOPETEXTS = "SELECT ST." + ScopeTextSchema.NAME + ", M."
                + MessageSchema.TYPE + ", M." + MessageSchema.REG_EXP + ", R."
                + ResponseSchema.ACTION_APP + ", R." + ResponseSchema.EXTERNAL_APP + ", ST."
                + ScopeTextSchema.IN_USE + "\n"
                + "FROM " + ScopeTextSchema.TABLE_NAME + " ST INNER JOIN " + MessageSchema.TABLE_NAME
                + " M ON ST." + ScopeTextSchema.MESSAGE_ID + " = M." + MessageSchema.MESSAGE_ID + "\n"
                + "INNER JOIN " + ResponseSchema.TABLE_NAME + " R ON ST." + ScopeTextSchema.RESPONSE_ID
                + " = R." + ResponseSchema.RESPONSE_ID;

        // Test
        ScopeTextDAO.getAllScopeTexts(db, null);
        Mockito.verify(db).rawQuery(GET_ALL_SCOPETEXTS, null);
    }

    @Test
    public void buildScopeTextListNoCache() {
        // Setup
        getAllScopeTextSetup();
        final String ST_NAME = "ST1",
                     REG_EXPR = "REG_EXPR",
                     MESSAGE_TYPE_TEXT = "TEXT",
                     ACTION_APP = "CREATE",
                     ALARM_APP = "ALARM",
                     IN_USE = "Y";
        final String[] returnVals = {ST_NAME, MESSAGE_TYPE_TEXT, REG_EXPR, MESSAGE_TYPE_TEXT,
            ACTION_APP, ALARM_APP, IN_USE};
        // Build expected ScopeText object for comparison
        ArrayList<ScopeText> expectedList = new ArrayList<>();
        Response response = new Response(ActionApp.CREATE, ExternalApp.ALARM);
        Message message = new Message(MessageType.TEXT, REG_EXPR);
        ScopeText expectedScopeText = new ScopeText(ST_NAME, message, response, true);
        // Stub setup
        Mockito.when(cursor.moveToNext()).thenReturn(true,false);
        for(int i = 0; i < returnVals.length; i++) {
            Mockito.when(cursor.getString(i)).thenReturn(returnVals[i]);
        }

        // Test
        ArrayList<ScopeText> actualList = ScopeTextDAO.getAllScopeTexts(db, null);
        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void buildScopeTextListEmptyListTest() {
        Assert.fail("Not Yet Implemented!");
    }

    @Test
    public void buildScopeTextList2RowTest() {
        Assert.fail("Not Yet Implemented!");
    }

    @Test
    public void buildScopeTextListCloseCursorTest() {
        Assert.fail("Not Yet Implemented!");
    }

    @Test
    public void buildScopeTextListCachedListTest() {
        Assert.fail("Not Yet Implemented!");
    }

    private void getAllScopeTextSetup() {
        db = Mockito.mock(SQLiteDatabase.class);
        cursor = Mockito.mock(Cursor.class);
        Mockito.when(db.rawQuery(Matchers.anyString(), Matchers.any(String[].class))).
                thenReturn(cursor);
    }
}
