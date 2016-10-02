package org.scopetext.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.scopetext.model.dao.DBHelper;
import org.scopetext.model.schema.DBOperation;
import org.scopetext.presenter.Presenter;
import org.scopetext.presenter.R;
import org.scopetext.presenter.ScopeTextFragmentManager;
import org.scopetext.presenter.ToolbarManager;

/**
 * Controller component of the app. Communicates with all of the other
 * components, and handles responses from all of them.
 * @author John Qualls
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    private Presenter presenter;

    /**
     * Initializes all necessary components.
     *
     * @param savedInstanceState The application's saved content
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup Presenter
        DBHelper dbHelper = new DBHelper(this);
        ToolbarManager toolbarManager = new ToolbarManager(this);
        ScopeTextFragmentManager stFragManager = new ScopeTextFragmentManager(this);
        presenter = new Presenter(dbHelper, toolbarManager, stFragManager);
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

    protected void dbReadOperation(DBOperation dbOperation) {
        presenter.dbReadOperation(dbOperation);
    }
}
