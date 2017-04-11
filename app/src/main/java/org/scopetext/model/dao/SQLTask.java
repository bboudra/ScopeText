package org.scopetext.model.dao;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import org.scopetext.presenter.Presenter;

import java.util.List;

/**
 * SQLTask executes any SQL statement in the application. <PRE> NOTES: 1. Not meant to be
 * subclassed. 2. Thread safe. <PRE/> Created by john.qualls on 9/20/2016.
 */
public class SQLTask extends AsyncTask<Object, Integer, Object> {
    private static final int ARG_SIZE = 2;
    private Presenter presenter;

    public enum Task {
        GET_ALL_SCOPE_TEXTS;
    }

    public SQLTask(Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Executes any SQL statement asynchronously.
     *
     * @param params The parameters must be of the following type, order, and number: <ol> <li>
     * DBHelper - Needed to retrieve the writeable database reference in a thread safe manner. </li>
     * <li> SQL - Type of SQL that will be executed. </li> </ol>
     * @return The resulting objects in a list.
     * @throws IllegalArgumentException Thrown if the parameter constraints above are violated.
     */
    @Override
    protected List<Object> doInBackground(Object... params) throws IllegalArgumentException {
        // Validate arguments
        if (params.length == 2) {
            List<Object> results = null;
            Object param1 = params[0];
            Object param2 = params[1];
            if (param1 != null && param2 != null && param1 instanceof DBHelper &&
                    param2 instanceof SQL) {
                // Delegate SQL to execute
                DBHelper dbHelper = (DBHelper) param1;
                SQL sql = (SQL) param2;
                SQLiteDatabase db = null;
                switch (sql) {
                    case SELECT_ALL_SCOPETEXTS_CONTACTS:
                        db = dbHelper.getReadableDatabase();
                        results = ScopeTextDAO.getAllScopeTextsAndContacts(db);
                        break;
                }
                return results;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    protected void onPostExecute(Object result) {
        List<Object> list = (List<Object>) result;
        presenter.retrieveSQLTaskResults(list);
    }
}
