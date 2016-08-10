package org.scopetext.database.schema;

import android.provider.BaseColumns;

/**
 * Schema for the ST_CT_ASSOC table.
 * Created by john.qualls on 8/9/2016.
 */
public class PhoneNumberContract {
    // Prevent accidental instantiation
    private PhoneNumberContract(){}

    public static abstract class PhoneNumberSchema implements BaseColumns {
        public static final String TABLE_NAME = "ST_CT_ASSOC",
                NUMBER = "NUMBER";
    }
}