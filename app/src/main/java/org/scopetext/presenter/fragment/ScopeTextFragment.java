package org.scopetext.presenter.fragment;

import org.scopetext.view.NewContactFragment;
import org.scopetext.view.ScopeTextListFragment;
import org.scopetext.view.ScopeTextListFragmentTest;

/**
 * ScopeTextFragment encapsulates constants that contain information about ScopeText fragments.
 * Created by john.qualls on 12/3/2016.
 */

public enum ScopeTextFragment {
    SCOPE_TEXT_LIST(ScopeTextListFragment.class.getName()),
    SCOPE_TEXT_LIST_TEST(ScopeTextListFragmentTest.class.getName()),
    NEW_CONTACT(NewContactFragment.class.getName());

    private String name;

    private ScopeTextFragment(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
