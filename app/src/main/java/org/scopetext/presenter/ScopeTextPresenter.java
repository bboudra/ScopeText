package org.scopetext.presenter;

import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.androidannotations.annotations.EBean;
import org.scopetext.model.cache.Cache;
import org.scopetext.model.cache.ScopeTextCache;
import org.scopetext.model.dao.DBHelper;
import org.scopetext.model.dao.SQL;
import org.scopetext.model.dao.SQLTask;
import org.scopetext.model.javabean.ScopeText;
import org.scopetext.presenter.fragment.FragmentAction;
import org.scopetext.presenter.fragment.ScopeTextFragment;
import org.scopetext.presenter.fragment.ScopeTextFragmentAction;
import org.scopetext.view.NewContactFragment;
import org.scopetext.view.ScopeTextListFragment;

import java.util.List;

/**
 * The presenter component in the <a href=http://antonioleiva.com/mvp-android/ "MVP">MVP</a>
 * architecture. The component between the view and model components. Responsible for routing each
 * UI interaction from view, to a specific piece of business logic, and formats the data for proper
 * display in the View component. Created by john.qualls on 11/28/2016.
 */
public class ScopeTextPresenter {
    private final static ScopeTextPresenter presenter = new ScopeTextPresenter();
    private DBHelper dbHelper;
    private FragmentAction fragmentAction;
    private Cache scopeTextCache;
    private RecyclerViewAdapter recyclerViewAdapter;
    private AppCompatActivity activity;

    /*
     * Used for unit testing this singleton class. Params are used to mock out collaborators with
     * this class.
     */
    ScopeTextPresenter(DBHelper dbHelper, FragmentAction fragmentAction, Cache scopeTextCache) {
        this.dbHelper = dbHelper;
        this.fragmentAction = fragmentAction;
        this.scopeTextCache = scopeTextCache;
    }

    /**
     * Initializes the following application components: <PRE> 1. Fragment Action. <PRE/>
     *
     * @see FragmentAction
     */
    private ScopeTextPresenter() {
        fragmentAction = ScopeTextFragmentAction.getInstance();
        scopeTextCache = ScopeTextCache.getInstance();
    }

    /**
     * Gets a reference to this singleton.
     *
     * @return The reference.
     */
    public static ScopeTextPresenter getInstance() {
        return presenter;
    }

    /**
     * Updates the Activity, and SQLiteOpenHelper references. This must be invoked when the Activity
     * restarts so that the UI functions correctly.
     *
     * @param activity The new Activity reference.
     * @param dbHelper Required for database transactions.
     */
    public void activityRefresh(AppCompatActivity activity, SQLiteOpenHelper dbHelper) {
        if (activity != null) {
            this.activity = activity;
            fragmentAction.activityRefresh(activity);
            setupActionBar();
            this.dbHelper = (DBHelper) dbHelper;
        }
    }

