package org.scopetext.presenter;

import org.scopetext.view.NewContactFragment;
import org.scopetext.view.ScopeTextListFragment;

/**
 * ScopeTextFragment encapsulates constants that contain information about ScopeText fragments.
 * Created by john.qualls on 12/3/2016.
 */

public enum ScopeTextFragment {
    SCOPE_TEXT_LIST(ScopeTextListFragment.class.getName()),
    NEW_CONTACT(NewContactFragment.class.getName());

    private String name;

    private ScopeTextFragment(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
