package org.scopetext.presenter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
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

    @Test(expected = NullPointerException.class)
    public void itShouldAssertIllegalArgumentExceptionForNullViewHolder() {
        buildValidScopeTextWithValidContact("scopeText", "contact");
        viewHolderDataSet.add(scopeText);

        try {
            objUnderTest.onBindViewHolder(null, viewHolderPosition, viewHolderDataSet);
        } catch (NullPointerException e) {
            assertEquals(ScopeTextPresenter.NULL_VIEWHOLDER, e.getMessage());
            throw e;
        }
    }

    @Test(expected = NullPointerException.class)
    public void itShouldAssertIllegalArgumentExceptionForNullLinearLayout() {
        buildValidScopeTextWithValidContact("scopeText", "contact");
        viewHolderDataSet.add(scopeText);

        try {
            objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet);
        } catch (NullPointerException e) {
            assertEquals(ScopeTextPresenter.NULL_LINEAR_LAYOUT, e.getMessage());
            throw e;
        }
    }

    @Test(expected = NullPointerException.class)
    public void itShouldAssertIllegalArgumentExceptionForNullScopeTextTextView() {
        buildValidScopeTextWithValidContact("scopeText", "contact");
        viewHolderDataSet.add(scopeText);
        when(viewHolder.getViewGroup()).thenReturn(scopeTextListLinearLayout);
        when(scopeTextListLinearLayout.getChildAt(1)).thenReturn(scopeTextView);

        try {
            objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet);
        } catch (NullPointerException e) {
            assertEquals(ScopeTextPresenter.NULL_TEXTVIEW, e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertIllegalArgumentExceptionForNullContactTextView() {
        buildValidScopeTextWithValidContact("scopeText", "contact");
        viewHolderDataSet.add(scopeText);
        when(viewHolder.getViewGroup()).thenReturn(scopeTextListLinearLayout);
        when(scopeTextListLinearLayout.getChildAt(0)).thenReturn(scopeTextView);

        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition,
                viewHolderDataSet);
    }

    @Test(expected = NullPointerException.class)
    public void itShouldAssertIllegalArgumentExceptionForNullDataSet() {
        buildValidViewHolder();

        try {
            objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, null);
        } catch (NullPointerException e) {
            assertEquals(ScopeTextPresenter.NULL_DATASET, e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertIllegalArgumentExceptionForEmptyDataSet() {
        buildValidViewHolder();

        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet);
    }

    @Test(expected = NullPointerException.class)
    public void itShouldAssertIllegalArgumentExceptionForNullScopeText() {
        buildValidViewHolder();
        viewHolderDataSet.add(scopeText);

        try {
            objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet);
        } catch (NullPointerException e) {
            assertEquals(ScopeTextPresenter.NULL_SCOPETEXT, e.getMessage());
            throw e;
        }
    }

    @Test(expected = NullPointerException.class)
    public void itShouldAssertIllegalArgumentExceptionForNullScopeTextName() {
        buildValidViewHolder();
        scopeText = new ScopeText();
        viewHolderDataSet.add(scopeText);

        try {
            objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet);
        } catch (NullPointerException e) {
            assertEquals(ScopeTextPresenter.NULL_SCOPETEXT_NAME, e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertIllegalArgumentExceptionForEmptyScopeTextName() {
        buildValidViewHolder();
        scopeText = new ScopeText();
        scopeText.setName("");
        viewHolderDataSet.add(scopeText);

        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition,
                viewHolderDataSet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertIllegalArgumentExceptionForNullContactList() {
        buildValidViewHolder();
        scopeText = new ScopeText();
        scopeText.setName("name");
        viewHolderDataSet.add(scopeText);

        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition,
                viewHolderDataSet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertIllegalArgumentExceptionForEmptyContactList() {
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
    public void itShouldAssertIllegalArgumentExceptionForNullContact() {
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
    public void itShouldAssertIllegalArgumentExceptionForNullContactName() {
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
    public void itShouldAssertIllegalArgumentExceptionForEmptyContactName() {
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
    public void itShouldAssertIllegalArgumentExceptionForNegPosition() {
        // Setup ScopeText
        ScopeText scopeText = new ScopeText();
        viewHolderDataSet = new ArrayList<>(1);
        viewHolderDataSet.add(scopeText);

        // Test
        objUnderTest.onBindViewHolder(viewHolder, -1, viewHolderDataSet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertIllegalArgumentExceptionForTooHighPosition() {
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

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertIllegalStateExceptionForNullScopeText() {
        // Setup ScopeText
        String stName = "ScopeText",
                contactName = "Contact";
        ScopeText scopeText = new ScopeText();
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

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertIllegalStateExceptionForNullContacts() {
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
    public void itShouldAssertIllegalArgumentExceptionForWrongResultsType() {
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
