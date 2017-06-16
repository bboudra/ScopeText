package org.scopetext.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.scopetext.model.dao.SQL;
import org.scopetext.model.dao.SQLTask;
import org.scopetext.presenter.R;
import org.scopetext.presenter.ScopeTextPresenter;
import org.scopetext.presenter.fragment.ScopeTextFragment;


/**
 * Handles the UI for displaying a list of contacts.
 */
public class ScopeTextListFragment extends Fragment {
    private ScopeTextPresenter presenter;

    public static ScopeTextListFragment newInstance(ScopeTextPresenter presenter) {
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
        presenter.setRecyclerViewAdapter(ScopeTextFragment.SCOPE_TEXT_LIST);
        presenter.executeSQL(SQL.SELECT_ALL_SCOPETEXTS_CONTACTS, new SQLTask(presenter));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