    /**
     * Sets up a new RecyclerViewAdapter for the given Fragment classname. The Fragment must contain
     * a RecyclerView element in it's corresponding xml layout in order to successfully setup a
     * RecyclerViewAdapter implementation.
     *
     * @param fragmentName The class with the desired UI to apply the adapter.
     * @return Whether or not the RecyclerViewAdapter was successfully setup.
     */
    public boolean setRecyclerViewAdapter(ScopeTextFragment fragmentName) {
        boolean result = false;
        RecyclerViewAdapter adapter = null;
        Fragment fragment = null;

        // Setup RecyclerViewAdapter based on Fragment name
        if (fragmentName == null) {
            // TODO put in logger class
/*            Log.e(ScopeTextPresenter.class.getName(), "Cannot set RecyclerViewAdapter with null"
            + " Fragment name.");*/
        } else if (fragmentName == ScopeTextFragment.SCOPE_TEXT_LIST) {
            // Get RecyclerView and LayoutManager
            RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.scopetext_list);
            RecyclerView.LayoutManager adapterItemLayout = new LinearLayoutManager(activity);
            recyclerView.setLayoutManager(adapterItemLayout);

            // Setup adapter
            adapter = new ScopeTextListAdapter(scopeTextCache.getCache(), this);
            recyclerView.setAdapter((RecyclerView.Adapter) adapter);
            result = true;
        } else {
            // TODO put in logger class
/*            Log.e(ScopeTextPresenter.class.getName(), "Cannot set RecyclerViewAdapter with"
                    + " Fragment: " + fragmentName.getName());*/
        }
        return result;
    }

    /**
     * Invokes FragmentAction implementation to add a new Fragment. Should determine what fragment
     * to add and to what View container based on the ScopeTextFragment fragmentName.
     *
     * @param fragmentName The ScopeTextFragment fragmentName.
     * @see FragmentAction
     * @see ScopeTextFragment
     */
    public void addFragment(ScopeTextFragment fragmentName) {
        if (fragmentName != null) {
            Fragment fragment = null;
            switch (fragmentName) {
                case SCOPE_TEXT_LIST:
                    fragment = ScopeTextListFragment.newInstance(presenter);
                    break;
                case NEW_CONTACT:
                    fragment = NewContactFragment.newInstance();
                    break;
            }
            fragmentAction.addFragment(R.id.scopetext_fragment, fragment, fragmentName);
        }
    }

    /**
     * Executes a specific SQL statement asynchronously.
     *
     * @param sql The SQL statement.
     * @param sqlTask The SQLTask instance to execute.
     * @return Whether or not the sql began execution successfully.
     */
    public boolean executeSQL(SQL sql, SQLTask sqlTask) {
        boolean beganExecution = false;
        if (sql != null && sqlTask != null) {
            switch (sql) {
                case SELECT_ALL_SCOPETEXTS_CONTACTS:
                    sqlTask.execute(dbHelper, sql);
                    beganExecution = true;
                    break;
            }
        }
        return beganExecution;
    }

    /**
     * Retrieves results from asynchronous SQLTask calls
     *
     * @param results The results from the SQL.
     */
    public void retrieveSQLTaskResults(List<Object> results) {
        if (results != null && !results.isEmpty()) {
            if (results.get(0) instanceof ScopeText) {

            }
        }
    }

    /**
     * Call back from the RecyclerViewAdapter.onBindView(), which is used to populate each View in
     * the ViewHolder. The dataSet is used to populate the view.
     *
     * @param viewHolder Used to retrieve the View at the dataSet position.
     * @param position The dataSet position.
     * @param dataSet The dataSet to populate the view.
     * @param fragmentName The name of the Fragment that uses the calling RecyclerViewAdapter.
     * @throws IllegalArgumentException Thrown if any of the arguments are not valid.
     * @throws IllegalStateException Thrown if the dataset is not in the correct state.
     *
     */
    public void onBindViewHolder(ScopeTextViewHolder viewHolder, int position,
                                 List<? extends Object> dataSet, ScopeTextFragment fragmentName)
            throws IllegalArgumentException {
        if(validBindViewHolderArguments(viewHolder, dataSet)) {
            if(ScopeTextFragment.SCOPE_TEXT_LIST == fragmentName) {
                ScopeText scopeText = (ScopeText) dataSet.get(position);
                String scopeTextName = scopeText.getName();

                if(scopeTextName != null && !scopeTextName.isEmpty()) {
                    ((TextView) viewHolder.getViewGroup().getChildAt(0)).setText(scopeText.getName());
                    ((TextView) viewHolder.getViewGroup().getChildAt(1))
                            .setText(scopeText.getContacts().get(0).getName());
                }
                else {
                    throw new IllegalStateException("ScopeText from dataSet is in an " +
                            "invalid state");
                }
            }
        }
        else {
            throw new IllegalArgumentException(
                    "Either ScopeTextViewHolder or dataSet have an invalid value.");
        }
    }

    void setupActionBar() {
        if (activity != null) {
            Toolbar toolbar = (Toolbar) activity.findViewById(R.id.actionBar);

            if (toolbar != null) {
                activity.setSupportActionBar(toolbar);
            }
        }
    }

    DBHelper getDbHelper() {
        return this.dbHelper;
    }

    private boolean validBindViewHolderArguments(ScopeTextViewHolder viewHolder,
                                                 List<? extends Object> dataSet) {
        return viewHolder != null && dataSet != null && !dataSet.isEmpty();
    }
}
