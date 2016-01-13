package org.scopetext.scopetext;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Handles the UI for displaying list of contacts.
 */
public class ContactFragment extends Fragment {
    private FragmentCommunicationListener mListener;

    public ContactFragment() {
        // Required empty public constructor
    }

    public static ContactFragment newInstance() {
        ContactFragment fragment = new ContactFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
