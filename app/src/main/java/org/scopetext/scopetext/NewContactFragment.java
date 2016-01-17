package org.scopetext.scopetext;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

//    @Override
//    public void onStop() {
//        super.onStop();
//        Log.i("Test", "New Contact Fragment onStop()");
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        Log.i("Test", "New Contact Fragment onPause()");
//    }
//
//    @Override
//    public void onResume() {
//        Log.i("Test", "New Contact Fragment onResume()");
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("Test", "New Contact Fragment onCreateView()");
        return inflater.inflate(R.layout.fragment_new_contact, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Test", "New Contact Fragment onDetach()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Test", "New Contact Fragment onDestroy()");
    }

}
