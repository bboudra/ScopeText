package org.scopetext.database.dao;

import org.scopetext.database.schema.MessageContract.MessageSchema;
import org.scopetext.database.schema.ResponseContract.ResponseSchema;
import org.scopetext.database.schema.ScopeTextContract.ScopeTextSchema;

/**
 * Created by john.qualls on 8/9/2016.
 */
public class ScopeTextDAO {
    // TODO - implement method to create table
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + ScopeTextSchema.TABLE_NAME + " (" +
                    ScopeTextSchema._ID + " INTEGER PRIMARY KEY NOT NULL," +
                    MessageSchema._ID + " INTEGER FOREIGN KEY(" +
                    MessageSchema._ID + ") REFERENCES MESSAGE(" +
                    MessageSchema._ID + ") " +
                    ResponseSchema._ID + " INTEGER FOREIGN KEY(" +
                    ResponseSchema._ID + ") REFERENCES MESSAGE(" +
                    ResponseSchema._ID + ") " +
                    ScopeTextSchema.NAME + " VARCHAR(25) NOT NULL," +
                    ScopeTextSchema.IN_USE + "CHARACTER(1) NOT NULL CHECK(" +
                    ScopeTextSchema.IN_USE + " IN ('Y','N'))\n" +
                    ")";
}
