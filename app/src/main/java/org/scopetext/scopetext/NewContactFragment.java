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

    private FragmentCommunicationListener mListener;

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
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
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
        // Ensure Parent Context is a
//        if (context instanceof FragmentCommunicationListener) {
//            mListener = (FragmentCommunicationListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement FragmentCommunicationListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
