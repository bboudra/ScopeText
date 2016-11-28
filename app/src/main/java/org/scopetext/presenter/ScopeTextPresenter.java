package org.scopetext.presenter;

import android.database.sqlite.SQLiteDatabase;

import org.scopetext.model.dao.DBHelper;
import org.scopetext.model.dao.ScopeTextDAO;
import org.scopetext.model.javabean.ScopeText;
import org.scopetext.view.MainActivity;

import java.util.ArrayList;

/**
 * Presenter Implementation. So far, this is the only implementation.
 * Created by john.qualls on 11/28/2016.
 * @see Presenter
 */
public class ScopeTextPresenter implements Presenter {
    private static Presenter presenter = new ScopeTextPresenter();
    private DBHelper dbHelper;
    private ToolbarManager toolbarManager;
    private ScopeTextFragmentManager stFragManager;
    private ArrayList<ScopeText> scopeTexts;

    private ScopeTextPresenter() {
        // TODO Refactor collaborators
        /*
        DBHelper dbHelper = new DBHelper();
        ToolbarManager toolbarManager = new ToolbarManager();
        ScopeTextFragmentManager stFragManager = new ScopeTextFragmentManager();


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

    // TODO Refactor methods along with refactoring collaborators
    /**
     * Getter method for the ToolbarManager singleton instance field.
     */
    protected ToolbarManager getToolbarManager() {
        return this.toolbarManager;
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

    /**
     * Updates the MainActivity reference. This must be invoked when the MainActivity restarts,
     * so that the new reference propagates through the entire application.
     * @see org.scopetext.view.MainActivity
     */
    public void updateMainActivity(MainActivity mainActivity) {

    }
}
