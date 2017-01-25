package org.scopetext.presenter;

import android.databinding.tool.processing.Scope;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.scopetext.model.dao.DBHelper;
import org.scopetext.model.javabean.ScopeText;
import org.scopetext.presenter.fragment.FragmentAction;
import org.scopetext.presenter.fragment.ScopeTextFragment;
import org.scopetext.presenter.fragment.ScopeTextFragmentAction;
import org.scopetext.view.NewContactFragment;
import org.scopetext.view.ScopeTextListFragment;

import java.util.ArrayList;
import java.util.List;

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
    private ScopeTextPresenter objUnderTest;
    @Mock
    DBHelper dbHelper;
    @Mock
    FragmentAction fragmentAction;


    @Before
    public void mockSetup() {
        objUnderTest = spy(new ScopeTextPresenter(dbHelper, fragmentAction));
    }

    @Test
    public void shouldVerifyNoActivityRefreshForNullActivity() {
        // Test
        objUnderTest = spy(new ScopeTextPresenter(null, fragmentAction));
        objUnderTest.activityRefresh(null, dbHelper);
        verifyZeroInteractions(fragmentAction);
        verify(objUnderTest, times(0)).setupActionBar(isA(AppCompatActivity.class));
        Assert.assertNull(objUnderTest.getDbHelper());
    }

    @Test
    public void itShouldVerifyActivityRefresh() {
        // Mock Setup
        AppCompatActivity activity = mock(AppCompatActivity.class);

        // Test
        objUnderTest = spy(new ScopeTextPresenter(null, fragmentAction));
        objUnderTest.activityRefresh(activity, dbHelper);
        verify(fragmentAction).activityRefresh(activity);
        verify(objUnderTest).setupActionBar(activity);
        Assert.assertEquals(objUnderTest.getDbHelper(), dbHelper);
    }

    @Test
    public void itShouldVerifyNoActivityReferenceForNullActivity() {
        // Test
        try {
            objUnderTest.setupActionBar(null);
        } catch (NullPointerException npe) {
            Assert.fail("Parameter should not be referenced if it is null.");
        }
    }

    @Test
    public void itShouldVerifyNoActionBarSetupForNullActivity() {
        // Test
        AppCompatActivity activity = mock(AppCompatActivity.class);

        // Test
        objUnderTest.setupActionBar(activity);
        verify(activity, times(0)).setSupportActionBar(null);
    }

    @Test
    public void itShouldVerifyActionBarSetupForValidToolbar() {
        // Test
        AppCompatActivity activity = mock(AppCompatActivity.class);
        Toolbar toolbar = mock(Toolbar.class);
        when(activity.findViewById(anyInt())).thenReturn(toolbar);

        // Test
        objUnderTest.setupActionBar(activity);
        verify(activity).findViewById(R.id.actionBar);
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
    public void itShouldAssertCacheUpdateForScopeTextSQL() {
        // TODO Implement .equals() methods for java beans
        // Setup
        List<Object> expectedResults = new ArrayList<>(1);
        String name = "name1";
        ScopeText scopeText = new ScopeText();
        scopeText.setName(name);
        expectedResults.add(scopeText);

        // Test
        objUnderTest.retrieveSQLTaskResults(expectedResults);
        List<ScopeText> actualResults = objUnderTest.getScopeTexts();
        Assert.assertEquals(name, actualResults.get(0).getName());
    }

    // TODO Refactor tests once presenter collaborators are refactored.
 /*   @Before
    public void mockSetup() {
        dbHelper = mock(DBHelper.class);
        toolbarManager = mock(ToolbarManager.class);
        fragmentAction = mock(ScopeTextFragmentAction.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullDBHelperTest() {
        dbHelper = null;
        objUnderTest = new ScopeTextPresenter(dbHelper, toolbarManager, fragmentAction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullToolbarManagerTest() {
        toolbarManager = null;
        objUnderTest = new copeTextPresenter(dbHelper, toolbarManager, fragmentAction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullSTFragManagerTest() {
        fragmentAction = null;
        objUnderTest = new ScopeTextPresenter(dbHelper, toolbarManager, fragmentAction);
    }

    @Test
    public void addFragmentOnStartupTest() {
        objUnderTest = new ScopeTextPresenter(dbHelper, toolbarManager, fragmentAction);
        verify(fragmentAction).addFragment(Matchers.any(ScopeTextListFragment.class));
    }*/
}
