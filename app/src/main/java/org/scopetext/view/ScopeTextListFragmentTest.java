package org.scopetext.view;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.scopetext.model.dao.SQL;
import org.scopetext.model.javabean.ScopeText;
import org.scopetext.model.schema.ContactContract;
import org.scopetext.model.schema.ScopeTextContract;
import org.scopetext.presenter.R;
import org.scopetext.presenter.ScopeTextPresenter;


/**
 * Handles the UI for displaying a list of contacts.
 */
public class ScopeTextListFragmentTest extends Fragment implements View.OnClickListener {
    private static final String SCOPETEXT_NAME = "ScopeText";
    private ScopeTextPresenter presenter;
    private ObservableList<ScopeText> scopeTexts;
    private int scopeTextCount;

    public static ScopeTextListFragmentTest newInstance(ScopeTextPresenter presenter) {
        ScopeTextListFragmentTest fragment = new ScopeTextListFragmentTest();
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
/*        FragmentScopetextRowBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_scopetext_list, container, false);
        View view = binding.getRoot();*/

        return inflater.inflate(R.layout.fragment_scopetext_list_test, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Setup Buttons
        Button addButton = (Button) getActivity().findViewById(R.id.AddButton),
               removeButton = (Button) getActivity().findViewById(R.id.RemoveButton);
        addButton.setOnClickListener(this);
        removeButton.setOnClickListener(this);

        // Setup test ScopeTexts
        presenter.setScopeTexts(new ObservableArrayList<ScopeText>());
        scopeTexts = presenter.getScopeTexts();
    }

    @Override
    public void onClick(View v) {
        ScopeText scopeText = new ScopeText();
        if(v.getId() == R.id.AddButton) {
            scopeText.setName(SCOPETEXT_NAME + scopeTextCount);
            scopeTexts.add(scopeText);
            scopeTextCount++;
        } else {
            scopeTexts.remove(scopeTexts.size() - 1);
        }
    }
}
