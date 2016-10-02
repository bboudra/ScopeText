package org.scopetext.model.schema;

import android.provider.BaseColumns;

/**
 * Schema for the CONTRACT table.
 * Created by john.qualls on 8/9/2016.
 */
public class ContactContract {
    // Prevent accidental instantiation
    private ContactContract(){}

    public static abstract class ContractSchema implements BaseColumns {
        public static final String TABLE_NAME = "CONTRACT",
                ID = "CONTACT_ID",
                DISPLAY_NAME = "DISPLAY_NAME";
    }
}
