package org.scopetext.presenter;

import android.view.ViewGroup;

/**
 * Interface to interact with a RecyclerViewAdapter implementation's internal ViewHolder.
 * Created by john.qualls on 4/24/2017.
 */
public interface ScopeTextViewHolder {
    /**
     * Retrieves a reference to the ViewGroup of the ViewHolder.
     * @return The ViewGroup reference.
     */
    public ViewGroup getViewGroup();
}
