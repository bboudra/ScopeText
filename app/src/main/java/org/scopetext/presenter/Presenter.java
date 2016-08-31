package org.scopetext.presenter;

import org.scopetext.database.dao.DBHelper;

/**
 * The presenter component in the
 * <a href=http://antonioleiva.com/mvp-android/ "MVP">MVP</a> architecture.
 * Middle man between the view and model components.
 * Responsible for routing each UI interaction from view, to a specific piece of business logic,
 * and interacts with model if necessary.
 * Created by john.qualls on 8/24/2016.
 */
public class Presenter {
    private static Presenter presenter;
    private DBHelper dbHelper;
    private ToolbarManager toolbarManager;
    private ScopeTextFragmentManager stFragManager;

    /**
     * Initialization that is required upon app startup.
     *
     */
    public Presenter(DBHelper dbHelper, ToolbarManager toolbarManager,
                     ScopeTextFragmentManager stFragManager) throws IllegalArgumentException {


        if(dbHelper != null && toolbarManager != null && stFragManager != null) {
            this.dbHelper = dbHelper;
            this.toolbarManager = toolbarManager;
            this.stFragManager = stFragManager;
        }
        else
            throw new IllegalArgumentException(ExceptionMessage.nullParams);
    }

    /**
     * Getter method for the ToolbarManager singleton instance field.
     */
    protected ToolbarManager getToolbarManager() {
        return this.toolbarManager;
    }

}
