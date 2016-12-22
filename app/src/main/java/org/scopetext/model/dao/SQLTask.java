package org.scopetext.model.dao;

import android.os.AsyncTask;

import org.scopetext.view.MainActivity;

/**
 * SQLTask executes any SQL statement for this application.
 * <PRE>
 * 1. Not meant to be subclassed.
 * 2. Thread safe.
 * <PRE/>
 * Created by john.qualls on 9/20/2016.
 */
public class SQLTask extends AsyncTask<Object, Integer, Object> {

    /**
     * Executes any SQL statement asynchronously.
     * @param params The parameters must be of the following type, and order:
     * <PRE>
     * 1. DBHelper - Needed to retrieve the writeable database reference. This is accessed in a
     * thread safe manner to avoid race conditions.
     * 2. TODO - Define Enum for specific SQL queries
     * <PRE/>
     * @return
     */
    @Override
    protected DBHelper doInBackground(Object... params) {
        return null;
    }
}
