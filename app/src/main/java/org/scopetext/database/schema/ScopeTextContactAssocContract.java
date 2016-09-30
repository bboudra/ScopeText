package org.scopetext.database.schema;

import android.provider.BaseColumns;

/**
 * Schema for the ST_CT_ASSOC table.
 * Created by john.qualls on 8/9/2016.
 */
public class ScopeTextContactAssocContract {
    // Prevent accidental instantiation
    private ScopeTextContactAssocContract(){}

    public static abstract class ScopeTextContactAssocSchema implements BaseColumns {
        public static final String TABLE_NAME = "SCOPETEXT_CONTACT_ASSOC",
                SCOPETEXT_CONTACT_ID = "SCOPETEXT_CONTACT_ID";
    }
}
