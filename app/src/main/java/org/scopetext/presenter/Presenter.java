package org.scopetext.presenter;

import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;

import org.scopetext.model.dao.SQL;
import org.scopetext.model.fragment.FragmentAction;
import org.scopetext.model.fragment.ScopeTextFragment;

import java.util.List;

/**
 * The presenter component in the <a href=http://antonioleiva.com/mvp-android/ "MVP">MVP</a>
 * architecture. The component between the view and model components. Responsible for routing each
 * UI interaction from view, to a specific piece of business logic, and formats the data for proper
 * display in the View component. Created by john.qualls on 11/28/2016.
 */
public interface Presenter {
    /**
     * Updates the Activity, and SQLiteOpenHelper references. This must be invoked when the Activity
     * restarts so that the UI functions correctly.
     *
     * @param activity The new Activity reference.
     * @param dbHelper Required for database transactions.
     */
    public void activityRefresh(AppCompatActivity activity, SQLiteOpenHelper dbHelper);

    /**
     * Sets up a new RecyclerViewAdapter for the given class's UI. The UI must contain a
     * RecyclerView element in it's xml layout.
     * @param classname The class with the desired UI to apply the adapter.
     */
    public void setRecyclerViewAdapter(String classname);

    /**
     * Invokes FragmentAction implementation to add a new Fragment. Should determine what fragment
     * to add and to what View container based on the ScopeTextFragment type.
     *
     * @param type The ScopeTextFragment type.
     * @see FragmentAction
     * @see ScopeTextFragment
     */
    public void addFragment(ScopeTextFragment type);

    /**
     * Executes a specific SQL statement asynchronously.
     *
     * @param sql The SQL statement.
     */
    public void executeSQL(SQL sql);

    /**
     * Retrieves results from asynchronous SQLTask calls
     *
     * @param results The results from the SQL.
     */
    public void retrieveSQLTaskResults(List<Object> results);
}
