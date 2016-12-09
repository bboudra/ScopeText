package org.scopetext.presenter;

import android.app.Activity;

/**
 * The presenter component in the <a href=http://antonioleiva.com/mvp-android/ "MVP">MVP</a>
 * architecture. Middle man between the view and model components. Responsible for routing each UI
 * interaction from view, to a specific piece of business logic, and interacts with model if
 * necessary. Created by john.qualls on 11/28/2016.
 */
public interface Presenter {
    /**
     * Updates the Activity reference. This must be invoked when the Activity restarts, so
     * that the new reference propagates through the entire application.
     *
     * @param activity The new Activity reference.
     */
    public void activityRefresh(Activity activity);

    /**
     * Invokes FragmentAction implementation to add a new Fragment. Should determine what fragment
     * to add and to what View container based on the ScopeTextFragment type.
     *
     * @param type The ScopeTextFragment type.
     * @see FragmentAction
     * @see ScopeTextFragment
     */
    public void addFragment(ScopeTextFragment type);
}
