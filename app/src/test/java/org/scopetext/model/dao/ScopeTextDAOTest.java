package org.scopetext.model.dao;


import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.scopetext.model.schema.ContactAssocContract.ContactAssocSchema;
import org.scopetext.model.schema.ContactContract.ContactSchema;
import org.scopetext.model.schema.MessageContract.MessageSchema;
import org.scopetext.model.schema.ResponseContract.ResponseSchema;
import org.scopetext.model.schema.ScopeTextContract.ScopeTextSchema;

/**
 * Unit tests for ScopeTextDAO. Created by john.qualls on 10/3/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScopeTextDAOTest {

    @Mock
    SQLiteDatabase db;

    @Test
    public void itShouldVerifySQL() {
        String All_SCOPETEXT_SQL =
                "SELECT ST." + ScopeTextSchema.NAME + ", C." + ContactSchema.NAME + ", ST." +
                        ScopeTextSchema.IN_USE +
                        "FROM " + ScopeTextSchema.TABLE_NAME + "ST, " + ContactSchema.TABLE_NAME
                        + "C\n" + "LEFT JOIN " + ContactAssocSchema.TABLE_NAME
                        + "CA ON ST." + ScopeTextSchema.SCOPETEXT_ID + " = CA." +
                        ContactAssocSchema.SCOPETEXT_ID
                        + "WHERE C." + ContactSchema.CONTACT_ID + " = CA." +
                        ContactAssocSchema.CONTACT_ID + ";";

    }
}
