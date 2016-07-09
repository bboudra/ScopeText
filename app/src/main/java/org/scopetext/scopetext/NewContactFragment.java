package org.scopetext.scopetext;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;


/**
 * Handles the UI when the user adds a new contact.
 */
public class NewContactFragment extends Fragment
    implements LoaderCallbacks<Cursor>, OnItemClickListener, OnEditorActionListener{

    /**
     * Replaces constructor.
     * @return A new instance of fragment NewContactFragment.
     */
    // TODO: Pass in parameters for fragment communication.
    public static NewContactFragment newInstance() {
        NewContactFragment fragment = new NewContactFragment();
        return fragment;
    }

    // Contains columns returned from query
    @SuppressLint("InlinedApi")
    private static final String[] PROJECTION =
            {
                    Contacts._ID,
                    Contacts.LOOKUP_KEY,
                    Build.VERSION.SDK_INT
                            >= Build.VERSION_CODES.HONEYCOMB ?
                            Contacts.DISPLAY_NAME_PRIMARY :
                            Contacts.DISPLAY_NAME

            };

    /*
 * Defines an array that contains column names to move from
 * the Cursor to the ListView.
 */
    @SuppressLint("InlinedApi")
    private final static String[] FROM_COLUMNS = {
            Build.VERSION.SDK_INT
                    >= Build.VERSION_CODES.HONEYCOMB ?
                    Contacts.DISPLAY_NAME_PRIMARY :
                    Contacts.DISPLAY_NAME
    };
    /*
     * Defines an array that contains resource ids for the layout views
     * that get the Cursor column contents. The id is pre-defined in
     * the Android framework, so it is prefaced with "android.R.id"
     */
    private final static int[] TO_IDS = {
            android.R.id.text1
    };

    // Defines the text expression
    @SuppressLint("InlinedApi")
    private static final String SELECTION =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                    Contacts.DISPLAY_NAME_PRIMARY + " LIKE ?" :
                    Contacts.DISPLAY_NAME + " LIKE ?";

    // Defines a variable for the search string
    private String mSearchString;
    // Defines the array to hold values that replace the ?
    private String[] mSelectionArgs = { mSearchString };
    // Define global mutable variables
    // Define a ListView object
    ListView mContactsList;
    // Define variables for the contact the user selects
    // The contact's _ID value
    long mContactId;
    // The contact's LOOKUP_KEY
    String mContactKey;
    // A content URI for the selected contact
    Uri mContactUri;
    // An adapter that binds the result Cursor to the ListView
    private SimpleCursorAdapter mCursorAdapter;

    // The column index for the _ID column
    private static final int CONTACT_ID_INDEX = 0;
    // The column index for the LOOKUP_KEY column
    private static final int LOOKUP_KEY_INDEX = 1;
    private static final String LOG_TAG = "NewContactFragment.java";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("Test", "New Contact Fragment onCreateView()");
        return inflater.inflate(R.layout.fragment_new_contact, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Initializes the loader
        getLoaderManager().initLoader(0, null, this);

        // Get ListView from parent activity
        mContactsList = (ListView) getActivity().findViewById(android.R.id.list);

        // Get a cursor adapter
        mCursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.new_contact_list_item,
                null, FROM_COLUMNS, TO_IDS, 0);
        mContactsList.setAdapter(mCursorAdapter);

        // Set on click listener to list view
        mContactsList.setOnItemClickListener(this);

        // Set edit text search button listener
        EditText form = (EditText)getActivity().findViewById(R.id.search_edit_text);
        form.setOnEditorActionListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View item, int position, long rowID) {
        // Get the Cursor
        SimpleCursorAdapter adapter = (SimpleCursorAdapter) parent.getAdapter();
        Cursor cursor = adapter.getCursor();
        // Move to the selected contact
        cursor.moveToPosition(position);
        // Get the _ID value
        mContactId = cursor.getLong(CONTACT_ID_INDEX);
        // Get the selected LOOKUP KEY
        mContactKey = cursor.getString(LOOKUP_KEY_INDEX);
        // Create the contact's content Uri
        mContactUri = Contacts.getLookupUri(mContactId, mContactKey);
        /*
         * You can use mContactUri as the content URI for retrieving
         * the details for a contact.
         */
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
        /*
         * Makes search string into pattern and
         * stores it in the selection array
         */
        mSelectionArgs[0] = "%" + mSearchString + "%";
        // Starts the query
        return new CursorLoader(
                getActivity(),
                Contacts.CONTENT_URI,
                PROJECTION,
                SELECTION,
                mSelectionArgs,
                null
        );
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Delete the reference to the existing Cursor
        mCursorAdapter.swapCursor(null);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Put the result Cursor in the adapter for the ListView
        mCursorAdapter.swapCursor(cursor);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_SEARCH) {
            Log.i(LOG_TAG, "Search Button clicked.");
            // Get EditText input from the user
            EditText form = (EditText) v;
            String searchString = form.getText().toString();
            Log.i(LOG_TAG, "Contact Search String: " + searchString);
            mSearchString = searchString;
            getLoaderManager().restartLoader(0, null, this);
        }
        return false;
    }
}
