package org.scopetext.model.dao;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.tool.processing.Scope;

import org.scopetext.model.javabean.Contact;
import org.scopetext.model.javabean.ScopeText;
import org.scopetext.model.schema.ContactAssocContract.ContactAssocSchema;
import org.scopetext.model.schema.ContactContract.ContactSchema;
import org.scopetext.model.schema.ScopeTextContract.ScopeTextSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by john.qualls on 8/9/2016.
 */
public class ScopeTextDAO {
    private static final String IS_IN_USE = "Y";

    /**
     * Reads all existing ScopeText objects from the database.
     *
     * @param db Used to retrieve data from the database.
     * @return A list of the resulting ScopeTexts, or null if nothing was retrieved.
     */
    public static List<Object> getAllScopeTextsAndContacts(SQLiteDatabase db) {
        List<Object> list = new ArrayList<>();
        final String All_SCOPETEXT_SQL =
                "SELECT ST." + ScopeTextSchema.NAME + ", C." + ContactSchema.NAME + ", ST." +
                        ScopeTextSchema.IN_USE +
                        "\nFROM " + ScopeTextSchema.TABLE_NAME + " ST" +
                        "\nLEFT JOIN " + ContactAssocSchema.TABLE_NAME +
                        " CA ON ST." + ScopeTextSchema.SCOPETEXT_ID + " = CA." +
                        ContactAssocSchema.SCOPETEXT_ID +
                        "\nLEFT JOIN " + ContactSchema.TABLE_NAME +
                        " C ON C." + ContactSchema.CONTACT_ID + " = CA." +
                        ContactAssocSchema.CONTACT_ID;
        Cursor cursor = db.rawQuery(All_SCOPETEXT_SQL, null);
        if (cursor != null) {
            list = buildScopeTextList(cursor);
        }
        return list;
    }

    private static List<Object> buildScopeTextList(Cursor cursor) {
        List<Object> list = new ArrayList<>();
        if(cursor.moveToFirst()) {
            String name = null,
                    contactName = null,
                    inUseStr = null;
            boolean inUse = false;
            ScopeText scopeText = null;
            Contact contact = null;
            List<Contact> contacts = null;
            while (cursor.moveToNext()) {
                // Get DB data
                name = cursor.getString(0);
                contactName = cursor.getString(1);
                inUseStr = cursor.getString(2);
                if (inUseStr != null) {
                    inUse = inUseStr.equals(IS_IN_USE) ? true : false;
                }

                // Build ScopeText and Contact Objects
                scopeText = new ScopeText();
                scopeText.setName(name);
                scopeText.setInUse(inUse);
                contact = new Contact();
                contact.setName(contactName);
                contacts = new Vector();
                contacts.add(contact);
                scopeText.setContacts(contacts);
                list.add(scopeText);
            }
        }
        return list;
    }
}
