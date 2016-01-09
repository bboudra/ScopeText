package org.scopetext.scopetext;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private final String NEW_CONTACT = "NEW_CONTACT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.actionBar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(0xFFFFFFFF);
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
            case R.id.action_new_contact:
                newContactFragment();
            default:
                // Call super class if unrecognized button
                return super.onOptionsItemSelected(item);
        }
    }

    private void newContactFragment() {
        NewContactFragment fragment = NewContactFragment.newInstance();
        getSupportFragmentManager().beginTransaction().
                add(R.id.fragment_container, fragment, NEW_CONTACT).
                addToBackStack(NEW_CONTACT).commit();
    }
}
