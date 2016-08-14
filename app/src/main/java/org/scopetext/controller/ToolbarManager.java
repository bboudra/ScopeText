package org.scopetext.controller;

/**
 * Manages all operations for the app's ActionBar.
 * Created by john.qualls on 8/13/2016.
 */
public class ToolbarManager {
    private final static String LOG_TAG = "ToolBarManager";
    private static ToolbarManager toolbarManager;

    /**
     * Initializes the ActionBar for this app.
     * @param activity Used to setup the apps ActionBar.
     */
    private ToolbarManager() {

    }

    /**
     * Public static factory method that provides instance control for this singleton class.
     * @param activity Used to setup the apps ActionBar.
     * @return ToolBarManager a reference to a singleton or null if there was an error with the
     * constructor initialization.
     */
    public static ToolbarManager getInstance(MainActivity activity) {
        if(toolbarManager == null) {
            toolbarManager = new ToolbarManager();
        }

        return toolbarManager;
    }
}
