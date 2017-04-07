package org.scopetext.presenter.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.scopetext.view.ScopeTextListFragment;

/**
 * FragmentAction implementation.
 * <p>
 * <pre>
 * NOTES:
 * 1. Not meant to be subclassed.
 * 2. Not thread safe.
 * 3. Singleton.
 * </pre>
 * Created by john.qualls on 8/13/2016.
 *
 * @see FragmentAction
 */
public class ScopeTextFragmentAction implements FragmentAction {
    private final static ScopeTextFragmentAction scopeTextFragmentAction =
            new ScopeTextFragmentAction();
    private FragmentManager fragmentManager;

    /*
     * Used for unit testing this singleton class. Params are used to mock out collaborators with
     * this class.
     */
    protected ScopeTextFragmentAction(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    private ScopeTextFragmentAction() {

    }

    /**
     * Retrieves a reference to this singleton.
     */
    public static ScopeTextFragmentAction getInstance() {
        return scopeTextFragmentAction;
    }

    /**
     * @see FragmentAction#activityRefresh(AppCompatActivity)
     */
    @Override
    public void activityRefresh(AppCompatActivity activity) {
        if (activity != null) {
            this.fragmentManager = activity.getSupportFragmentManager();
        }
    }

    /**
     * Adds a new Fragment instance to the FragmentManager, and commits it, only if the fragment is
     * the ScopeTextList Fragment. Otherwise the current Fragment is added to the back stack and
     * replaced.
     *
     * @param containerId The View container id.
     * @param fragment The new Fragment to add.
     * @param type Used to retrieve the tag name for the new Fragment.
     * @see FragmentAction#addFragment(int, Fragment, ScopeTextFragment)
     */
    @Override
    public void addFragment(int containerId, Fragment fragment, ScopeTextFragment type) {
        if (fragment != null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (fragment instanceof ScopeTextListFragment) {
                transaction.add(containerId, fragment, type.getName());
            } else {
                transaction.replace(containerId, fragment, type.getName());
                transaction.addToBackStack(null);
            }
            transaction.commit();
        }
    }

    @Override
    public Fragment getFragment(ScopeTextFragment fragment) {
        return null;
    }

    protected FragmentManager getFragmentManager() {
        return this.fragmentManager;
    }
}
