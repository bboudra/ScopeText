package org.scopetext.view;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.scopetext.model.javabean.ScopeText;

/**
 * Created by john.qualls on 2/2/2017.
 */

public class ListBinder {
    @BindingAdapter("bind:items")
    public static void bindList(ListView view, ObservableArrayList<ScopeText> list) {
        ListAdapter adapter = new ScopeTextListAdapter(list);
        view.setAdapter(adapter);
    }
}
