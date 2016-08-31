package org.scopetext.presenter;

import android.support.v7.widget.Toolbar;

import org.scopetext.view.MainActivity;

/**
 * Manages all operations for the app's ActionBar.
 * Created by john.qualls on 8/13/2016.
 */
public class ToolbarManager {
    private final static String LOG_TAG = "ToolBarManager";
    private static ToolbarManager toolbarManager;
    private Toolbar toolbar;

    /**
     * Initializes the ActionBar
     * @param activity Used to setup the app's ActionBar.
     */
    private ToolbarManager(MainActivity activity) {
        toolbar = (Toolbar) activity.findViewById(R.id.actionBar);
        activity.setSupportActionBar(toolbar);
    }

    /**
     * Static factory method that provides instance control for this singleton class.
     * @param activity Used to setup the apps ActionBar.
     * @return ToolBarManager a reference to a singleton or null if there was an error with the
     * constructor initialization.
     */
    public static ToolbarManager getInstance(MainActivity activity) {
        if(toolbarManager == null && activity != null) {
            toolbarManager = new ToolbarManager(activity);
        }

        return toolbarManager;
    }
}
