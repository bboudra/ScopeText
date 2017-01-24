package org.scopetext.model.dao;


import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.scopetext.model.javabean.Contact;
import org.scopetext.model.javabean.ScopeText;
import org.scopetext.model.schema.ContactAssocContract.ContactAssocSchema;
import org.scopetext.model.schema.ContactContract.ContactSchema;
import org.scopetext.model.schema.ScopeTextContract.ScopeTextSchema;

import java.util.List;

/**
 * Unit tests for ScopeTextDAO. Created by john.qualls on 10/3/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScopeTextDAOTest {
    @Mock
    private SQLiteDatabase db;
    @Mock
    private Cursor cursor;
    private List<Object> resultSet;

    @Test
    public void itShouldVerifySQL() {
        String All_SCOPETEXT_SQL =
                "SELECT ST." + ScopeTextSchema.NAME + ", C." + ContactSchema.NAME + ", ST." +
                        ScopeTextSchema.IN_USE +
                        "\nFROM " + ScopeTextSchema.TABLE_NAME + " ST" +
                        "\nLEFT JOIN " + ContactAssocSchema.TABLE_NAME +
                        " CA ON ST." + ScopeTextSchema.SCOPETEXT_ID + " = CA." +
                        ContactAssocSchema.SCOPETEXT_ID +
                        "\nLEFT JOIN " + ContactSchema.TABLE_NAME +
                        " C ON C." + ContactSchema.CONTACT_ID + " = CA." +
                        ContactAssocSchema.CONTACT_ID;
        ScopeTextDAO.getAllScopeTextsAndContacts(db);
        verify(db).rawQuery(All_SCOPETEXT_SQL, null);
    }

    @Test
    public void itShouldAssertEmptyResultSetForNullCursor() {
        resultSet = ScopeTextDAO.getAllScopeTextsAndContacts(db);
        assertEquals(resultSet.size(), 0);
    }

    @Test
    public void itShouldAssertValidScopeTextForValidSQLExecution() {
        // Test setup
        String name = "name",
                contactName = "contactName",
                inUse = "Y";
        when(cursor.moveToFirst()).thenReturn(true);
        when(cursor.moveToNext()).thenReturn(true, false);
        when(cursor.getString(0)).thenReturn(name);
        when(cursor.getString(1)).thenReturn(contactName);
        when(cursor.getString(2)).thenReturn(inUse);
        when(db.rawQuery(isA(String.class), (String[])isNull())).thenReturn(cursor);

        // Test
        resultSet = ScopeTextDAO.getAllScopeTextsAndContacts(db);
        ScopeText scopeText = (ScopeText) resultSet.get(0);
        Contact contact = (Contact) scopeText.getContacts().get(0);
        assertEquals(name, scopeText.getName());
        assertEquals(contactName, contact.getName());
        assertEquals(true, scopeText.isInUse());
    }

    @Test
    public void itShouldAssert2ContactsForOneScopeText() {
        fail("Not Yet Implemented");
    }
}
