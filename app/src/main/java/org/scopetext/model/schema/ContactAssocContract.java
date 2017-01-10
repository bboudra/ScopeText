package org.scopetext.model.schema;

import android.provider.BaseColumns;

/**
 * Schema for the ST_CT_ASSOC table. Created by john.qualls on 8/9/2016.
 */
public class ContactAssocContract {
    // Prevent accidental instantiation
    private ContactAssocContract() {
    }

    public static abstract class ContactAssocSchema implements BaseColumns {
        public static final String TABLE_NAME = "CONTACT_ASSOCIATION";
        public static final String CONTACT_ASSOC_ID = "CONTACT_ASSOC_ID";
        public static final String SCOPETEXT_ID = "SCOPETEXT_ID";
        public static final String CONTACT_ID = "CONTACT_ID";
    }
}
