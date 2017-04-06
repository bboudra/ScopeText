package org.scopetext.presenter;

import org.scopetext.presenter.fragment.FragmentAction;
import org.scopetext.presenter.fragment.ScopeTextFragment;
import org.scopetext.view.ScopeTextListFragment;

/**
 * Initializes RecyclerViewAdapter implementations. Created by john.qualls on 4/3/2017.
 */
public class RecyclerViewAdapterFactory {

    /**
     * Initializes RecyclerViewAdapter implementations.
     *
     * @param fragment The Fragment fragment.
     * @param fragment Used to retrieve the Fragment to apply the adapter.
     * @return The RecyclerViewAdapter instance that corresponds to the fragment.
     * @throws IllegalArgumentException Thrown if an invalid fragment is passed.
     * @throws NullPointerException Thrown if null name is passed.
     */
    public static RecyclerViewAdapter buildAdapter(ScopeTextFragment fragment, FragmentAction fragmentAction)
            throws IllegalArgumentException {
        RecyclerViewAdapter adapter = null;
        if (fragment == null || fragmentAction == null) {
            throw new NullPointerException("Argument(s) is null.");
        }
        else if (ScopeTextFragment.SCOPE_TEXT_LIST == fragment) {
            adapter = new ScopeTextAdapter();
        }
        else {
            throw new IllegalArgumentException("No RecyclerViewAdapter exists for: " +
                    fragment.getName());
        }
        return adapter;
    }
}
