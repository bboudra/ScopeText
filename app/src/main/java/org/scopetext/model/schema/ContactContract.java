package org.scopetext.model.schema;

import android.provider.BaseColumns;

/**
 * Schema for the CONTRACT table. Created by john.qualls on 8/9/2016.
 */
public class ContactContract {
    // Prevent accidental instantiation
    private ContactContract() {
    }

    public static abstract class ContactSchema implements BaseColumns {
        public static final String TABLE_NAME = "CONTACT";
        public static final String CONTACT_ID = "CONTACT_ID";
        public static final String NAME = "NAME";
    }
}
