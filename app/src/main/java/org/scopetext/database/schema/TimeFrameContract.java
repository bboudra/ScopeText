package org.scopetext.database.schema;

import android.provider.BaseColumns;

/**
 * Schema for the TIMEFRAME table
 * Created by john.qualls on 8/10/2016.
 */
public class TimeFrameContract {
    // Prevent accidental instantiation
    private TimeFrameContract(){}

    public static abstract class TimeFrameSchema implements BaseColumns {
        public static final String TABLE_NAME = "TIMEFRAME",
                START_TIME = "START_TIME",
                END_TIME = "END_TIME";
    }
}
