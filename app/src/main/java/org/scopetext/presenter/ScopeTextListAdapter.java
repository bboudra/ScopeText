package org.scopetext.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.scopetext.model.javabean.ScopeText;

import java.util.List;

/**
 * Implementation of RecyclerViewAdapter that dynamically populates a list of all ScopeTexts and
 * corresponding Contacts via a LinearLayout for each item.
 * Created by john.qualls on 4/9/2017.
 */

public class ScopeTextListAdapter extends RecyclerView.Adapter<ScopeTextListAdapter.ViewHolder> {
    private List<ScopeText> scopeTexts;
    private ScopeTextPresenter presenter;

    /**
     * Creates a new instance with a data set that is used for display.
     * @param scopeTexts The data set.
     * @param presenter The presenter to handle business logic.
     */
    public ScopeTextListAdapter (List<ScopeText> scopeTexts, ScopeTextPresenter presenter){
        this.scopeTexts = scopeTexts;
        this.presenter = presenter;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements ScopeTextViewHolder {
        private LinearLayout linearLayout;

        public ViewHolder(LinearLayout v) {
            super(v);
            linearLayout = v;
        }

        @Override
        public ViewGroup getViewGroup() {
            return linearLayout;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_scopetext_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        presenter.onBindViewHolder(holder, position, scopeTexts);
    }

    @Override
    public int getItemCount() {
        return scopeTexts.size();
    }

    /**
     * @see android.support.v7.widget.RecyclerView.Adapter#notifyItemRangeInserted(int, int)
     */
    public void notifyItemRangeInsertedWrapper(int poisitionStart, int itemCount) {
        super.notifyItemRangeInserted(poisitionStart, itemCount);
    }
}
