package org.scopetext.presenter;

import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;

import org.scopetext.model.dao.SQL;
import org.scopetext.presenter.fragment.FragmentAction;
import org.scopetext.presenter.fragment.ScopeTextFragment;

import java.util.List;

/**
 * The presenter component in the <a href=http://antonioleiva.com/mvp-android/ "MVP">MVP</a>
 * architecture. Middle man between the view and model components. Responsible for routing each UI
 * interaction from view, to a specific piece of business logic, and interacts with model if
 * necessary. Created by john.qualls on 11/28/2016.
 */
public interface Presenter {
    /**
     * Updates the Activity, and SQLiteOpenHelper references. This must be invoked when the Activity
     * restarts, so that the new references propagate through the entire application.
     *
     * @param activity The new Activity reference.
     * @param dbHelper Required for database transactions.
     */
    public void activityRefresh(AppCompatActivity activity, SQLiteOpenHelper dbHelper);

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
     * @param sql The SQL statement.
     * @return The resulting object(s).
     */
    public List<Object> executeSQL(SQL sql);

}
