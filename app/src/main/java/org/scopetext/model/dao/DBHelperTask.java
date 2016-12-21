package org.scopetext.model.dao;

import android.os.AsyncTask;

import org.scopetext.view.MainActivity;

/**
 * DBHelperTask retrieves a new instance of the DBHelper class asynchronously.
 * <PRE>
 * 1. Not meant to be subclassed.
 * <PRE/>
 * Created by john.qualls on 9/20/2016.
 */
public class DBHelperTask extends AsyncTask<MainActivity, Integer, DBHelper> {

    @Override
    protected DBHelper doInBackground(MainActivity... params) {
        return null;
    }
}
