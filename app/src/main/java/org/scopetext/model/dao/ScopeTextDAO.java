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
    private String allScopeTextsSQL;

    /**
     * Reads all existing ScopeText objects from the database.
     * @param db Used retrieve data from the database.
     * @param cachedList The current list of ScopeText objects.
     * @return A list of the resulting ScopeTexts, or null if nothing was retrieved.
     */
    public ArrayList<ScopeText> getAllScopeTexts(SQLiteDatabase db,
        ArrayList<ScopeText> cachedList) {
        ArrayList<ScopeText> list = null;
        allScopeTextsSQL = "SELECT ST." + ScopeTextSchema.NAME + ", M."
            + MessageSchema.TYPE + ", M." + MessageSchema.REG_EXP + ", R."
            + ResponseSchema.ACTION_APP + ", R." + ResponseSchema.EXTERNAL_APP + ", ST."
            + ScopeTextSchema.IN_USE + "\n"
            + "FROM " + ScopeTextSchema.TABLE_NAME + " ST INNER JOIN " + MessageSchema.TABLE_NAME
            + " M ON ST." + ScopeTextSchema.MESSAGE_ID + " = M." + MessageSchema.MESSAGE_ID + "\n"
            + "INNER JOIN " + ResponseSchema.TABLE_NAME + " R ON ST." + ScopeTextSchema.RESPONSE_ID
            + " = R." + ResponseSchema.RESPONSE_ID;
        Cursor cursor = db.rawQuery(allScopeTextsSQL, null);
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
    private ArrayList<ScopeText> buildScopeTextList(Cursor cursor,
        ArrayList<ScopeText> cachedList) {
        if(cachedList == null) {
            try {
                while(cursor.moveToFirst()) {

                }
            } catch (SQLException e) {

            }
        }
        return null;
    }

    public String getAllScopeTextsSQL() {
        return allScopeTextsSQL;
    }

    public void setAllScopeTextsSQL(String allScopeTextsSQL) {
        this.allScopeTextsSQL = allScopeTextsSQL;
    }
}
