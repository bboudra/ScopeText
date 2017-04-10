package org.scopetext.presenter;

import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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
 * Presenter Implementation.
 * <p>
 * <pre>
 * NOTES:
 * 1. Not meant to be subclassed.
 * 2. Not thread safe.
 * 3. Singleton.
 * </pre>
 * Created by john.qualls on 11/28/2016.
 *
 * @see Presenter
 */
public class ScopeTextPresenter implements Presenter {
    private final static Presenter presenter = new ScopeTextPresenter();
    private DBHelper dbHelper;
    private FragmentAction fragmentAction;
    private Cache scopeTextCache;
    private List<RecyclerViewAdapter> recyclerViewAdapters;
    private AppCompatActivity activity;

    /*
     * Used for unit testing this singleton class. Params are used to mock out collaborators with
     * this class.
     */
    ScopeTextPresenter(DBHelper dbHelper, FragmentAction fragmentAction,
                       Cache scopeTextCache) {
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
    public static Presenter getInstance() {
        return presenter;
    }

    /**
     * @see Presenter#activityRefresh(AppCompatActivity activity, SQLiteOpenHelper dbHelper)
     */
    @Override
    public void activityRefresh(AppCompatActivity activity, SQLiteOpenHelper dbHelper) {
        if (activity != null) {
            fragmentAction.activityRefresh(activity);
            setupActionBar();
            this.dbHelper = (DBHelper) dbHelper;
        }
    }

    /**
     * @see Presenter#setRecyclerViewAdapter(ScopeTextFragment)
     */
    @Override
    public boolean setRecyclerViewAdapter(ScopeTextFragment fragmentName) {
        boolean result = false;
        RecyclerViewAdapter adapter = null;
        Fragment fragment = null;

        // Setup RecyclerViewAdapter based on Fragment name
        if(fragmentName == null) {
            // TODO put in logger class
/*            Log.e(ScopeTextPresenter.class.getName(), "Cannot set RecyclerViewAdapter with null"
            + " Fragment name.");*/
        }
        else if(fragmentName == ScopeTextFragment.SCOPE_TEXT_LIST) {
            // Get RecyclerView and LayoutManager
            fragment = fragmentAction.getFragment(fragmentName);
            RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.scopetext_list);
            RecyclerView.LayoutManager adapterItemLayout = new LinearLayoutManager(activity);

            // Setup adapter
            adapter = new ScopeTextListAdapter(scopeTextCache.getCache(), this);
            recyclerView.setAdapter(adapter);
        }
        else {
            // TODO put in logger class
/*            Log.e(ScopeTextPresenter.class.getName(), "Cannot set RecyclerViewAdapter with"
                    + " Fragment: " + fragmentName.getName());*/
        }
        return result;
    }

    /**
     * @see Presenter#addFragment(ScopeTextFragment)
     */
    @Override
    public void addFragment(ScopeTextFragment type) {
        if (type != null) {
            Fragment fragment = null;
            switch (type) {
                case SCOPE_TEXT_LIST:
                    fragment = ScopeTextListFragment.newInstance(presenter);
                    break;
                case NEW_CONTACT:
                    fragment = NewContactFragment.newInstance();
                    break;
            }
            fragmentAction.addFragment(R.id.scopetext_fragment, fragment, type);
        }
    }

    @Override
    public void executeSQL(SQL sql) {
        switch (sql) {
            case SELECT_ALL_SCOPETEXTS_CONTACTS:
                new SQLTask(presenter).execute(dbHelper, sql);
                break;
        }
    }

    @Override
    public void retrieveSQLTaskResults(List<Object> results) {
        if (results != null && !results.isEmpty()) {
            if (results.get(0) instanceof ScopeText) {

            }
        }
    }

    @Override
    public RecyclerView.ViewHolder initializeViewHolder(ScopeTextFragment fragmentName) {
        return null;
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
}
