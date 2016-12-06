package org.scopetext.presenter;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import org.scopetext.model.dao.DBHelper;
import org.scopetext.model.dao.ScopeTextDAO;
import org.scopetext.model.javabean.ScopeText;

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
    private ToolbarManager toolbarManager;
    private FragmentAction fragmentAction;
    private ArrayList<ScopeText> scopeTexts;

    /*
     * Used for unit testing this singleton class. Params are used to mock out collaborators with
     * this class.
     */
    protected ScopeTextPresenter(DBHelper dbHelper, ToolbarManager toolbarManager,
                                 FragmentAction fragmentAction) {
        this.dbHelper = dbHelper;
        this.toolbarManager = toolbarManager;
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
     * @see Presenter#activityRefresh(AppCompatActivity activity)
     */
    public void activityRefresh(AppCompatActivity activity) {
        if(activity != null) {
            fragmentAction.activityRefresh(activity);
        }
    }

    /**
     * @see Presenter#addFragment(ScopeTextFragment)
     */
    public void addFragment(ScopeTextFragment type) {

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

    // TODO Refactor methods along with refactoring collaborators.

    /**
     * Getter method for the ToolbarManager singleton instance field.
     */
    protected ToolbarManager getToolbarManager() {
        return this.toolbarManager;
    }
}
