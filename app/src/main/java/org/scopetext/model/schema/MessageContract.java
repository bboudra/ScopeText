package org.scopetext.model.schema;

import android.provider.BaseColumns;

/**
 * Schema for the MESSAGE table. Created by john.qualls on 8/7/2016.
 */
public class MessageContract {
    // Prevent accidental instantiation
    private MessageContract() {
    }


    public static abstract class MessageSchema implements BaseColumns {
        public static final String TABLE_NAME = "MESSAGE", MESSAGE_ID = "MESSAGE_ID", TYPE = "TYPE",
                REG_EXP = "REG_EXP";
    }
}
