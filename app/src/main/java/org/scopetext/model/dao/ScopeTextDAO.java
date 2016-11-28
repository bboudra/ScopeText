package org.scopetext.model.dao;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.scopetext.model.javabean.ScopeText;
import org.scopetext.model.schema.MessageContract.MessageSchema;
import org.scopetext.model.schema.ResponseContract.ResponseSchema;
import org.scopetext.model.schema.ScopeTextContract.ScopeTextSchema;

import java.util.ArrayList;

/**
 *
 * Created by john.qualls on 8/9/2016.
 */
public class ScopeTextDAO {

    /**
     * Reads all existing ScopeText objects from the database.
     * @param db Used retrieve data from the database.
     * @param cachedList The current list of ScopeText objects.
     * @return A list of the resulting ScopeTexts, or null if nothing was retrieved.
     */
    public static ArrayList<ScopeText> getAllScopeTexts(SQLiteDatabase db,
        ArrayList<ScopeText> cachedList) {
        ArrayList<ScopeText> list = null;
        final String All_SCOPETEXT_SQL = "SELECT ST." + ScopeTextSchema.NAME + ", M."
            + MessageSchema.TYPE + ", M." + MessageSchema.REG_EXP + ", R."
            + ResponseSchema.ACTION_APP + ", R." + ResponseSchema.EXTERNAL_APP + ", ST."
            + ScopeTextSchema.IN_USE + "\n"
            + "FROM " + ScopeTextSchema.TABLE_NAME + " ST INNER JOIN " + MessageSchema.TABLE_NAME
            + " M ON ST." + ScopeTextSchema.MESSAGE_ID + " = M." + MessageSchema.MESSAGE_ID + "\n"
            + "INNER JOIN " + ResponseSchema.TABLE_NAME + " R ON ST." + ScopeTextSchema.RESPONSE_ID
            + " = R." + ResponseSchema.RESPONSE_ID;
        Cursor cursor = db.rawQuery(All_SCOPETEXT_SQL, null);
        if(cursor != null)
            list = buildScopeTextList(cursor, cachedList);
        return list;
    }

    /**
     * Builds a list of ScopeText java bean objects from a Cursor. A cached list of objects are
     * compared against to see if new objects need to be added.
     * @param cursor Used to retrieve the data to populate the java beans.
     * @param cachedList The cached list to check. Null can be passed if there is no cache.
     */
    protected static ArrayList<ScopeText> buildScopeTextList(Cursor cursor,
        ArrayList<ScopeText> cachedList) {
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
        return null;
    }
}
