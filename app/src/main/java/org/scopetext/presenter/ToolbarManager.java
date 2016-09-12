package org.scopetext.presenter;

import android.support.v7.widget.Toolbar;

import org.scopetext.presenter.constant.ViewId;
import org.scopetext.view.MainActivity;

/**
 * Manages all operations for the app's ActionBar.
 * Created by john.qualls on 8/13/2016.
 */
public class ToolbarManager {
    private Toolbar toolbar;

    /**
     * Initializes the ActionBar.
     * @param activity Used to setup the app's ActionBar.
     */
    public ToolbarManager(MainActivity activity) {
        if(activity != null) {
            toolbar = (Toolbar) activity.findViewById(ViewId.ACTION_BAR);

            if(toolbar != null) {
                activity.setSupportActionBar(toolbar);
            } else
                throw new NullPointerException("Null Toolbar because Android API couldn't " +
                        "retrieve the associated view");
        } else
            throw new IllegalArgumentException("Cannot have null parameters during " +
                    "ToolBarManager initialization.");
    }
}
