package org.scopetext.model.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.scopetext.model.javabean.ScopeText;
import org.scopetext.model.schema.MessageContract;
import org.scopetext.model.schema.ResponseContract;
import org.scopetext.model.schema.ScopeTextContract;

import java.util.ArrayList;

/**
 * Unit tests for ScopeTextDAO.
 * Created by john.qualls on 10/3/2016.
 */
public class ScopeTextDAOTest {
    private ScopeTextDAO objUnderTest;
    @Mock
    SQLiteDatabase db;
    @Mock
    Cursor cursor;


    @Before
    public void setup() {
        objUnderTest = new ScopeTextDAO();
    }

    @Test
    public void getAllScopeTextsSQLError() {
        getAllScopeTextSetup();
        Mockito.when(db.rawQuery(Matchers.anyString(), Matchers.any(String[].class))).
                thenReturn(null);
        Assert.assertNull(objUnderTest.getAllScopeTexts(db, null));
    }

    @Test
    public void getAllScopeTextsSQL(){
        getAllScopeTextSetup();
        final String GET_ALL_SCOPETEXTS = "SELECT ST." + ScopeTextContract.ScopeTextSchema.NAME + ", M."
                + MessageContract.MessageSchema.TYPE + ", M." + MessageContract.MessageSchema.REG_EXP + ", R."
                + ResponseContract.ResponseSchema.ACTION_APP + ", R." + ResponseContract.ResponseSchema.EXTERNAL_APP + ", ST."
                + ScopeTextContract.ScopeTextSchema.IN_USE + "\n"
                + "FROM " + ScopeTextContract.ScopeTextSchema.TABLE_NAME + " ST INNER JOIN " + MessageContract.MessageSchema.TABLE_NAME
                + " M ON ST." + ScopeTextContract.ScopeTextSchema.MESSAGE_ID + " = M." + MessageContract.MessageSchema.MESSAGE_ID + "\n"
                + "INNER JOIN " + ResponseContract.ResponseSchema.TABLE_NAME + " R ON ST." + ScopeTextContract.ScopeTextSchema.RESPONSE_ID
                + " = R." + ResponseContract.ResponseSchema.RESPONSE_ID;

        // Test
        objUnderTest.getAllScopeTexts(db, null);
        Mockito.verify(db).rawQuery(objUnderTest.getAllScopeTextsSQL(), null);
        Assert.assertEquals(GET_ALL_SCOPETEXTS, objUnderTest.getAllScopeTextsSQL());
    }

    @Test
    public void buildScopeTextListNoCache() {
        getAllScopeTextSetup();
        ArrayList<ScopeText> expectedList = new ArrayList<>();

        // Test
        ArrayList<ScopeText> actualList = objUnderTest.getAllScopeTexts(db, null);
        Assert.assertEquals(expectedList, actualList);
    }

    private void getAllScopeTextSetup() {
        db = Mockito.mock(SQLiteDatabase.class);
        cursor = Mockito.mock(Cursor.class);
        Mockito.when(db.rawQuery(Matchers.anyString(), Matchers.any(String[].class))).
                thenReturn(cursor);
    }
}
