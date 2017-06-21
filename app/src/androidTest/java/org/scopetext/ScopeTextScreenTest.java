package org.scopetext;

import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.scopetext.model.dao.DBHelper;
import org.scopetext.model.schema.ScopeTextContract;
import org.scopetext.view.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.scopetext.model.schema.ContactAssocContract.ContactAssocSchema;
import static org.scopetext.model.schema.ContactContract.ContactSchema;
import static org.scopetext.model.schema.ScopeTextContract.ScopeTextSchema;

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

    @BeforeClass
    public static void beforeClass() {
        clearDB();
    }

    @After
    public void after() {
        clearDB();
    }

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
        insertScopeTexts();

        // Test
        mActivityRule.launchActivity(null);
        onView(allOf(withText("Name1"), hasSibling(withText("Contactz"))));
        //onData(withId(R.id.scopetext_name)).atPosition(0).check(matches(withText("Name1")));
    }

    // TODO Implement ViewAssertion interface, see https://developer.android.com/reference/android/support/test/espresso/ViewAssertion.html

    private void insertScopeTexts() {
        // Retrieve SQLiteDatabase
        DBHelper dbHelper;
        SQLiteDatabase db = null;
        try {
            dbHelper = new DBHelper(InstrumentationRegistry.getTargetContext());
            db = dbHelper.getWriteableDB();

            // Insert ScopeTexts
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ").append(ScopeTextContract.ScopeTextSchema.TABLE_NAME).append(" (")
                    .append(ScopeTextSchema.SCOPETEXT_ID).append(", ").append(ScopeTextSchema.NAME)
                    .append(", ").append(ScopeTextSchema.IN_USE)
                    .append(") VALUES (0, 'Name1', 'Y')");
            StringBuilder sql2 = new StringBuilder();
            sql2.append("INSERT INTO ").append(ScopeTextContract.ScopeTextSchema.TABLE_NAME).append(" (")
                    .append(ScopeTextSchema.SCOPETEXT_ID).append(", ").append(ScopeTextSchema.NAME)
                    .append(", ").append(ScopeTextSchema.IN_USE)
                    .append(") VALUES (1, 'Name2', 'N')");
            db.execSQL(sql.toString());
            db.execSQL(sql2.toString());

            // Insert Contacts
            sql = new StringBuilder();
            sql.append("INSERT INTO ").append(ContactSchema.TABLE_NAME).append(" (")
                    .append(ContactSchema.CONTACT_ID).append(", ").append(ContactSchema.NAME)
                    .append(") VALUES (0, 'Contact1')");
            sql2 = new StringBuilder();
            sql2.append("INSERT INTO ").append(ContactSchema.TABLE_NAME).append(" (")
                    .append(ContactSchema.CONTACT_ID).append(", ").append(ContactSchema.NAME)
                    .append(") VALUES (1, 'Contact2')");
            db.execSQL(sql.toString());
            db.execSQL(sql2.toString());

            // Insert ContactAssoc records
            sql = new StringBuilder();
            sql.append("INSERT INTO ").append(ContactAssocSchema.TABLE_NAME).append(" (")
                    .append(ContactAssocSchema.SCOPETEXT_ID).append(", ")
                    .append(ContactAssocSchema.CONTACT_ID).append(") VALUES (0, 0)");
            sql2 = new StringBuilder();
            sql2.append("INSERT INTO ").append(ContactAssocSchema.TABLE_NAME).append(" (")
                    .append(ContactAssocSchema.SCOPETEXT_ID).append(", ")
                    .append(ContactAssocSchema.CONTACT_ID).append(") VALUES (1, 1)");
            db.execSQL(sql.toString());
            db.execSQL(sql2.toString());
        } finally {
            if(db != null) {
                db.close();
            }
        }
    }

    private static void clearDB() {
        // Retrieve SQLiteDatabase
        DBHelper dbHelper;
        SQLiteDatabase db = null;
        try {
            dbHelper = new DBHelper(InstrumentationRegistry.getTargetContext());
            db = dbHelper.getWriteableDB();

            // Clear all data from DB
            db.delete(ContactAssocSchema.TABLE_NAME, null, null);
            db.delete(ContactSchema.TABLE_NAME, null, null);
            db.delete(ScopeTextSchema.TABLE_NAME, null, null);
        } finally {
            if(db != null) {
                db.close();
            }
        }
    }
}
