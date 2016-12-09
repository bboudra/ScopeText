package org.scopetext.presenter;

import android.app.Activity;
import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;

import org.scopetext.model.dao.DBHelper;
import org.scopetext.model.dao.ScopeTextDAO;
import org.scopetext.model.javabean.ScopeText;
import org.scopetext.view.NewContactFragment;
import org.scopetext.view.ScopeTextListFragment;

import java.util.ArrayList;

/**
 * Presenter Implementation.
 *
 * <pre>
 * NOTES:
 * 1. Not meant to be subclassed.
 * 2. Not thread safe.
 * 3. Singleton class.
 * </pre>
 * Created by john.qualls on 11/28/2016.
 *
 * @see Presenter
 */
public class ScopeTextPresenter implements Presenter {
    private final static Presenter presenter = new ScopeTextPresenter();
    private DBHelper dbHelper;
    private FragmentAction fragmentAction;
    private ArrayList<ScopeText> scopeTexts;

    /*
     * Used for unit testing this singleton class. Params are used to mock out collaborators with
     * this class.
     */
    protected ScopeTextPresenter(DBHelper dbHelper, FragmentAction fragmentAction) {
        this.dbHelper = dbHelper;
        this.fragmentAction = fragmentAction;
    }

    private ScopeTextPresenter() {
        fragmentAction = ScopeTextFragmentAction.getInstance();
        // TODO Refactor collaborators.
        /*
        DBHelper dbHelper = new DBHelper();
        ToolbarManager toolbarManager = new ToolbarManager();
        ScopeTextFragmentAction fragmentAction = new ScopeTextFragmentAction();


        if (dbHelper != null && toolbarManager != null && fragmentAction != null) {
            this.dbHelper = dbHelper;
            this.toolbarManager = toolbarManager;
            this.fragmentAction = fragmentAction;
        } else {
            throw new IllegalArgumentException(
                    "Cannot have null parameters during Presenter " + "initialization.");
        }

        // Initialize ScopeTextListFragment
        ScopeTextListFragment stFragment = ScopeTextListFragment.newInstance();
        fragmentAction.addFragment(stFragment);
        */
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
     * @see Presenter#activityRefresh(android.app.Activity activity)
     */
    public void activityRefresh(Activity activity) {
        if(activity != null) {
            fragmentAction.activityRefresh(activity);
        }
    }

    /**
     * @see Presenter#addFragment(ScopeTextFragment)
     */
    public void addFragment(ScopeTextFragment type) {
        if(type != null) {
            Fragment fragment = null;
            switch (type) {
                case SCOPE_TEXT_LIST:
                    fragment = ScopeTextListFragment.newInstance();
                    break;
                case NEW_CONTACT:
                    fragment = NewContactFragment.newInstance();
                    break;
            }
            fragmentAction.addFragment(R.id.scopetext_fragment, fragment, type);
        }
    }

    /**
     * Reads all of the ScopeText objects from the database.
     */
    public void getAllScopeTexts() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        scopeTexts = ScopeTextDAO.getAllScopeTexts(db, scopeTexts);
    }

    protected ArrayList<ScopeText> getScopeTexts() {
        return scopeTexts;
    }

    protected void setScopeTexts(ArrayList<ScopeText> scopeTexts) {
        this.scopeTexts = scopeTexts;
    }
}
