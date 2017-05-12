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
import static org.mockito.Matchers.any;
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

    @Before
    public void before() {
        objUnderTest = spy(new ScopeTextPresenter(dbHelper, fragmentAction, cache));
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

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertIllegalArgumentExceptionForNullViewHolder() {
        viewHolderDataSet = new ArrayList<>();
        objUnderTest.onBindViewHolder(null, viewHolderPosition, viewHolderDataSet,
                ScopeTextFragment.SCOPE_TEXT_LIST);
        fail("IllegalArgumentException should have been thrown from null ViewHolder.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertIllegalArgumentExceptionForNullDataSet() {
        viewHolderDataSet = null;
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet,
                ScopeTextFragment.SCOPE_TEXT_LIST);
        fail("IllegalArgumentException should have been thrown from null DataSet.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertIllegalArgumentExceptionForInvalidFragmentName() {
        viewHolderDataSet = new ArrayList<>();
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet,
                ScopeTextFragment.NEW_CONTACT);
        fail("IllegalArgumentException should have been thrown from invalid FragmentName.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertIllegalArgumentExceptionForNullFragmentName() {
        viewHolderDataSet = new ArrayList<>();
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet,
                null);
        fail("IllegalArgumentException should have been thrown from invalid FragmentName.");
    }

    @Test
    public void itShouldAssertScopeTextNameAndContactNameForValidScopeTextListArguments() {

        // Setup ScopeText
        String stName = "ScopeText",
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
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet,
                ScopeTextFragment.SCOPE_TEXT_LIST);
        verify(scopeTextView).setText(stName);
        verify(contactView).setText(contactName);
    }

    @Test(expected = IllegalStateException.class)
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
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet,
                ScopeTextFragment.SCOPE_TEXT_LIST);
        fail("IllegalStateException should have been thrown with a null ScopeText name.");
    }

    @Test(expected = IllegalStateException.class)
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
        objUnderTest.onBindViewHolder(viewHolder, viewHolderPosition, viewHolderDataSet,
                ScopeTextFragment.SCOPE_TEXT_LIST);
        fail("IllegalStateException should have been thrown with an empty ScopeText name.");
    }

    //TODO Change when new caches are updated
    @Test
    public void itShouldVerifyNoCacheUpdateForNullResults() {
        objUnderTest.retrieveSQLTaskResults(null);
        verify(cache, times(0)).updateCache(isA(List.class));
    }

    @Test
    public void itShouldVerifyNoCacheUpdateForEmptyResults() {
        objUnderTest.retrieveSQLTaskResults(null);
        verify(cache, times(0)).updateCache(isA(List.class));
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
}
