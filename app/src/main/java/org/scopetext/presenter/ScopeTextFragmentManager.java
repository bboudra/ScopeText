package org.scopetext.presenter;

/**
 * Handles all fragment transactions, such as initializing and swaping fragments into a layout.
 * Created by john.qualls on 8/13/2016.
 */
public class ScopeTextFragmentManager {
    private static ScopeTextFragmentManager scopeTextFragmentManager;

    private ScopeTextFragmentManager() {

    }

    public static ScopeTextFragmentManager getInstance() {
        if(scopeTextFragmentManager == null) {
            scopeTextFragmentManager = new ScopeTextFragmentManager();
        }

        return scopeTextFragmentManager;
    }
}
