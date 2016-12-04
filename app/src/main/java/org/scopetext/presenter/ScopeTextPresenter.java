package org.scopetext.presenter;

import android.database.sqlite.SQLiteDatabase;

import org.scopetext.model.dao.DBHelper;
import org.scopetext.model.dao.ScopeTextDAO;
import org.scopetext.model.javabean.ScopeText;
import org.scopetext.view.MainActivity;

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
    private ScopeTextFragmentAction stFragManager;
    private ArrayList<ScopeText> scopeTexts;

    /*
     * Used for unit testing this singleton class. Params are used to mock out collaborators with
     * this class.
     */
    protected ScopeTextPresenter(DBHelper dbHelper, ToolbarManager toolbarManager,
                                 ScopeTextFragmentAction stFragManager) {

    }

    private ScopeTextPresenter() {
        // TODO Refactor collaborators.
        /*
        DBHelper dbHelper = new DBHelper();
        ToolbarManager toolbarManager = new ToolbarManager();
        ScopeTextFragmentAction stFragManager = new ScopeTextFragmentAction();


        if (dbHelper != null && toolbarManager != null && stFragManager != null) {
            this.dbHelper = dbHelper;
            this.toolbarManager = toolbarManager;
            this.stFragManager = stFragManager;
        } else {
            throw new IllegalArgumentException(
                    "Cannot have null parameters during Presenter " + "initialization.");
        }

        // Initialize ScopeTextListFragment
        ScopeTextListFragment stFragment = ScopeTextListFragment.newInstance();
        stFragManager.addFragment(stFragment);
        */
    }

    /**
     * Gets a reference to this singleton.
     *
     * @return The reference.
     */
    public static Presenter getInstance() {
        return ScopeTextPresenter.presenter;
    }

    /**
     * @see Presenter#updateMainActivity(MainActivity)
     */
    public void updateMainActivity(MainActivity mainActivity) {

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
