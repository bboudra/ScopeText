package org.scopetext.model.dao;


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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for ScopeTextDAO. Created by john.qualls on 10/3/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScopeTextDAOTest {
    @Mock
    private SQLiteDatabase db;
    @Mock
    private Cursor cursor;
    private List<ScopeText> resultSet;

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
                        ContactAssocSchema.CONTACT_ID +
                        "\nORDER BY ST." + ScopeTextSchema.SCOPETEXT_ID;
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
        when(cursor.moveToNext()).thenReturn(false);
        when(cursor.getString(0)).thenReturn(name);
        when(cursor.getString(1)).thenReturn(contactName);
        when(cursor.getString(2)).thenReturn(inUse);
        when(db.rawQuery(isA(String.class), (String[])isNull())).thenReturn(cursor);

        // Test
        resultSet = ScopeTextDAO.getAllScopeTextsAndContacts(db);
        ScopeText scopeText = (ScopeText) resultSet.get(0);
        Contact contact = scopeText.getContacts().get(0);
        assertEquals(1, resultSet.size());
        assertEquals(name, scopeText.getName());
        assertEquals(contactName, contact.getName());
        assertEquals(true, scopeText.isInUse());
    }

    @Test
    public void itShouldAssert2ValidScopeTexts2ValidContactsForValidSQLExecution() {
        // Test setup
        String name = "name",
                name2 = "name2",
                contactName = "contactName",
                contactName2 = "contactName2",
                inUse = "Y",
                inUse2 = "N";
        when(cursor.moveToFirst()).thenReturn(true);
        when(cursor.moveToNext()).thenReturn(true, false);
        when(cursor.getString(0)).thenReturn(name, name2);
        when(cursor.getString(1)).thenReturn(contactName, contactName2);
        when(cursor.getString(2)).thenReturn(inUse, inUse2);
        when(db.rawQuery(isA(String.class), (String[])isNull())).thenReturn(cursor);

        // Test
        resultSet = ScopeTextDAO.getAllScopeTextsAndContacts(db);
        ScopeText scopeText = resultSet.get(0),
                  scopeText2 = resultSet.get(1);
        List<Contact> contacts = scopeText.getContacts(),
                      contacts2 = scopeText2.getContacts();
        Contact contact = contacts.get(0),
                contact2 = contacts2.get(0);
        assertEquals(2, resultSet.size());
        assertEquals(name, scopeText.getName());
        assertEquals(name2, scopeText2.getName());
        assertEquals(contactName, contact.getName());
        assertEquals(contactName2, contact2.getName());
        assertEquals(true, scopeText.isInUse());
        assertEquals(false, scopeText2.isInUse());
    }

    @Test
    public void itShouldAssert2ContactsForOneScopeText() {
        // Test setup
        String name = "name",
                contactName = "contactName",
                contactName2 = "contactName2",
                inUse = "Y";
        when(cursor.moveToFirst()).thenReturn(true);
        when(cursor.moveToNext()).thenReturn(true, false);
        when(cursor.getString(0)).thenReturn(name);
        when(cursor.getString(1)).thenReturn(contactName, contactName2);
        when(cursor.getString(2)).thenReturn(inUse);
        when(db.rawQuery(isA(String.class), (String[])isNull())).thenReturn(cursor);

        // Test
        resultSet = ScopeTextDAO.getAllScopeTextsAndContacts(db);
        ScopeText scopeText = resultSet.get(0);
        List<Contact> contacts = scopeText.getContacts();
        Contact contact = contacts.get(0),
                contact2 = contacts.get(1);
        assertEquals(2, contacts.size());
        assertEquals(contactName, contact.getName());
        assertEquals(contactName2, contact2.getName());
    }

    @Test
    public void itShouldAssert2ScopeTextsForValidSQLExecution() {
        // Test setup
        String name = "name",
                contactName = "contactName",
                inUse = "Y",
                name2 = "name2",
                contactName2 = "contactName2",
                inUse2 = "N";
        when(cursor.moveToFirst()).thenReturn(true);
        when(cursor.moveToNext()).thenReturn(true, false);
        when(cursor.getString(0)).thenReturn(name, name2);
        when(cursor.getString(1)).thenReturn(contactName, contactName2);
        when(cursor.getString(2)).thenReturn(inUse, inUse2);
        when(db.rawQuery(isA(String.class), (String[])isNull())).thenReturn(cursor);

        // Test
        resultSet = ScopeTextDAO.getAllScopeTextsAndContacts(db);
        ScopeText scopeText1 = (ScopeText) resultSet.get(0),
                scopeText2 = (ScopeText) resultSet.get(1);
        Contact contact1 = scopeText1.getContacts().get(0),
                contact2 = scopeText2.getContacts().get(0);
        assertEquals(2, resultSet.size());
        assertEquals(name, scopeText1.getName());
        assertEquals(contactName, contact1.getName());
        assertEquals(true, scopeText1.isInUse());
        assertEquals(name2, scopeText2.getName());
        assertEquals(contactName2, contact2.getName());
        assertEquals(false, scopeText2.isInUse());
    }

    @Test
    public void itShouldAssertCloseForGetAllScopeTexts() {
        ScopeTextDAO.getAllScopeTextsAndContacts(db);
        verify(db).close();
    }
}
