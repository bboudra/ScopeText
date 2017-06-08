package org.scopetext.presenter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.scopetext.model.cache.Cache;
import org.scopetext.model.dao.DBHelper;
import org.scopetext.model.dao.SQL;
import org.scopetext.model.dao.SQLTask;
import org.scopetext.model.javabean.Contact;
import org.scopetext.model.javabean.ScopeText;
import org.scopetext.presenter.fragment.FragmentAction;
import org.scopetext.presenter.fragment.ScopeTextFragment;
import org.scopetext.view.NewContactFragment;
import org.scopetext.view.ScopeTextListFragment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Unit tests for Presenter.java Created by john.qualls on 8/25/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScopeTextPresenterTest {
    @Rule
    public ExpectedException thrown= ExpectedException.none();
    private  boolean isRecyclerViewSet;
    private  int viewHolderPosition;
    private  ScopeTextPresenter objUnderTest;
    private  List<ScopeText> scopeTexts;
    private  ScopeText scopeText;
    @Mock
    private  DBHelper dbHelper;
    @Mock
    private  FragmentAction fragmentAction;
    @Mock
    private  Cache cache;
    @Mock
    private  AppCompatActivity activity;
    @Mock
    private  SQLTask sqlTask;
    @Mock
    private  ScopeTextViewHolder viewHolder;
    @Mock
    private ScopeTextListAdapter scopeTextListAdapter;
    @Mock
    private LinearLayout scopeTextListLinearLayout;
    @Mock
    private TextView scopeTextView;
    @Mock
    private TextView contactView;

    /*
     * Expected exception messages
     */
    // TODO Remove and put literals in asserts within test cases
    private static final String NULL_VIEWHOLDER = "ViewHolder parameter cannot be null";
    private static final String NULL_LINEAR_LAYOUT = "LinearLayout from ViewHolder parameter cannot be " +
            "null";
    private static final String NULL_DATASET = "dataSet parameter cannot be null";
    private static final String EMPTY_DATASET = "dataSet parameter cannot be EMPTY";
    private static final String NULL_SCOPETEXT_VIEW = "ScopeText TextView from ViewHolder parameter " +
            "cannot be null";
    private static final String NULL_CONTACT_VIEW = "Contact TextView from ViewHolder parameter " +
            "cannot be null";
    private static final String NULL_SCOPETEXT = "ScopeText from dataset parameter cannot be null";
    private static final String NULL_CONTACT = "Contact from dataset parameter cannot be null";
    private static final String NULL_SCOPETEXT_NAME = "ScopeText name from dataset parameter " +
            "cannot be null";
    private static final String EMPTY_SCOPETEXT_NAME = "ScopeText name from dataset parameter " +
            "cannot be empty";
    private static final String NULL_CONTACT_LIST = "Contact list from dataset parameter cannot be " +
            "null";
    private static final String EMPTY_CONTACT_LIST = "Contact list from dataset parameter cannot be " +
            "empty";

    @Before
    public void before() {
        objUnderTest = spy(new ScopeTextPresenter(dbHelper, fragmentAction, cache));
        objUnderTest.setScopeTextListAdapter(scopeTextListAdapter);
        scopeTexts = new ArrayList<>();
    }

    @Test
    public void itShouldVerifyNoActivityRefreshForNullActivity() {
        // Test
        objUnderTest.activityRefresh(null, null);
        verifyZeroInteractions(fragmentAction);
        verify(objUnderTest, times(0)).setupActionBar();
    }

    @Test
    public void itShouldVerifyNoDBRefreshForNullActivity() {
        // Test
        objUnderTest = spy(new ScopeTextPresenter(null, fragmentAction, cache));
        objUnderTest.activityRefresh(null, dbHelper);
        verifyZeroInteractions(fragmentAction);
        assertNull(objUnderTest.getDbHelper());
    }

    @Test
    public void itShouldVerifyActivityRefresh() {
        objUnderTest = spy(new ScopeTextPresenter(null, fragmentAction, cache));
        objUnderTest.activityRefresh(activity, dbHelper);
        verify(fragmentAction).activityRefresh(activity);
        verify(objUnderTest).setupActionBar();
        assertEquals(objUnderTest.getDbHelper(), dbHelper);
    }

    @Test
    public void itShouldVerifyNoActivityReferenceForNullActivity() {
        objUnderTest.activityRefresh(null,dbHelper);
        try {
            objUnderTest.setupActionBar();
        } catch (NullPointerException npe) {
            fail("Parameter should not be referenced if it is null.");
        }
    }

    @Test
    public void itShouldVerifyNoActionBarSetupForNullActivity() {
        objUnderTest.activityRefresh(null,dbHelper);
        objUnderTest.setupActionBar();
        verify(activity, times(0)).setSupportActionBar(null);
    }

    @Test
    public void itShouldVerifyActionBarSetupForValidToolbar() {
        // Mock setup
        objUnderTest.activityRefresh(activity, dbHelper);
        Toolbar toolbar = mock(Toolbar.class);
        when(activity.findViewById(anyInt())).thenReturn(toolbar);

        // Test
        objUnderTest.setupActionBar();
        verify(activity).setSupportActionBar(toolbar);
    }

    @Test
    public void itshouldVerifySTListFragmentAddedForSTType() {
        objUnderTest.addFragment(ScopeTextFragment.SCOPE_TEXT_LIST);
        verify(fragmentAction)
                .addFragment(eq(R.id.scopetext_fragment), isA(ScopeTextListFragment.class),
                        eq(ScopeTextFragment.SCOPE_TEXT_LIST));
    }

    @Test
    public void itshouldVerifyNewContactFragmentAddedForNewContactType() {
        objUnderTest.addFragment(ScopeTextFragment.NEW_CONTACT);
        verify(fragmentAction)
                .addFragment(eq(R.id.scopetext_fragment), isA(NewContactFragment.class),
                        eq(ScopeTextFragment.NEW_CONTACT));
    }

    @Test
    public void itshouldVerifyNoFragActionInteractionForNullType() {
        objUnderTest.addFragment(null);
        verifyZeroInteractions(fragmentAction);
    }

    @Test
    public void itShouldAssertFalseForNullFragmentName() {
        isRecyclerViewSet = objUnderTest.setRecyclerViewAdapter(null);
        assertFalse("RecyclerView was set with a null Fragment name.", isRecyclerViewSet);
    }

    @Test
    public void itShouldAssertFalseForInvalidFragmentName() {
        ScopeTextFragment fragment = ScopeTextFragment.NEW_CONTACT;
        isRecyclerViewSet = objUnderTest.setRecyclerViewAdapter(fragment);
        assertFalse("RecyclerView was set with an invalid Fragment name: " + fragment.getName(),
                isRecyclerViewSet);
    }

    @Test
    public void itShouldAssertAdapterImplForScopeTextListFragment() {
        // Mock setup
        objUnderTest.activityRefresh(activity, dbHelper);
        RecyclerView recyclerView = mock(RecyclerView.class);
        when(activity.findViewById(R.id.scopetext_list)).thenReturn(recyclerView);

        // Test
        ScopeTextFragment fragment = ScopeTextFragment.SCOPE_TEXT_LIST;
        isRecyclerViewSet = objUnderTest.setRecyclerViewAdapter(fragment);
        verify(recyclerView).setAdapter(isA(RecyclerView.Adapter.class));
        verify(recyclerView).setLayoutManager(isA(RecyclerView.LayoutManager.class));
        assertTrue("RecyclerView should have been set with Fragment name: " + fragment.getName(),
                isRecyclerViewSet);
    }

    @Test
    public void itShouldAssertNoExecutionSQLForNullSQL() {
        assertFalse("SQLTask should not be executed with null a SQL.",objUnderTest.executeSQL(null, sqlTask));
    }

    @Test
    public void itShouldAssertNoExecutionSQLForNullSQLTask() {
        assertFalse("SQLTask should not be executed with null a SQLTask.",objUnderTest.executeSQL(SQL.SELECT_ALL_SCOPETEXTS_CONTACTS, null));
    }

    @Test
    public void itShouldAssertExecutionSQLForSelectAllScopeTextsSQL() {
        assertTrue("SQLTask should be executed with a valid SQL and SQLTask.",objUnderTest.executeSQL(SQL.SELECT_ALL_SCOPETEXTS_CONTACTS, sqlTask));
    }

    @Test
    public void itShouldAssertExceptionForNullViewHolder() {
        buildValidScopeTextWithValidContact("scopeText", "contact");
        scopeTexts.add(scopeText);

        // Test
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(NULL_VIEWHOLDER);
        objUnderTest.onBindViewHolder(null, viewHolderPosition, scopeTexts);
    }

    @Test
    public void itShouldAssertExceptionForNullLinearLayout() {
        buildValidScopeTextWithValidContact("scopeText", "contact");
        scopeTexts.add(scopeText);

        // Test
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(NULL_LINEAR_LAYOUT);
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, scopeTexts);
    }

    @Test
    public void itShouldAssertExceptionForNullScopeTextTextView() {
        buildValidScopeTextWithValidContact("scopeText", "contact");
        scopeTexts.add(scopeText);
        when(viewHolder.getViewGroup()).thenReturn(scopeTextListLinearLayout);
        when(scopeTextListLinearLayout.getChildAt(1)).thenReturn(scopeTextView);

        // Test
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(NULL_SCOPETEXT_VIEW);
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, scopeTexts);
    }

    @Test
    public void itShouldAssertExceptionForNullDataSet() {
        buildValidViewHolder();

        // Test
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(NULL_DATASET);
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, null);
    }

    @Test
    public void itShouldAssertExceptionForEmptyDataSet() {
        buildValidViewHolder();

        // Test
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(EMPTY_DATASET);
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, scopeTexts);
    }

    @Test
    public void itShouldAssertExceptionForNullScopeTextName() {
        buildValidViewHolder();
        scopeText = new ScopeText();
        scopeTexts.add(scopeText);

        // Test
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(NULL_SCOPETEXT_NAME);
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, scopeTexts);
    }

    @Test
    public void itShouldAssertExceptionForEmptyScopeTextName() {
        buildValidViewHolder();
        buildValidScopeTextWithValidContact("", "Contact");
        scopeTexts.add(scopeText);

        // Test
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(EMPTY_SCOPETEXT_NAME);
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, scopeTexts);
    }

    @Test
    public void itShouldAssertExceptionForNullContactList() {
        buildValidViewHolder();
        scopeText = new ScopeText();
        scopeText.setName("name");
        scopeTexts.add(scopeText);

        // Test
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(NULL_CONTACT_LIST);
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, scopeTexts);
    }

    @Test
    public void itShouldAssertExceptionForEmptyContactList() {
        buildValidViewHolder();
        scopeText = new ScopeText();
        scopeText.setName("name");
        List<Contact> contacts = new ArrayList<>();
        scopeText.setContacts(contacts);
        scopeTexts.add(scopeText);

        // Test
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(EMPTY_CONTACT_LIST);
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, scopeTexts);
    }

    @Test
    public void itShouldAssertExceptionForNullContact() {
        buildValidViewHolder();
        scopeText = new ScopeText();
        scopeText.setName("name");
        List<Contact> contacts = new ArrayList<>();
        Contact contact = null;
        contacts.add(contact);
        scopeText.setContacts(contacts);
        scopeTexts.add(scopeText);

        // Test
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(NULL_CONTACT);
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, scopeTexts);
    }

    @Test
    public void itShouldAssertExceptionForNullContactName() {
        buildValidViewHolder();
        scopeText = new ScopeText();
        scopeText.setName("name");
        List<Contact> contacts = new ArrayList<>();
        Contact contact = new Contact();
        contacts.add(contact);
        scopeText.setContacts(contacts);
        scopeTexts.add(scopeText);

        // Test
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Contact name from dataset parameter cannot be null");
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, scopeTexts);
    }

    @Test
    public void itShouldAssertExceptionForEmptyContactName() {
        buildValidViewHolder();
        scopeText = new ScopeText();
        scopeText.setName("name");
        List<Contact> contacts = new ArrayList<>();
        Contact contact = new Contact();
        contact.setName("");
        contacts.add(contact);
        scopeText.setContacts(contacts);
        scopeTexts.add(scopeText);

        // Test
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Contact name from dataset parameter cannot be empty");
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, scopeTexts);
    }

    @Test
    public void itShouldAssertExceptionForNegPosition() {
        // Setup
        buildValidViewHolder();
        buildValidScopeTextWithValidContact("Name", "Contact");
        scopeTexts.add(scopeText);

        // Test
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Position parameter is out of the dataset bounds");
        objUnderTest.onBindViewHolder(viewHolder, -1, scopeTexts);
    }

    @Test
    public void itShouldAssertExceptionForTooHighPosition() {
        // Setup
        buildValidViewHolder();
        buildValidScopeTextWithValidContact("Name", "Contact");
        scopeTexts.add(scopeText);

        // Test
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Position parameter is out of the dataset bounds");
        objUnderTest.onBindViewHolder(viewHolder, 1, scopeTexts);
    }

    @Test
    public void itShouldAssertScopeTextNameAndContactNameForValidScopeTextListArguments() {
        // Setup ScopeText
        String expectedScopeTextName = "scopeText",
               expectedContactName = "contact";
        buildValidScopeTextWithValidContact(expectedScopeTextName, expectedContactName);
        buildValidViewHolder();
        scopeTexts.add(scopeText);

        // Test
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, scopeTexts);
        verify(scopeTextView).setText(expectedScopeTextName);
        verify(contactView).setText(expectedContactName);
    }

    @Test
    public void itShouldAssertScopeTextAtSpecificPositionFrom2ScopeTexts() {
        // Setup ScopeTexts
        String expectedScopeTextName = "scopeText",
                expectedContactName = "contact";
        buildValidScopeTextWithValidContact("wrongScopeText", "wrongContact");
        scopeTexts.add(scopeText);
        buildValidScopeTextWithValidContact(expectedScopeTextName, expectedContactName);
        scopeTexts.add(scopeText);
        buildValidViewHolder();
        viewHolderPosition = 1;

        // Test
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, scopeTexts);
        verify(scopeTextView).setText(expectedScopeTextName);
        verify(contactView).setText(expectedContactName);
    }

    @Test
    public void itShouldAssertSecondContactForDuplicateScopeText() {
        // Setup ScopeText
        String stName = "ScopeText",
                contactName = "Contact",
                contactName2 = "Contact2";
        ScopeText scopeText = new ScopeText();
        scopeText.setName(stName);
        Contact contact = new Contact(),
                contact2 = new Contact();
        contact.setName(contactName);
        contact.setInList(true);
        contact2.setName(contactName2);
        List<Contact> contacts = new ArrayList<>(2);
        contacts.add(contact);
        contacts.add(contact2);
        scopeText.setContacts(contacts);
        scopeTexts.add(scopeText);

        // Setup Mocks
        buildValidViewHolder();

        // Test
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, scopeTexts);
        verify(scopeTextView).setText(stName);
        verify(contactView).setText(contactName2);
    }

    @Test
    public void itShouldAssertExceptionForNullScopeText() {
        // Setup
        ScopeText scopeText = null;
        scopeTexts = new ArrayList<>(1);
        scopeTexts.add(scopeText);
        buildValidViewHolder();

        // Test
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(NULL_SCOPETEXT);
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, scopeTexts);
    }

    @Test
    public void itShouldAssertExceptionForNullContactView() {
        // Setup
        buildValidScopeTextWithValidContact("Name", "Contact");
        scopeTexts.add(scopeText);
        when(viewHolder.getViewGroup()).thenReturn(scopeTextListLinearLayout);
        when(scopeTextListLinearLayout.getChildAt(0)).thenReturn(scopeTextView);

        // Test
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(NULL_CONTACT_VIEW);
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, scopeTexts);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertIllegalStateExceptionForEmptyScopeTextName() {
        // Setup ScopeText
        String stName = "",
                contactName = "Contact";
        ScopeText scopeText = new ScopeText();
        scopeText.setName(stName);
        Contact contact = new Contact();
        contact.setName(contactName);
        List<Contact> contacts = new ArrayList<>(1);
        contacts.add(contact);
        scopeText.setContacts(contacts);

        // Setup Mocks
        scopeTexts = new ArrayList<>(1);
        scopeTexts.add(scopeText);
        LinearLayout linearLayout = mock(LinearLayout.class);
        TextView scopeTextView = mock(TextView.class);
        TextView contactView = mock(TextView.class);
        when(viewHolder.getViewGroup()).thenReturn(linearLayout);
        when(linearLayout.getChildAt(0)).thenReturn(scopeTextView);
        when(linearLayout.getChildAt(1)).thenReturn(contactView);

        // Test
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, scopeTexts);
    }

    @Test
    public void itShouldVerifyNoCacheUpdateForNullResults() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("results List cannot be null");
        objUnderTest.retrieveSQLTaskResults(null);
    }

    @Test
    public void itShouldVerifyNoCacheUpdateForEmptyResults() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("results List cannot be empty");
        objUnderTest.retrieveSQLTaskResults(scopeTexts);
    }

    @Test
    public void itShouldAssertExceptionForWrongResultsType() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("type parameter for results list is not supported");
        List<Object> list = new ArrayList<>(1);
        list.add(new Object());
        objUnderTest.retrieveSQLTaskResults(list);
    }

    @Test
    public void itShouldVerifyCacheUpdateForScopeTextResults() {
        ScopeTextListAdapter adapter = mock(ScopeTextListAdapter.class);
        objUnderTest.setScopeTextListAdapter(adapter);
        scopeTexts.add(new ScopeText());
        objUnderTest.retrieveSQLTaskResults(scopeTexts);
        verify(cache).updateCache(scopeTexts);
    }

    @Test
    public void itShouldVerifyAdapterRefreshForScopeTextResult() {
        ScopeTextListAdapter adapter = mock(ScopeTextListAdapter.class);
        objUnderTest.setScopeTextListAdapter(adapter);
        scopeTexts.add(new ScopeText());
        objUnderTest.retrieveSQLTaskResults(scopeTexts);
        verify(adapter).notifyItemRangeInsertedWrapper(0, scopeTexts.size());
    }

    @Test
    public void itShouldVerifyCloseForValidDBHelper() {
        objUnderTest.shutdown();
        verify(dbHelper).close();
    }

    private void buildValidScopeTextWithValidContact(String scopeTextName, String contactName) {
        // Setup ScopeText
        ScopeText scopeText = new ScopeText();
        scopeText.setName(scopeTextName);
        Contact contact = new Contact();
        contact.setName(contactName);
        List<Contact> contacts = new ArrayList<>(1);
        contacts.add(contact);
        scopeText.setContacts(contacts);
        this.scopeText = scopeText;
    }

    private void buildValidViewHolder() {
        when(viewHolder.getViewGroup()).thenReturn(scopeTextListLinearLayout);
        when(scopeTextListLinearLayout.getChildAt(0)).thenReturn(scopeTextView);
        when(scopeTextListLinearLayout.getChildAt(1)).thenReturn(contactView);
    }
}
