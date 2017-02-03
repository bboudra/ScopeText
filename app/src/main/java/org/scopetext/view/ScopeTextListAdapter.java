package org.scopetext.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.scopetext.model.javabean.ScopeText;
import org.scopetext.presenter.R;
import org.scopetext.presenter.databinding.FragmentScopetextRowBinding;

/**
 * Created by john.qualls on 2/2/2017.
 */

public class ScopeTextListAdapter extends BaseAdapter {
    private ObservableArrayList<ScopeText> list;
    private LayoutInflater inflater;

    public ScopeTextListAdapter(ObservableArrayList<ScopeText> l) {
        list = l;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        FragmentScopetextRowBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_scopetext_row, parent, false);

        //binding.setInfo(list.get(position));

        return binding.getRoot();
    }
}
