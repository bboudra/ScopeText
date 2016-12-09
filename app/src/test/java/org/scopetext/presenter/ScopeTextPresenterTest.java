package org.scopetext.presenter;

import android.support.v7.app.AppCompatActivity;

import org.junit.Before;
import org.junit.Test;
import org.scopetext.model.dao.DBHelper;
import org.scopetext.view.NewContactFragment;
import org.scopetext.view.ScopeTextListFragment;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Unit tests for Presenter.java
 * Created by john.qualls on 8/25/2016.
 */
public class ScopeTextPresenterTest {
    private Presenter objUnderTest;
    DBHelper dbHelper;
    FragmentAction fragmentAction;


    @Before
    public void mockSetup() {
        dbHelper = mock(DBHelper.class);
        fragmentAction = mock(ScopeTextFragmentAction.class);
        objUnderTest = new ScopeTextPresenter(dbHelper, fragmentAction);
    }

    @Test
    public void shouldVerifySTListFragmentAddedForSTType() {
        objUnderTest.addFragment(ScopeTextFragment.SCOPE_TEXT_LIST);
        verify(fragmentAction).addFragment(eq(R.id.scopetext_fragment), isA(
                ScopeTextListFragment.class), eq(ScopeTextFragment.SCOPE_TEXT_LIST));
    }

    @Test
    public void shouldVerifyNewContactFragmentAddedForNewContactType() {
        objUnderTest.addFragment(ScopeTextFragment.NEW_CONTACT);
        verify(fragmentAction).addFragment(eq(R.id.scopetext_fragment), isA(
                NewContactFragment.class), eq(ScopeTextFragment.NEW_CONTACT));
    }

    @Test
    public void shouldVerifyNoFragActionInteractionForNullType() {
        objUnderTest.addFragment(null);
        verifyZeroInteractions(fragmentAction);
    }

    @Test
    public void shouldVerifyNoActivityRefreshForNullActivity() {
        // Test
        objUnderTest.activityRefresh(null);
        verifyZeroInteractions(fragmentAction);
    }

    @Test
    public void itShouldVerifyActivityRefresh() {
        // Mock Setup
        AppCompatActivity activity = mock(AppCompatActivity.class);

        // Test
        objUnderTest.activityRefresh(activity);
        verify(fragmentAction).activityRefresh(activity);
    }

    // TODO Refactor tests once presenter collaborators are refactored.
 /*   @Before
    public void mockSetup() {
        dbHelper = Mockito.mock(DBHelper.class);
        toolbarManager = Mockito.mock(ToolbarManager.class);
        fragmentAction = Mockito.mock(ScopeTextFragmentAction.class);
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
        Mockito.verify(fragmentAction).addFragment(Matchers.any(ScopeTextListFragment.class));
    }*/
}
