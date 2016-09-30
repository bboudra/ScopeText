package org.scopetext.database.schema;

import android.provider.BaseColumns;

/**
 * Schema for the CT_PHN_NBR_ASSOC Table
 * Created by john.qualls on 8/10/2016.
 */
public class ContactPhoneNumberAssocContract {
    // Prevent accidental instantiation
    private ContactPhoneNumberAssocContract(){}

    public static abstract class ContactPhoneNumberSchema implements BaseColumns {
        public static final String
                TABLE_NAME = "CONTACT_PHONE_NUMBER_ASSOC",
                CONTACT_PHONE_NUMBER_ID = "CONTACT_PHONE_NUMBER_ASSOC_ID";
    }
}
