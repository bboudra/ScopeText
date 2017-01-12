package org.scopetext.model.dao;

import android.os.AsyncTask;

/**
 * SQLTask executes any SQL statement in the application.
 * <PRE>
 * NOTES:
 * 1. Not meant to be subclassed.
 * 2. Thread safe.
 * <PRE/>
 * Created by john.qualls on 9/20/2016.
 */
public class SQLTask extends AsyncTask<Object, Integer, Object> {
    public enum Task {
        GET_ALL_SCOPE_TEXTS;
    }

    /**
     * Executes any SQL statement asynchronously.
     * @param params The parameters must be of the following type, and order:
     *<ol>
     *     <li>
     *         DBHelper - Needed to retrieve the writeable database reference in a thread safe
     *         manner.
     *     </li>
     *     <li>
     *         TODO - Define Enum for specific SQL queries
     *     </li>
     *</ol>
     * @return
     */
    @Override
    protected DBHelper doInBackground(Object... params) {
        return null;
    }
}
