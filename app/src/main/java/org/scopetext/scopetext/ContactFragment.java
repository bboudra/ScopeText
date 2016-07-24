package org.scopetext.scopetext;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.scopetext.scopetext.SampleContract.Test;


/**
 * Handles the UI for displaying list of contacts.<br/>
 */
public class ContactFragment extends Fragment implements Button.OnClickListener {
    private final String[] projection = {
        Test.CONTACT_NAME
    };
    private SQLiteDatabase dbWrite,
                           dbRead;
    public ContactFragment() {
        // Required empty public constructor
    }

    public static ContactFragment newInstance(SampleDBHelper dbHelper) {
        ContactFragment cf = new ContactFragment();
        cf.dbWrite = dbHelper.getReadableDatabase();
        cf.dbRead = dbHelper.getReadableDatabase();
        return cf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("Test", "Contact Fragment onCreateView()");
        return inflater.inflate(R.layout.fragment_scope_text_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO - Temporary test button setup
        getActivity().findViewById(R.id.insert_button).setOnClickListener(this);
        getActivity().findViewById(R.id.select_button).setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.insert_button) {
            ContentValues values = new ContentValues();
            values.put(Test.SCOPETEXT_NAME, "ScopeText Test!");
            values.put(Test.CONTACT_NAME, "Bob");
            dbWrite.insert(Test.TABLE_NAME, null, values);
        } else {
            final String WHERE = Test.SCOPETEXT_NAME + " = ScopeText Test!";
            Cursor cursor = dbRead.query(Test.TABLE_NAME,
                         projection,
                         null,
                         null,
                         null,
                         null,
                         null);
            cursor.moveToFirst();
            final int ID = cursor.getColumnIndexOrThrow(Test.CONTACT_NAME);
            Toast.makeText(getActivity(), cursor.getString(ID), Toast.LENGTH_LONG).show();
        }
    }
}
