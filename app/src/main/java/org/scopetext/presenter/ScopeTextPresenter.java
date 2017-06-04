package org.scopetext.presenter;

import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.common.base.Preconditions;

import org.scopetext.model.cache.Cache;
import org.scopetext.model.cache.ScopeTextCache;
import org.scopetext.model.dao.ContactAssocDAO;
import org.scopetext.model.dao.ContactDAO;
import org.scopetext.model.dao.DBHelper;
import org.scopetext.model.dao.SQL;
import org.scopetext.model.dao.SQLTask;
import org.scopetext.model.dao.ScopeTextDAO;
import org.scopetext.model.javabean.Contact;
import org.scopetext.model.javabean.ScopeText;
import org.scopetext.presenter.fragment.FragmentAction;
import org.scopetext.presenter.fragment.ScopeTextFragment;
import org.scopetext.presenter.fragment.ScopeTextFragmentAction;
import org.scopetext.view.NewContactFragment;
import org.scopetext.view.ScopeTextListFragment;
import org.w3c.dom.Text;

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
    private ScopeTextListAdapter scopeTextListAdapter;
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
        Fragment fragment = null;

        // Setup RecyclerViewAdapter based on Fragment name
        if (fragmentName == null) {
            // TODO Throw IllegalArgumentException
/*            Log.e(ScopeTextPresenter.class.getName(), "Cannot set RecyclerViewAdapter with null"
            + " Fragment name.");*/
        } else if (fragmentName == ScopeTextFragment.SCOPE_TEXT_LIST) {
            // Get RecyclerView and LayoutManager
            RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.scopetext_list);
            RecyclerView.LayoutManager adapterItemLayout = new LinearLayoutManager(activity);
            recyclerView.setLayoutManager(adapterItemLayout);

            // Setup adapter
            scopeTextListAdapter = new ScopeTextListAdapter(scopeTextCache.getCache(), this);
            recyclerView.setAdapter(scopeTextListAdapter);
            result = true;
        } else {
            // TODO Throw IllegalArgumentException
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
     * Retrieves results from asynchronous SQLTask calls, and updates the appropriate cache.
     *
     * @param results The results from the SQLTask call.
     */
    public <T> void retrieveSQLTaskResults(List<T> results) {
        if (results != null && !results.isEmpty()) {
            if(results.get(0) instanceof ScopeText){
                scopeTextCache.updateCache(results);
                scopeTextListAdapter.notifyItemRangeInsertedWrapper(0, results.size());
            }
        }
        else {
            // TODO Make logger class
            /*Log.d("", "No results were retrieved from the SQLTask execution.");*/
        }
    }

    /**
     * Call back from a RecyclerView.Adapter subclass onBindView() method, which is used to
     * populate each View in the ViewHolder with a data element, retrieved from the dataset.
     *
     * @param viewHolder Used to retrieve the View at the dataSet position.
     * @param position The dataSet position.
     * @param dataSet The dataSet that contains the data element.
     * @throws NullPointerException Thrown for the following conditions:<br/>
     *          viewHolder
     *          <ul style="list-style-type:disc">
     *              <li>viewHolder is null.</li>
     *              <li>LinearLayout is null.</li>
     *              <li>Either TextView is null.</li>
     *          </ul>
     *          ScopeText dataSet
     *          <ul style="list-style-type:disc">
     *          <li>dataset is null.</li>
     *          <li>ScopeText is null.</li>
     *          <li>ScopeText has a null name.</li>
     *          <li>Contact list is null.</li>
     *          <li>Contact is null.</li>
     *          <li>Contact name is null.</li>
     *          </ul>
     * @throws IndexOutOfBoundsException Thrown for the following conditions:<br/>
     *          Position
     *          <ul style="list-style-type:disc">
     *              <li>position is outside the range of the dataset.</li>
     *          </ul>
     * @throws IllegalArgumentException Thrown for the following conditions:<br/>
     *          ScopeText dataSet
     *          <ul style="list-style-type:disc">
     *              <li>dataSet is empty.</li>
     *              <li>ScopeText has a empty name.</li>
     *              <li>Contact list is empty.</li>
     *              <li>Contact name is empty.</li>
     *          </ul>
     *
     * @see RecyclerView
     * @see ScopeText
     */
    public void onBindViewHolder(ScopeTextViewHolder viewHolder, int position,
                                 List<ScopeText> dataSet) {
        // Validate arguments
        Preconditions.checkNotNull(viewHolder, "ViewHolder parameter cannot be null");
        LinearLayout linearLayout = (LinearLayout) viewHolder.getViewGroup();
        Preconditions.checkNotNull(linearLayout, "LinearLayout from ViewHolder parameter cannot " +
                "be null");
        TextView scopeTextNameView = (TextView) linearLayout.getChildAt(0);
        Preconditions.checkNotNull(scopeTextNameView, "ScopeText TextView from ViewHolder " +
                "parameter cannot be null");
        Preconditions.checkNotNull(dataSet, "dataSet parameter cannot be null");
        if(dataSet.isEmpty()) throw new IllegalArgumentException("dataSet parameter cannot be " +
                "EMPTY");
        Preconditions.checkElementIndex(position, dataSet.size(), "Position parameter is out of" +
                " the dataset bounds");
        ScopeText scopeText = dataSet.get(position);
        Preconditions.checkNotNull(scopeText, "ScopeText from dataset parameter cannot be null");
        String scopeTextName = scopeText.getName();
        Preconditions.checkNotNull(scopeTextName, "ScopeText name from dataset parameter cannot" +
                " be null");
        if(scopeTextName.isEmpty()) throw new IllegalArgumentException("ScopeText name from dataset " +
                "parameter cannot be empty");
        List<Contact> contacts = scopeText.getContacts();
        Preconditions.checkNotNull(contacts, "Contact list from dataset parameter cannot be null");
        if(contacts.isEmpty()) throw new IllegalArgumentException("Contact list from dataset " +
                "parameter cannot be empty");
        TextView contactView = (TextView) linearLayout.getChildAt(1);
        Preconditions.checkNotNull(contactView, "Contact TextView from ViewHolder parameter " +
                "cannot be null");


        // Set ScopeText name
        scopeTextNameView.setText(scopeText.getName());

        // Set contact name
        for (Contact contact : contacts) {
            if (contact != null) {
                if (!contact.isInList()) {
                    String contactName = contact.getName();
                    if(contactName != null) {
                        if (!contactName.isEmpty()) {
                            contactView.setText(contact.getName());
                            contact.setInList(true);
                        } else throw new IllegalArgumentException("Contact name from dataset " +
                                "parameter cannot be empty");
                    } else throw new NullPointerException("Contact name from dataset parameter " +
                            "cannot be null");
                }
            } else throw new NullPointerException("Contact from dataset parameter cannot be null");
        }
    }

    // TODO Comment out when not testing.
    /*
     * Currently just used for testing.
     */
    public void createTestDB() {
        // Delete all data
        ScopeTextDAO.delete(dbHelper.getWriteableDB());
        ContactDAO.delete(dbHelper.getWriteableDB());
        ContactAssocDAO.delete(dbHelper.getWriteableDB());

        // Insert data
        ScopeTextDAO.insertScopeText(dbHelper.getWriteableDB());
        ContactDAO.insertContact(dbHelper.getWriteableDB());
        ContactAssocDAO.insertContactAssoc(dbHelper.getWriteableDB());
    }

    /**
     * Closes all open resources
     */
    public void shutdown() {
        if(dbHelper != null) {
            dbHelper.close();
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

    /*
     * Unit testing getters and setters
     */

    void setScopeTextListAdapter(ScopeTextListAdapter scopeTextListAdapter) {
        this.scopeTextListAdapter = scopeTextListAdapter;
    }

    DBHelper getDbHelper() {
        return this.dbHelper;
    }
}
