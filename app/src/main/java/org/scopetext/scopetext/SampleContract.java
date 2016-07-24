package org.scopetext.scopetext;

import android.provider.BaseColumns;

/**
 * Created by john.qualls on 7/24/2016.
 */
public final class SampleContract {
    // Prevent accidental instantiation
    private SampleContract(){}

    public static abstract class Test implements BaseColumns {
        public static final String TABLE_NAME = "test",
                                   SCOPETEXT_NAME = "scopetext_name",
                                   CONTACT_NAME = "contact_name",
                                   USABLE = "usable";
    }
}
