package org.scopetext.presenter;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * FragmentAction makes it simpler to interact with the Android FragmentManager, and execute
 * Fragment transactions. Created by john.qualls on 12/2/2016.
 *
 * @see android.app.FragmentManager
 * @see android.app.Fragment
 */

public interface FragmentAction {

    /**
     * Re assigns a new Activity reference, and retrieves a reference to its FragmentManager.
     *
     * @param activity The new Activity reference.
     */
    public void activityRefresh(AppCompatActivity activity);

    /**
     * Adds a new fragment to a View container.
     *
     * @param containerId The View container id.
     * @param fragment The new Fragment to add.
     * @param type Used to retrieve the tag name for the new Fragment.
     */
    public void addFragment(int containerId, Fragment fragment, ScopeTextFragment type);
}
