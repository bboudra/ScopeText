package org.scopetext.presenter;

import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.scopetext.model.dao.DBHelper;
import org.scopetext.model.dao.SQL;
import org.scopetext.model.dao.SQLTask;
import org.scopetext.model.javabean.ScopeText;
import org.scopetext.model.fragment.FragmentAction;
import org.scopetext.model.fragment.ScopeTextFragment;
import org.scopetext.model.fragment.ScopeTextFragmentAction;
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

    /*
     * Used for unit testing this singleton class. Params are used to mock out collaborators with
     * this class.
     */
    ScopeTextPresenter(DBHelper dbHelper, FragmentAction fragmentAction) {
        this.dbHelper = dbHelper;
        this.fragmentAction = fragmentAction;
    }

    /**
     * Initializes the following application components: <PRE> 1. Fragment Action. <PRE/>
     *
     * @see FragmentAction
     */
    private ScopeTextPresenter() {
        fragmentAction = ScopeTextFragmentAction.getInstance();
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
            setupActionBar(activity);
            this.dbHelper = (DBHelper) dbHelper;
        }
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

    void setupActionBar(AppCompatActivity activity) {
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
