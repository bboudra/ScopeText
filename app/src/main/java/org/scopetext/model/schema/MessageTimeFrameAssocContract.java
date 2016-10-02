package org.scopetext.model.schema;

import android.provider.BaseColumns;

/**
 * Schema for the MSG_TM_ASSOC table.
 * Created by john.qualls on 8/10/2016.
 */
public class MessageTimeFrameAssocContract {
    // Prevent accidental instantiation
    private MessageTimeFrameAssocContract(){}

    public static abstract class MessageTimeFrameAssocSchema implements BaseColumns {
        public static final String TABLE_NAME = "MESSAGE_TIMEFRAME_ASSOC ",
                MESSAGE_TIMEFRAME_ID = "CONTACT_PHONE_NUMBER_ASSOC_ID";
    }
}
