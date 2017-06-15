package org.scopetext;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.scopetext.model.dao.DBHelper;
import org.scopetext.presenter.R;
import org.scopetext.view.MainActivity;

/**
 * Encapsulates all of the functional tests for the ScopeText screen. These tests meet the
 * requirements specified <a href=https://github.com/Alton09/ScopeText/wiki/Requirements:-ScopeText-1.0#ScopeTextScreen">here.</a>
 * <br/>
 * Created by john.qualls on 6/11/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ScopeTextScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class, false, false);

    /**
     * This test ensures that on app startup, all persisted ScopeTexts in the DB are retrieved in a
     * list. See the requirement <a href=https://github.com/Alton09/ScopeText/wiki/Requirements:-ScopeText-1.0#ScopeTextListRetrieval">here.</a>
     *
     * <p>
     *     The data inserted consists of 2 ScopeText objects that must be asserted after the
     *     Activity is started. The 1st ScopeText's name and 2 contact names are asserted. The
     *     2nd ScopeText's name and 1 contact name are asserted.
     * </p>
     */
    @Test
    public void itShouldAssertAllPersistedScopeTextsAreRetrievedOnStartup() {
        // Inject test data
        DBHelper dbHelper;
        SQLiteDatabase db = null;
        try {
            dbHelper = new DBHelper(InstrumentationRegistry.getTargetContext());
        } finally {
            if(db != null) {
                db.close();
            }
        }

        // Test
        mActivityRule.launchActivity(null);
        onData(withId(R.id.scopetext_list)).atPosition(0).check(matches(withText("Name1")));
    }

    // TODO Implement ViewAssertion interface, see https://developer.android.com/reference/android/support/test/espresso/ViewAssertion.html

    private void clearDB(SQLiteDatabase db) {
        // Insert ScopeTexts
        String sql = "INSERT INTO SCOPETEXT (SCOPETEXT_ID, NAME, IN_USE) " +
                "VALUES (0, 'Name1', 'Y')";
        String sql2 = "INSERT INTO SCOPETEXT (SCOPETEXT_ID, NAME, IN_USE) " +
                "VALUES (1, 'Name2', 'N')";
        db.execSQL(sql);
        db.execSQL(sql2);

        // Insert Contacts
        sql = "INSERT INTO CONTACT (CONTACT_ID, NAME) VALUES (0, 'Contact1')";
        sql2 = "INSERT INTO CONTACT (CONTACT_ID, NAME) VALUES (1, 'Contact2')";
        String sql3 = "INSERT INTO CONTACT (CONTACT_ID, NAME) VALUES (2, 'Contact3')";
        db.execSQL(sql);
        db.execSQL(sql2);
        db.execSQL(sql3);

        // Insert ContactAssoc records
        sql = "INSERT INTO CONTACT_ASSOCIATION (SCOPETEXT_ID, CONTACT_ID)\n " +
                "VALUES (0, 0);";
        sql2 = "INSERT INTO CONTACT_ASSOCIATION (SCOPETEXT_ID, CONTACT_ID)\n " +
                "VALUES (0, 1);";
        sql3 = "INSERT INTO CONTACT_ASSOCIATION (SCOPETEXT_ID, CONTACT_ID)\n " +
                "VALUES (1, 2);";
        db.execSQL(sql);
        db.execSQL(sql2);
        db.execSQL(sql3);
    }

    private void deleteScopeTexts(SQLiteDatabase db) {
        db.delete("CONTACT_ASSOCIATION", null, null);
    }
}
