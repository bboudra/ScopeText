package org.scopetext.presenter;

import static org.scopetext.presenter.ScopeTextPresenter.Exception.*;
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
    private  List<ScopeText> viewHolderDataSet;
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

    @Before
    public void before() {
        objUnderTest = spy(new ScopeTextPresenter(dbHelper, fragmentAction, cache));
        objUnderTest.setScopeTextListAdapter(scopeTextListAdapter);
        viewHolderDataSet = new ArrayList<>();
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
        viewHolderDataSet.add(scopeText);

        // Test
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(NULL_VIEWHOLDER);
        objUnderTest.onBindViewHolder(null, viewHolderPosition, viewHolderDataSet);
    }

    @Test
    public void itShouldAssertExceptionForNullLinearLayout() {
        buildValidScopeTextWithValidContact("scopeText", "contact");
        viewHolderDataSet.add(scopeText);

        // Test
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(NULL_LINEAR_LAYOUT);
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet);
    }

    @Test
    public void itShouldAssertExceptionForNullScopeTextTextView() {
        buildValidScopeTextWithValidContact("scopeText", "contact");
        viewHolderDataSet.add(scopeText);
        when(viewHolder.getViewGroup()).thenReturn(scopeTextListLinearLayout);
        when(scopeTextListLinearLayout.getChildAt(1)).thenReturn(scopeTextView);

        // Test
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(NULL_TEXTVIEW);
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertExceptionForNullContactTextView() {
        buildValidScopeTextWithValidContact("scopeText", "contact");
        viewHolderDataSet.add(scopeText);
        when(viewHolder.getViewGroup()).thenReturn(scopeTextListLinearLayout);
        when(scopeTextListLinearLayout.getChildAt(0)).thenReturn(scopeTextView);

        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition,
                viewHolderDataSet);
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
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet);
    }

    @Test
    public void itShouldAssertExceptionForNullScopeTextName() {
        buildValidViewHolder();
        scopeText = new ScopeText();
        viewHolderDataSet.add(scopeText);

        // Test
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(NULL_SCOPETEXT_NAME);
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertExceptionForEmptyScopeTextName() {
        buildValidViewHolder();
        scopeText = new ScopeText();
        scopeText.setName("");
        viewHolderDataSet.add(scopeText);

        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition,
                viewHolderDataSet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertExceptionForNullContactList() {
        buildValidViewHolder();
        scopeText = new ScopeText();
        scopeText.setName("name");
        viewHolderDataSet.add(scopeText);

        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition,
                viewHolderDataSet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertExceptionForEmptyContactList() {
        buildValidViewHolder();
        scopeText = new ScopeText();
        scopeText.setName("name");
        List<Contact> contacts = new ArrayList<>();
        scopeText.setContacts(contacts);
        viewHolderDataSet.add(scopeText);

        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition,
                viewHolderDataSet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertExceptionForNullContact() {
        buildValidViewHolder();
        scopeText = new ScopeText();
        scopeText.setName("name");
        List<Contact> contacts = new ArrayList<>();
        Contact contact = null;
        contacts.add(contact);
        scopeText.setContacts(contacts);
        viewHolderDataSet.add(scopeText);

        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition,
                viewHolderDataSet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertExceptionForNullContactName() {
        buildValidViewHolder();
        scopeText = new ScopeText();
        scopeText.setName("name");
        List<Contact> contacts = new ArrayList<>();
        Contact contact = new Contact();
        contacts.add(contact);
        scopeText.setContacts(contacts);
        viewHolderDataSet.add(scopeText);

        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition,
                viewHolderDataSet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertExceptionForEmptyContactName() {
        buildValidViewHolder();
        scopeText = new ScopeText();
        scopeText.setName("name");
        List<Contact> contacts = new ArrayList<>();
        Contact contact = new Contact();
        contact.setName("");
        contacts.add(contact);
        scopeText.setContacts(contacts);
        viewHolderDataSet.add(scopeText);

        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition,
                viewHolderDataSet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertExceptionForNegPosition() {
        // Setup ScopeText
        ScopeText scopeText = new ScopeText();
        viewHolderDataSet = new ArrayList<>(1);
        viewHolderDataSet.add(scopeText);

        // Test
        objUnderTest.onBindViewHolder(viewHolder, -1, viewHolderDataSet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertExceptionForTooHighPosition() {
        // Setup ScopeText
        ScopeText scopeText = new ScopeText();
        viewHolderDataSet = new ArrayList<>(1);
        viewHolderDataSet.add(scopeText);

        // Test
        objUnderTest.onBindViewHolder(viewHolder, 1, viewHolderDataSet);
    }

    @Test
    public void itShouldAssertScopeTextNameAndContactNameForValidScopeTextListArguments() {
        // Setup ScopeText
        String expectedScopeTextName = "scopeText",
               expectedContactName = "contact";
        buildValidScopeTextWithValidContact(expectedScopeTextName, expectedContactName);
        buildValidViewHolder();
        viewHolderDataSet.add(scopeText);

        // Test
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet);
        verify(scopeTextView).setText(expectedScopeTextName);
        verify(contactView).setText(expectedContactName);
    }

    @Test
    public void itShouldAssertScopeTextAtSpecificPositionFrom2ScopeTexts() {
        // Setup ScopeTexts
        String expectedScopeTextName = "scopeText",
                expectedContactName = "contact";
        buildValidScopeTextWithValidContact("wrongScopeText", "wrongContact");
        viewHolderDataSet.add(scopeText);
        buildValidScopeTextWithValidContact(expectedScopeTextName, expectedContactName);
        viewHolderDataSet.add(scopeText);
        buildValidViewHolder();
        viewHolderPosition = 1;

        // Test
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet);
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

        // Setup Mocks
        buildValidViewHolder();

        // Test
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet);
        verify(scopeTextView).setText(stName);
        verify(contactView).setText(contactName2);
    }

    @Test
    public void itShouldAssertExceptionForNullScopeText() {
        // Setup
        ScopeText scopeText = null;
        viewHolderDataSet = new ArrayList<>(1);
        viewHolderDataSet.add(scopeText);
        buildValidViewHolder();

        // Test
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(NULL_SCOPETEXT);
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet);
    }

    @Test
    public void itShouldAssertExceptionForNullScopeTextView() {
        // Setup
        buildValidScopeTextWithValidContact("Name", "Contact");
        when(viewHolder.getViewGroup()).thenReturn(scopeTextListLinearLayout);
        when(scopeTextListLinearLayout.getChildAt(1)).thenReturn(contactView);

        // Test
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(NULL_TEXTVIEW);
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet);
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
        viewHolderDataSet = new ArrayList<>(1);
        viewHolderDataSet.add(scopeText);
        LinearLayout linearLayout = mock(LinearLayout.class);
        TextView scopeTextView = mock(TextView.class);
        TextView contactView = mock(TextView.class);
        when(viewHolder.getViewGroup()).thenReturn(linearLayout);
        when(linearLayout.getChildAt(0)).thenReturn(scopeTextView);
        when(linearLayout.getChildAt(1)).thenReturn(contactView);

        // Test
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet);
    }

    @Test
    public void itShouldAssertExceptionForNullContacts() {
        // Setup ScopeText
        String stName = "Name",
                contactName = "Contact";
        ScopeText scopeText = new ScopeText();
        scopeText.setName(stName);
        List<Contact> contacts = new ArrayList<>();
        scopeText.setContacts(contacts);

        // Setup Mocks
        viewHolderDataSet = new ArrayList<>(1);
        viewHolderDataSet.add(scopeText);
        LinearLayout linearLayout = mock(LinearLayout.class);
        TextView scopeTextView = mock(TextView.class);
        when(viewHolder.getViewGroup()).thenReturn(linearLayout);
        when(linearLayout.getChildAt(0)).thenReturn(scopeTextView);

        // Test
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertIllegalStateExceptionForEmptyContacts() {
        // Setup ScopeText
        String stName = "Name",
                contactName = "Contact";
        ScopeText scopeText = new ScopeText();
        scopeText.setName(stName);

        // Setup Mocks
        viewHolderDataSet = new ArrayList<>(1);
        viewHolderDataSet.add(scopeText);
        LinearLayout linearLayout = mock(LinearLayout.class);
        TextView scopeTextView = mock(TextView.class);
        when(viewHolder.getViewGroup()).thenReturn(linearLayout);
        when(linearLayout.getChildAt(0)).thenReturn(scopeTextView);

        // Test
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition,
                viewHolderDataSet);
    }

    //TODO Change when new caches are updated
    @Test
    public void itShouldVerifyNoCacheUpdateForNullResults() {
        objUnderTest.retrieveSQLTaskResults(null);
        verify(cache, times(0)).updateCache(isA(List.class));
    }

    @Test
    public void itShouldVerifyNoCacheUpdateForEmptyResults() {
        List<Object> results = new ArrayList<>();
        objUnderTest.retrieveSQLTaskResults(results);
        verify(cache, times(0)).updateCache(isA(List.class));
    }

    @Test
    public void itShouldVerifyCacheUpdateForScopeTextResult() {
        List<Object> results = new ArrayList<>();
        ScopeText scopeText = new ScopeText();
        results.add(scopeText);
        objUnderTest.retrieveSQLTaskResults(results);
        verify(cache).updateCache(isA(List.class));
    }

    @Test
    public void itShouldVerifyAdapterUpdateForScopeTextResult() {
        List<Object> results = new ArrayList<>();
        ScopeText scopeText = new ScopeText();
        results.add(scopeText);
        objUnderTest.retrieveSQLTaskResults(results);
        verify(scopeTextListAdapter).notifyItemRangeInsertedWrapper(0,
               results.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertExceptionForWrongResultsType() {
        List<Object> list = new ArrayList<>(1);
        list.add(new Object());
        objUnderTest.retrieveSQLTaskResults(list);
    }

    @Test
    public void itShouldAssertCacheUpdateForScopeTextResults() {
        ScopeTextListAdapter adapter = mock(ScopeTextListAdapter.class);
        objUnderTest.setScopeTextListAdapter(adapter);
        List<Object> list = new ArrayList<>(1);
        list.add(new ScopeText());
        objUnderTest.retrieveSQLTaskResults(list);
        verify(cache).updateCache(list);
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
