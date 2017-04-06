package org.scopetext.presenter.fragment;


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
     * Adds a new Fragment to a View container.
     *
     * @param containerId The View container id.
     * @param Fragment The new Fragment to add.
     * @param type Used to retrieve the tag name for the new Fragment.
     */
    public void addFragment(int containerId, Fragment Fragment, ScopeTextFragment type);

    /**
     * Retrieves the specified active Fragment.
     *
     * @param fragment The name of the Fragment to retrieve.
     * @return The specified Fragment, or null if it does not exist in the collection of active
     * Fragments.
     */
    public Fragment getFragment(ScopeTextFragment fragment);
}
