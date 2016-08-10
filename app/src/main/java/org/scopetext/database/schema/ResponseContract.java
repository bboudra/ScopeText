package org.scopetext.database.schema;

import android.provider.BaseColumns;

/**
 * Created by john.qualls on 8/7/2016.
 */
public class ResponseContract {
    // Prevent accidental instantiation
    private ResponseContract(){}

    public static abstract class ResponseSchema implements BaseColumns {
        public static final String TABLE_NAME = "RESPONSE",
                ACTION_CD = "ACTION_CD",
                EXT_APP_CD = "EXT_APP_CD";
    }
}
