package org.scopetext.presenter;

import android.support.v4.app.FragmentManager;

import org.scopetext.view.MainActivity;
import org.scopetext.view.ViewInterface;

/**
 * Handles all fragment transactions, such as initializing and swapping fragments into a layout.
 * Created by john.qualls on 8/13/2016.
 */
public class ScopeTextFragmentManager {
    private static ScopeTextFragmentManager scopeTextFragmentManager;
    private FragmentManager fragmentManager;

    /**
     * Retrieves the FragmentManager.
     * @param activity The activity necessary to retrieve the FragmentManager.
     */
    public ScopeTextFragmentManager(MainActivity activity) {
        fragmentManager = activity.getSupportFragmentManager();
    }

    /**
     * Adds a new Fragment instance to the FragmentManager, and commits it.
     * @param fragment The new fragment to add.
     * @return Whether or not the fragment was added.
     */
    public boolean addFragment(ViewInterface fragment) {
        boolean added = true;

        /*FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(layoutID, fragment);
        if(transaction.commit() != -1)
            added = true;*/

        return added;
    }
}
