package org.scopetext.model.dao;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.scopetext.model.javabean.Contact;
import org.scopetext.model.javabean.ScopeText;
import org.scopetext.model.schema.ContactAssocContract.ContactAssocSchema;
import org.scopetext.model.schema.ContactContract.ContactSchema;
import org.scopetext.model.schema.MessageContract.MessageSchema;
import org.scopetext.model.schema.ResponseContract.ResponseSchema;
import org.scopetext.model.schema.ScopeTextContract.ScopeTextSchema;

import java.util.ArrayList;
import java.util.List;

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
    public static List<Object> getAllScopeTextsAndContacts(SQLiteDatabase db) {
        List<Object> list = new ArrayList<>(0);
        final String All_SCOPETEXT_SQL =
                "SELECT ST." + ScopeTextSchema.NAME + ", C." + ContactSchema.NAME + ", ST." +
                        ScopeTextSchema.IN_USE +
                        "FROM " + ScopeTextSchema.TABLE_NAME + "ST, " + ContactSchema.TABLE_NAME
                        + "C\n" + "LEFT JOIN " + ContactAssocSchema.TABLE_NAME
                        + "CA ON ST." + ScopeTextSchema.SCOPETEXT_ID + " = CA." +
                        ContactAssocSchema.SCOPETEXT_ID
                        + "WHERE C." + ContactSchema.CONTACT_ID + " = CA." +
                        ContactAssocSchema.CONTACT_ID + ";";
        Cursor cursor = db.rawQuery(All_SCOPETEXT_SQL, null);
        if (cursor != null) {
            //list = buildScopeTextList(cursor, cachedList);
        }
        return list;
    }

    private static List<ScopeText> buildScopeTextList(Cursor cursor, List<ScopeText> cachedList) {
        List<ScopeText> list = new ArrayList<>();
        if (cachedList == null) {
            try {
                String scopeTextName = "",
                        messageType = "",
                        regularExpression = "",
                        actionApp = "",
                        externalApp = "",
                        inUse = "";

                while (cursor.moveToNext()) {
                    scopeTextName = cursor.getString(0);
                    messageType = cursor.getString(1);
                    regularExpression = cursor.getString(2);
                    actionApp = cursor.getString(3);
                    externalApp = cursor.getString(4);
                    inUse = cursor.getString(5);
                }
            } catch (SQLException e) {

            }
        }
        return list;
    }
}
