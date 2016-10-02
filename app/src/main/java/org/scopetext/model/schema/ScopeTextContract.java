package org.scopetext.model.schema;

import android.provider.BaseColumns;

/**
 * Schema for the SCOPETEXT table.
 * Created by john.qualls on 8/7/2016.
 */
public class ScopeTextContract {
    // Prevent accidental instantiation
    private ScopeTextContract(){}

    public static abstract class ScopeTextSchema implements BaseColumns {
        public static final String TABLE_NAME = "SCOPETEXT",
                NAME = "NAME",
                MESSAGE_ID = "MESSAGE_ID",
                RESPONSE_ID = "RESPONSE_ID",
                IN_USE = "IN_USE";
    }
}
