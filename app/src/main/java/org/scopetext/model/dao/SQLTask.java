package org.scopetext.model.dao;

import android.os.AsyncTask;

import java.util.List;

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
    private static final int ARG_SIZE = 2;
    public enum Task {
        GET_ALL_SCOPE_TEXTS;
    }

    /**
     * Executes any SQL statement asynchronously.
     * @param params The parameters must be of the following type, order, and number:
     *<ol>
     *     <li>
     *         DBHelper - Needed to retrieve the writeable database reference in a thread safe
     *         manner.
     *     </li>
     *     <li>
     *         SQL - Type of SQL that will be executed.
     *     </li>
     *</ol>
     * @return The resulting objects in a list.
     * @throws IllegalArgumentException Thrown if the parameter constraints above are violated.
     */
    @Override
    protected List<Object> doInBackground(Object... params) throws IllegalArgumentException {
        if(params.length != 2) {
            throw new IllegalArgumentException("Incorrect number of parameters.");
        }

        return null;
    }
}
