package org.scopetext.database.schema;

import android.provider.BaseColumns;

/**
 * Schema for the MESSAGE table.
 * Created by john.qualls on 8/7/2016.
 */
public class MessageContract {
    // Prevent accidental instantiation
    private MessageContract(){}


    public static abstract class MessageSchema implements BaseColumns {
        public static final String TABLE_NAME = "MESSAGE",
                TYPE = "TYPE",
                REG_EXP = "REG_EXP";
    }
}
