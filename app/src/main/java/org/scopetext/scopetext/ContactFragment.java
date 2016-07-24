package org.scopetext.scopetext;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Handles the UI for displaying list of contacts.<br/>
 */
public class ContactFragment extends Fragment {

    public ContactFragment() {
        // Required empty public constructor
    }

    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        Log.i("Test", "Contact Fragment onStop()");
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        Log.i("Test", "Contact Fragment onPause()");
//    }
//
//    @Override
//    public void onResume() {
//        Log.i("Test", "Contact Fragment onResume()");
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("Test", "Contact Fragment onCreateView()");
        return inflater.inflate(R.layout.fragment_scope_text_detail, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Test", "Contact Fragment onDetach()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Test", "Contact Fragment onDestroy()");
    }
}
