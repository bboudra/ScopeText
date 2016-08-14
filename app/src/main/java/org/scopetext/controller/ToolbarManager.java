package org.scopetext.controller;

/**
 * Manages all operations for the app's ActionBar.
 * Created by john.qualls on 8/13/2016.
 */
public class ToolbarManager {
    private static ToolbarManager toolbarManager;

    private ToolbarManager() {

    }

    public static ToolbarManager getInstance() {
        if(toolbarManager == null) {
            toolbarManager = new ToolbarManager();
        }

        return toolbarManager;
    }
}
