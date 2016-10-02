package org.scopetext.model.schema;

import android.provider.BaseColumns;

/**
 * Created by john.qualls on 8/7/2016.
 */
public class ResponseContract {
    // Prevent accidental instantiation
    private ResponseContract(){}

    public static abstract class ResponseSchema implements BaseColumns {
        public static final String TABLE_NAME = "RESPONSE",
                RESPONSE_ID = "RESPONSE_ID",
                ACTION_APP = "ACTION_APP",
                EXTERNAL_APP = "EXTERNAL_APP";
    }
}
