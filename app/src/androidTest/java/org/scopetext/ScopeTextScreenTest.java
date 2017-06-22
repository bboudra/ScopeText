package org.scopetext;

import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.scopetext.model.dao.DBHelper;
import org.scopetext.model.schema.ScopeTextContract;
import org.scopetext.presenter.R;
import org.scopetext.view.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
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
     *     Activity is started. The 1st ScopeText's name and contact name are asserted. The
     *     2nd ScopeText's name and contact name are asserted.
     * </p>
     */
    @Test
    public void itShouldAssertAllPersistedScopeTextsAreRetrievedOnStartup() {
        // Inject test data
        insertScopeTexts();

        // Test
        mActivityRule.launchActivity(null);
        onView(withIndex(withId(R.id.scopetext_name), 0)).check(matches(withText("Name1")));
        onView(withIndex(withId(R.id.scopetext_contact), 0)).check(matches(withText("Contact1")));
        onView(withIndex(withId(R.id.scopetext_name), 1)).check(matches(withText("Name2")));
        onView(withIndex(withId(R.id.scopetext_contact), 1)).check(matches(withText("Contact2")));
    }

    /**
     * Returns a Custom matcher that retrieves a specific index of from multiple retrieved Views.
     *
     * <p>
     *     Copied from FrostRocket's answer to the StackOverflow question
     *     <a href=https://stackoverflow.com/questions/29378552/in-espresso-how-to-choose-one-the-view-who-have-same-id-to-avoid-ambiguousviewm>here</a>
     * </p>
     *
     * @param matcher the matcher used to retrieve multiple views
     * @param index the index with the specified View from the matcher
     * @return The specified view
     */
    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
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
