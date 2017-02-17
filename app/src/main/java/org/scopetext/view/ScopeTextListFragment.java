package org.scopetext.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.scopetext.model.dao.SQL;
import org.scopetext.presenter.Presenter;
import org.scopetext.presenter.R;


/**
 * Handles the UI for displaying a list of contacts.
 */
public class ScopeTextListFragment extends Fragment {
    private Presenter presenter;

    public static ScopeTextListFragment newInstance(Presenter presenter) {
        ScopeTextListFragment fragment = new ScopeTextListFragment();
        fragment.presenter = presenter;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scopetext_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.executeSQL(SQL.SELECT_ALL_SCOPETEXTS_CONTACTS);
    }
}
