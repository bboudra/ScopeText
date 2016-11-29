package org.scopetext.presenter;

import org.scopetext.view.MainActivity;

/**
 * The presenter component in the <a href=http://antonioleiva.com/mvp-android/ "MVP">MVP</a>
 * architecture. Middle man between the view and model components. Responsible for routing each UI
 * interaction from view, to a specific piece of business logic, and interacts with model if
 * necessary. Created by john.qualls on 11/28/2016.
 */
public interface Presenter {
    /**
     * Updates the MainActivity reference. This must be invoked when the MainActivity restarts, so
     * that the new reference propagates through the entire application.
     *
     * @see org.scopetext.view.MainActivity
     */
    public void updateMainActivity(MainActivity mainActivity);
}
