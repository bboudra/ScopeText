package org.scopetext.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.scopetext.presenter.Presenter;
import org.scopetext.presenter.R;
import org.scopetext.presenter.ScopeTextPresenter;

/**
 * The main view component of the application. Routes all UI interaction to the Presenter component,
 * where the necessary business logic is executed. Created by john.qualls on 11/28/2016.
 *
 * @see Presenter
 */
public class MainActivity extends AppCompatActivity {
    private Presenter presenter;

    /**
     * Initializes all necessary components.
     *
     * @param savedInstanceState The application's saved content
     */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = ScopeTextPresenter.getInstance();
        presenter.activityRefresh(this);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_custom_settings:
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void getAllScopeTexts() {
        //presenter.getAllScopeTexts();
    }
}
