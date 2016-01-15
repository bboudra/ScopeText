package org.scopetext.scopetext;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Handles the UI when the user adds a new contact.
 */
public class NewContactFragment extends Fragment {

    public NewContactFragment() {
        // Required empty public constructor
    }

    /**
     * Replaces constructor.
     * @return A new instance of fragment NewContactFragment.
     */
    // TODO: Pass in parameters for fragment communication.
    public static NewContactFragment newInstance() {
        NewContactFragment fragment = new NewContactFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_contact, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
