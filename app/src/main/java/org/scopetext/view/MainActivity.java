package org.scopetext.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.scopetext.database.dao.DBHelper;
import org.scopetext.presenter.R;
import org.scopetext.presenter.ToolbarManager;

/**
 * Controller component of the app. Communicates with all of the other
 * components, and handles responses from all of them.
 * @author John Qualls
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private ToolbarManager toolbarManager;

    /**
     * Initializes all necessary components.
     *
     * @param savedInstanceState The application's saved content
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        // Initialize db
        dbHelper = new DBHelper(this);
*/
        // Toolbar setup
        toolbarManager = ToolbarManager.getInstance(this, R.id.actionBar);

        // Initialize ScopeText fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ContactFragment fragment = ContactFragment.newInstance(dbHelper);
        transaction.add(R.id.scopetext_fragment, fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_custom_settings:
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
