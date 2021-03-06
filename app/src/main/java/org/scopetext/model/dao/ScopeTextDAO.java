package org.scopetext.model.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.scopetext.model.javabean.Contact;
import org.scopetext.model.javabean.ScopeText;
import org.scopetext.model.schema.ContactAssocContract.ContactAssocSchema;
import org.scopetext.model.schema.ContactContract.ContactSchema;
import org.scopetext.model.schema.ScopeTextContract.ScopeTextSchema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Created by john.qualls on 8/9/2016.
 */
public class ScopeTextDAO {

    /**
     * Reads all existing ScopeText objects from the database.
     *
     * @param db Used to retrieve data from the database.
     * @return A list of the resulting ScopeTexts, or null if nothing was retrieved.
     */
    public static List<ScopeText> getAllScopeTextsAndContacts(SQLiteDatabase db) {
        List<ScopeText> list = Collections.emptyList();
        try{
            final String All_SCOPETEXT_SQL =
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
            Cursor cursor = db.rawQuery(All_SCOPETEXT_SQL, null);
            if (cursor != null) {
                list = buildScopeTextList(cursor);
            }
        } finally {
            db.close();
        }
        return list;
    }

    private static List<ScopeText> buildScopeTextList(Cursor cursor) {
        List<ScopeText> list = Collections.emptyList();
        if (cursor.moveToFirst()) {
            list = new ArrayList<>();
            String name = null,
                    contactName = null,
                    inUseStr = null,
                    prevName = null;
            boolean inUse = false;
            ScopeText scopeText = null;
            Contact contact = null;
            List<Contact> contacts = null;
            int row = 0;
            do {
                // Get DB data
                name = cursor.getString(0);
                contactName = cursor.getString(1);
                inUseStr = cursor.getString(2);
                if (inUseStr != null) {
                    inUse = inUseStr.equals("Y");
                }

                // TODO refactor by using .contains() and adding .equals to ScopeText and Contact javabeans
                // Check if record is an additional contact
                if (!list.isEmpty()) {
                    scopeText = list.get(row - 1);
                    prevName = scopeText.getName();
                } else {
                    scopeText = null;
                    prevName = null;
                }
                if (prevName != null && prevName.equals(name)) {
                    // Add contact
                    contact = new Contact();
                    contact.setName(contactName);
                    contacts = scopeText.getContacts();
                    if (contacts != null) {
                        contacts.add(contact);
                    }
                } else {
                    // Add ScopeText
                    scopeText = new ScopeText();
                    scopeText.setName(name);
                    scopeText.setInUse(inUse);
                    contact = new Contact();
                    contact.setName(contactName);
                    contacts = new ArrayList<>();
                    contacts.add(contact);
                    scopeText.setContacts(contacts);
                    list.add(scopeText);
                    row++;
                }
            } while (cursor.moveToNext());
        }
        return list;
    }
}
