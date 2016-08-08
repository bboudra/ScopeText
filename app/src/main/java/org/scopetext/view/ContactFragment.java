package org.scopetext.view;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import org.scopetext.database.schema.SampleDBHelper;

import schema.SampleContract.Test;


/**
 * Handles the UI for displaying list of contacts.<br/>
 */
public class ContactFragment extends Fragment implements Button.OnClickListener,
        OnEditorActionListener {
    private final String[] PROJECTION = {
            Test._ID,
            Test.SCOPETEXT_NAME,
            Test.CONTACT_NAME
    };
    private SQLiteDatabase dbWrite,
                           dbRead;
    private SimpleCursorAdapter adapter;
    private Cursor cursor;

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
        final int[] toViews = {R.id.scopetext_name, R.id.scopetext_contact};
        final String[] fromColumns = {Test.SCOPETEXT_NAME, Test.CONTACT_NAME};
        adapter = new SimpleCursorAdapter(getActivity(), R.layout.scopetext_row, cursor, fromColumns, toViews, 0);
        ListView listView = (ListView) getActivity().findViewById(R.id.scopetext_list);
        listView.setAdapter(adapter);
        EditText et = (EditText)getActivity().findViewById(R.id.insert_button);
        et.setOnEditorActionListener(this);
        getActivity().findViewById(R.id.select_button).setOnClickListener(this);
        getActivity().findViewById(R.id.delete_button).setOnClickListener(this);
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
        if(v.getId() == R.id.delete_button) {
            int rows = dbWrite.delete(Test.TABLE_NAME, null, null);
            Toast.makeText(getActivity(), rows + "", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.select_button) {
            cursor = dbRead.query(Test.TABLE_NAME,
                    PROJECTION,
                    null,
                    null,
                    null,
                    null,
                    null);
            cursor.moveToFirst();
            adapter.swapCursor(cursor);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            EditText editText = (EditText) v;
            String value = editText.getText().toString();
            ContentValues values = new ContentValues();
            values.put(Test.SCOPETEXT_NAME, value);
            values.put(Test.CONTACT_NAME, "Bob");
            dbWrite.insert(Test.TABLE_NAME, null, values);
            return true;
        }
        return false;
    }
}
